package Controller;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Player;
import View.View;
import Model.Card.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Controller {
    /**
     * <b>Deck Variable</b> that represents the deck of the game.
     */
    private Deck board;

    /**
     * <b>View Variable</b> that represents the view of the game.
     */
    private View view;

    /**
     * <b>Player Variable</b> that represents the first player of the game.
     */
    private Player player1_r;
    private Player player2_y;
    private Player PlayerPlaying;

    /**
     * <b>Transformer: </b> Initializes the game
     * @pre-condition None
     * @post-condition Initializes the game
     */
    public void initGame(){
        initBoardAndView();
        initPlayers();
        startGame();
    }

    /**
     * <b>Transformer: </b> Initializes the board and the view
     * @pre-condition None
     * @post-condition Initializes the board and the view
     */
    private void initBoardAndView() {
        board = new Deck();
        board.initDeck();
        view = new View();
    }

    /**
     * <b>Observer: </b> Initializes the players
     * @pre-condition None
     * @post-condition Initializes the players
     */
    private void initPlayers() {
        Pawn[] RedPawns = {new Pawn(PAWNS_COLOR.RED, 72), new Pawn(PAWNS_COLOR.RED, 72)};
        Pawn[] YellowPawns = {new Pawn(PAWNS_COLOR.YELLOW, 73), new Pawn(PAWNS_COLOR.YELLOW, 73)};
        player1_r = new Player(PAWNS_COLOR.RED,"Player 1", RedPawns);
        player2_y = new Player(PAWNS_COLOR.YELLOW,"Player 2", YellowPawns);
        view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));

        setListeners();
    }

    /**
     * <b>Transformer: </b>Starts the game
     * @pre-condition None
     * @post-condition Starts the game
     */
    private void startGame() {
        PlayerPlaying = new Random().nextInt(2) == 0 ? player1_r : player2_y;
        PlayerPlaying.swapTurns();
        System.out.println(PlayerPlaying.toString());
        board.drawCard();
        view.repaintLastCard(board.getCurrentCard());
        updateInfo();
        System.out.println("New Card: " + board.getCurrentCard().toString());
    }

    /**
     * <b>Observer:</b> Updates the info box
     * @pre-condition None
     * @post-condition Updates the info box
     */
    public void updateInfo() {
        try {
            String infoText = view.getInfoBox().getText(0, 9);
            String newInfo = "\n\n\nTurn: " + PlayerPlaying.toString() + "\nCards Remaining: " + board.getCardsRemaining() + "\n";
            view.repaintInfoBox(infoText + newInfo);
        } catch (BadLocationException ee) {
            System.out.println("ERROR");
        }
    }

    /**
     * <b>Transformer:</b> Folds the cards.
     * @pre-condition None
     * @post-condition Folds the cards.
     */
    public void foldCards(){
        Card currentCard = board.getCurrentCard();

        if(currentCard instanceof NumberSevenCard card) {
            boolean canMove = card.canMove(PlayerPlaying.getPawn(1), board) || card.canMove(PlayerPlaying.getPawn(2), board);

            for (int i = 6; i >= 1; i--) {
                if(canMove) {
                    break;
                }
                if (card.canMove(PlayerPlaying.getPawn(1), i, PlayerPlaying.getPawn(2), 7 - i, board)) {
                    canMove = true;
                    break;
                }
            }

            if (!canMove) {
                System.out.println("\nFold Button Pressed.\n");
                swapTurns();
                card.cancelCard();
            }
        }
        else if(currentCard instanceof NumberTwoCard card){
            if (!card.canMove(PlayerPlaying.getPawn(1), board) && !card.canMove(PlayerPlaying.getPawn(2), board)) {

                System.out.println("\nFold Button Pressed.\n");
                card.cancelCard();
            }
        }
        else if(currentCard instanceof NumberTenCard card){
            if(!card.canMove(PlayerPlaying.getPawn(1), board) && !card.canMove(PlayerPlaying.getPawn(2), board) && !card.canMoveBackwards(PlayerPlaying.getPawn(1), board) && !card.canMoveBackwards(PlayerPlaying.getPawn(2), board)) {
                System.out.println("\nFold Button Pressed.\n");
                swapTurns();
                card.cancelCard();
            }
        }
        else if (currentCard instanceof SorryCard) {
            SorryCard card = (SorryCard) board.getCurrentCard();
            Pawn oppPawn1;
            if (PlayerPlaying.equals(player1_r))
                oppPawn1 = player2_y.getPawn(1);
            else
                oppPawn1 = player1_r.getPawn(1);

            Pawn oppPawn2;
            if (PlayerPlaying.equals(player1_r))
                oppPawn2 = player2_y.getPawn(2);
            else
                oppPawn2 = player1_r.getPawn(2);

            if (!card.canMove(PlayerPlaying.getPawn(1), oppPawn1, board)
                    && !card.canMove(PlayerPlaying.getPawn(2), oppPawn1, board)
                    && !card.canMove(PlayerPlaying.getPawn(1), oppPawn2, board)
                    && !card.canMove(PlayerPlaying.getPawn(2), oppPawn2, board)) {

                System.out.println("Fold Button Pressed.\n");
                swapTurns();
                card.cancelCard();
            }
        }
        else if(currentCard instanceof NumberElevenCard card) {
            boolean canMove;
            Pawn oppPawn1;
            if (PlayerPlaying.equals(player1_r))
                oppPawn1 = player2_y.getPawn(1);
            else
                oppPawn1 = player1_r.getPawn(1);

            Pawn oppPawn2;
            if (PlayerPlaying.equals(player1_r))
                oppPawn2 = player2_y.getPawn(2);
            else
                oppPawn2 = player1_r.getPawn(2);

            if(!card.canMove(PlayerPlaying.getPawn(1), board) && !card.canMove(PlayerPlaying.getPawn(2), board)) {
                canMove = false;
            } else canMove = card.canMove(PlayerPlaying.getPawn(1), oppPawn1, board)
                    || card.canMove(PlayerPlaying.getPawn(2), oppPawn1, board)
                    || card.canMove(PlayerPlaying.getPawn(1), oppPawn2, board)
                    || card.canMove(PlayerPlaying.getPawn(2), oppPawn2, board);

            if (!canMove) {
                System.out.println("\nFold Button Pressed.\n");
                swapTurns();
                card.cancelCard();
            }
        }
        else {
            NumberCard card = (NumberCard) currentCard;
            if (!card.canMove(PlayerPlaying.getPawn(1), board)
                    && !card.canMove(PlayerPlaying.getPawn(2), board)) {

                System.out.println("Fold Button Pressed.\n");
                swapTurns();
                card.cancelCard();
            }
        }
    }

    /**
     * <b>Transformer: </b> Handles the cards.
     * @pre-condition None
     * @post-condition Swaps the Turns.
     */
    public void swapTurns() {
        PlayerPlaying.swapTurns();
        PlayerPlaying = PlayerPlaying.equals(player1_r) ? player2_y : player1_r;
        PlayerPlaying.swapTurns();
        System.out.println(PlayerPlaying.toString());
    }

    /**
     * <b>Transformer: </b> Sets the listeners
     * @pre-condition None
     * @post-condition Sets the listeners
     */
    public void setListeners() {
        foldButtonListener foldListenerInstance = new foldButtonListener();
        pawnButtonsListener pawnListenerInstance = new pawnButtonsListener();
        cardButtonListener cardListenerInstance = new cardButtonListener();
        menuBarListener menuListenerInstance = new menuBarListener();

        view.getFoldButton().addMouseListener(foldListenerInstance);
        view.getYELLOW_PAWNS()[0].addMouseListener(pawnListenerInstance);
        view.getYELLOW_PAWNS()[1].addMouseListener(pawnListenerInstance);
        view.getRED_PAWNS()[0].addMouseListener(pawnListenerInstance);
        view.getRED_PAWNS()[1].addMouseListener(pawnListenerInstance);
        view.getDeckOfCards().addMouseListener(cardListenerInstance);
        view.getJMenuBar().getMenu(0).addMouseListener(menuListenerInstance);
        view.getJMenuBar().getMenu(1).addMouseListener(menuListenerInstance);
        view.getJMenuBar().getMenu(2).addMouseListener(menuListenerInstance);
        view.getJMenuBar().getMenu(3).addMouseListener(menuListenerInstance);
    }

    /**
     * <b>Getter</b> for the PlayerPlaying
     * @return PlayerPlaying
     */
    public Player getPlayerPlaying() {
        return PlayerPlaying;
    }

    /**
     * <b>Getter</b> for the board
     * @return the board
     */
    public Object getBoard() {
        return board;
    }

    /**
     * <b>Getter</b> for the view
     * @return view
     */
    public Object getView() {
        return view;
    }

    /**
     * <b>Getter</b> for the player1_r
     * @return player1_r
     */
    public Object getPlayer1_r() {
        return player1_r;
    }

    /**
     * <b>Getter</b> for the player2_y
     * @return player2_y
     */
    public Object getPlayer2_y() {
        return player2_y;
    }

    /**
     * <b>Observer:</b> This is for the Auto Play.
     */
    public void gameplay() {
        while (!GameIsFinished()) {
            if (board.getCurrentCard().cardIsPlayed()) {
                board.drawCard();
                view.repaintLastCard(board.getCurrentCard());
                updateInfo();
                System.out.println("NEW CARD: " + board.getCurrentCard().toString());
            }
            if (GameIsFinished()) {
                printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                return;
            }
            if (!board.getCurrentCard().cardIsPlayed()) {
                autoPlay();
                foldCards();
            }
        }
        printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
    }

    /**
     * <b>Observer:</b> This is for the Auto Play.
     */
    public void autoPlay() {
        Card currentCard = board.getCurrentCard();
        if (!currentCard.cardIsPlayed()) {
            boolean moveMade = false;
            if (currentCard instanceof SorryCard card) {
                Player opponent = PlayerPlaying.equals(player1_r) ? player2_y : player1_r;
                for (int i = 1; i <= 2; i++) {
                    Pawn pawn = PlayerPlaying.getPawn(i);
                    Pawn oppPawn = opponent.getPawn(i);
                    if (card.canMove(pawn, oppPawn, board)) {
                        card.executeMove(pawn, oppPawn, board);
                        view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                        card.cancelCard();
                        moveMade = true;
                        break;
                    }
                }
            } else {
                for (int i = 1; i <= 2; i++) {
                    Pawn pawn = PlayerPlaying.getPawn(i);
                    if (currentCard.canMove(pawn, board)) {
                        currentCard.executeMove(pawn, board);
                        view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                        currentCard.cancelCard();
                        moveMade = true;
                        break;
                    }
                }
            }
            if (moveMade) {
                swapTurns();
            }
            try {
                Thread.sleep(50); // Add a 500ms delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <b>Listener:</b> for the card button.
     */
    private class cardButtonListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == view.getDeckOfCards()) {
                if (GameIsFinished()) {
                    printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                    return;
                }
                if (board.getCurrentCard().cardIsPlayed()){
                    board.drawCard();
                    view.repaintLastCard(board.getCurrentCard());
                    updateInfo();
                    System.out.println("NEW CARD: " + board.getCurrentCard().toString());
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        private Point originalLocation;
        private Dimension originalSize;

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == view.getDeckOfCards()) {
                JButton button = (JButton) e.getSource();
                originalSize = button.getSize();
                originalLocation = button.getLocation();
                int newWidth = (int)(originalSize.width * 1.1);
                int newHeight = (int)(originalSize.height * 1.1);
                int newX = originalLocation.x - (newWidth - originalSize.width) / 2;
                int newY = originalLocation.y - (newHeight - originalSize.height) / 2;
                button.setBounds(newX, newY, newWidth, newHeight);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == view.getDeckOfCards()) {
                JButton button = (JButton) e.getSource();
                button.setBounds(originalLocation.x, originalLocation.y, originalSize.width, originalSize.height);
            }
        }
    }

    /**
     * <b>Listener:</b> for the fold button.
     */
    class foldButtonListener implements MouseListener {
        private Point originalLocation;
        private Dimension originalSize;

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == view.getFoldButton()) {
                if (GameIsFinished()) {
                    printVictoryWindow("The winner is " + PlayerPlaying.toString() + "\n");
                    return;
                }
                if (!board.getCurrentCard().cardIsPlayed()) {
                    foldCards();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == view.getFoldButton()) {
                JButton button = (JButton) e.getSource();
                originalSize = button.getSize();
                originalLocation = button.getLocation();
                int newWidth = (int)(originalSize.width * 1.05);
                int newHeight = (int)(originalSize.height * 1.05);
                int newX = originalLocation.x - (newWidth - originalSize.width) / 2;
                int newY = originalLocation.y - (newHeight - originalSize.height) / 2;
                button.setBounds(newX, newY, newWidth, newHeight);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == view.getFoldButton()) {
                JButton button = (JButton) e.getSource();
                button.setBounds(originalLocation.x, originalLocation.y, originalSize.width, originalSize.height);
            }
        }
    }

    /**
     * <b>Listener:</b> for the menu bar.
     */
    private class menuBarListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == view.getJMenuBar().getMenu(0)) {
                NewGame();
            } else if (e.getSource() == view.getJMenuBar().getMenu(1)) {
                saveGame();
            } else if(e.getSource() == view.getJMenuBar().getMenu(2)) {
                loadGame();
            } else if(e.getSource() == view.getJMenuBar().getMenu(3)){
                System.exit(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * This method is to save the GameState.
     * @return True if the game is finished, false otherwise.
     */
    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("gamestate.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(new GameState(board, player1_r, player2_y, PlayerPlaying));
            out.close();
            fileOut.close();
            System.out.println("\nTHE GAME WAS SAVED SUCCESFULLY.\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * This method is to load the GameState.
     */
    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("gamestate.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            GameState gameState = (GameState) in.readObject();
            in.close();
            fileIn.close();

            this.board = gameState.getBoard();
            this.player1_r = gameState.getPlayer1_r();
            this.player2_y = gameState.getPlayer2_y();
            this.PlayerPlaying = gameState.getPlayerPlaying();

            for(Card card : this.board.getGameCards()){
                String imagePath = card.getImagePath();
                if(imagePath != null){
                    Image image = loadImage(imagePath);
                    card.setActualImage(image);
                } else {
                    Image defaultImage = loadImage("src/images/cards/cardBack.png");
                    card.setActualImage(defaultImage);
                }
            }

            view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
            view.repaintLastCard(board.getCurrentCard());
            updateInfo();

            System.out.println("\nTHE GAME WAS LOADED SUCCESFULLY.\n");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("GameState class not found");
            c.printStackTrace();
        }
    }

    /**
     * This method loads the Image.
     * @param imagePath The path of the image.
     * @return The image.
     */
    private Image loadImage(String imagePath) {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * <b>Listener: </b>for the Pawns.
     */
    private class pawnButtonsListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if(e.getSource() == view.getYELLOW_PAWNS()[0]){
                HandleCards(1, player1_r);
            } else if(e.getSource() == view.getYELLOW_PAWNS()[1]){
                HandleCards(2, player1_r);
            } else if(e.getSource() == view.getRED_PAWNS()[0]){
                HandleCards(1, player2_y);
            } else if(e.getSource() == view.getRED_PAWNS()[1]){
                HandleCards(2, player2_y);
            }
        }

        private void HandleCards(int index, Player opponent){
            Card currentCard = board.getCurrentCard();
            if (currentCard instanceof NumberSevenCard card) {
                int oppIndex;
                if(index==1) oppIndex = 2; else oppIndex = 1;

                if (!card.cardIsPlayed()) {
                    String[] moves = {"MOVE PAWN" + index + " BY 7 STEPS", "MOVE PAWN" + index + " BY 6 AND PAWN" + oppIndex + " BY 1 STEPS",
                            "MOVE PAWN" + index + " BY 5 AND PAWN" + oppIndex + " BY 2 STEPS", "MOVE PAWN" + index + " BY 4 AND PAWN" + oppIndex + " BY 3 STEPS",
                            "MOVE PAWN" + index + " BY 3 AND PAWN" + oppIndex + " BY 4 STEPS", "MOVE PAWN" + index + " BY 2 AND PAWN" + oppIndex + " BY 5 STEPS",
                            "MOVE PAWN" + index + " BY 1 AND PAWN" + oppIndex + " BY 6 STEPS"};
                    String selectedMove = (String) JOptionPane.showInputDialog(view, "CHOOSE MOVE!\n", "SEVEN CARD",
                            JOptionPane.PLAIN_MESSAGE, null, moves, "");

                    if (selectedMove != null) {
                        int[] moveOptions = {7, 6, 5, 4, 3, 2, 1};
                        int moveIndex = Arrays.asList(moves).indexOf(selectedMove);

                        if(selectedMove.equals(moves[0])){
                            if (card.canMove(PlayerPlaying.getPawn(index), board)) {
                                card.executeMove(PlayerPlaying.getPawn(index), board);
                                view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                                card.cancelCard();

                                if (GameIsFinished()) {
                                    printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                                    return;
                                }
                                swapTurns();
                            } else {
                                JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                            }
                        }
                        else{
                            if (card.canMove(PlayerPlaying.getPawn(index), moveOptions[moveIndex], PlayerPlaying.getPawn(oppIndex), 7 - moveOptions[moveIndex], board)) {
                                card.executeMove(PlayerPlaying.getPawn(index), moveOptions[moveIndex], PlayerPlaying.getPawn(oppIndex), 7 - moveOptions[moveIndex], board);
                                view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                                card.cancelCard();

                                if (GameIsFinished()) {
                                    printVictoryWindow("The winner is " + PlayerPlaying + "\n");
                                    return;
                                }
                                swapTurns();
                            } else {
                                JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                            }
                        }
                    }
                }
            }
            else if(currentCard instanceof NumberTwoCard card){
                if (!card.cardIsPlayed() && card.canMove(PlayerPlaying.getPawn(index), board)) {

                    card.executeMove(PlayerPlaying.getPawn(index), board);
                    view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                    card.cancelCard();

                    if (GameIsFinished()) {
                        printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                }
            }
            else if (currentCard instanceof NumberTenCard card) {
                if (!card.cardIsPlayed()) {
                    String[] moves = {"MOVE FRONT 10 STEPS", "MOVE BACK 1 STEP"};
                    String selectedMove = (String) JOptionPane.showInputDialog(view, "CHOOSE MOVE!\n", "TEN CARD", JOptionPane.PLAIN_MESSAGE, null, moves, "");

                    if (selectedMove.equals(moves[0]) && card.canMove(PlayerPlaying.getPawn(index), board)) {
                        card.executeMove(PlayerPlaying.getPawn(index), board);
                        card.cancelCard();
                    } else if (selectedMove.equals(moves[1]) && card.canMoveBackwards(PlayerPlaying.getPawn(index), board)) {
                        card.movePawnBackwards(PlayerPlaying.getPawn(index), board);
                        card.cancelCard();
                    } else {
                        JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                    }
                    view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));

                    if (GameIsFinished()) {
                        printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                        return;
                    }
                    swapTurns();
                }
            }
            else if (currentCard instanceof NumberElevenCard card) {

                if (!card.cardIsPlayed()) {
                    String[] moves = {"MOVE 11 STEPS", "SWAP POSITIONS WITH PAWN1", "SWAP POSITIONS WITH PAWN2"};

                    String selectedMove = (String) JOptionPane.showInputDialog(view, "CHOOSE MOVE!\n", "ELEVEN CARD",
                            JOptionPane.PLAIN_MESSAGE, null, moves, "");


                    if (selectedMove != null) {
                        if(selectedMove.equals(moves[1]) || selectedMove.equals(moves[2])) {
                            Pawn opponentPawn;
                            if (selectedMove.equals(moves[1])) {
                                opponentPawn = opponent.getPawn(1);
                            }
                            else {
                                opponentPawn = opponent.getPawn(2);
                            }

                            if (opponentPawn != null && card.canMove(PlayerPlaying.getPawn(index), opponentPawn, board)) {
                                card.executeMove(PlayerPlaying.getPawn(index), opponentPawn, board);
                                view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                                card.cancelCard();

                                if (GameIsFinished()) {
                                    printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                                    return;
                                }
                                swapTurns();
                            } else {
                                JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                            }
                        }
                        else if(selectedMove.equals(moves[0])) {
                            if (card.canMove(PlayerPlaying.getPawn(index), board)){
                                card.executeMove(PlayerPlaying.getPawn(index), board);
                                view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                                card.cancelCard();
                                if (GameIsFinished()) {
                                    printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                                    return;
                                }
                                swapTurns();
                            } else {
                                JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                            }
                        }
                    }
                }
            }
            else if(currentCard instanceof SorryCard card){

                if (!card.cardIsPlayed()) {
                    String[] moves = {"SWAP WITH PAWN 1", "SWAP WITH PAWN 2"};
                    String selectedMove = (String) JOptionPane.showInputDialog(view, "CHOOSE MOVE!\n", "SORRY CARD",
                            JOptionPane.PLAIN_MESSAGE, null, moves, "");

                    if (selectedMove != null) {
                        Pawn opponentPawn;
                        if (selectedMove.equals(moves[0])) {
                            opponentPawn = opponent.getPawn(1);
                        }
                        else {
                            opponentPawn = opponent.getPawn(2);
                        }

                        if (card.canMove(PlayerPlaying.getPawn(index), opponentPawn, board)) {
                            card.executeMove(PlayerPlaying.getPawn(index), opponentPawn, board);
                            view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                            card.cancelCard();

                            if (GameIsFinished()) {
                                printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                                return;
                            }
                            swapTurns();
                        } else {
                            JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                        }
                    }
                }

            }
            else {
                NumberCard card = (NumberCard) currentCard;
                if (!card.cardIsPlayed() && card.canMove(PlayerPlaying.getPawn(index), board)) {

                    card.executeMove(PlayerPlaying.getPawn(index), board);
                    view.updatePawn(player1_r.getPawn(1), player1_r.getPawn(2), player2_y.getPawn(1), player2_y.getPawn(2));
                    card.cancelCard();

                    if (GameIsFinished()) {
                        printVictoryWindow(PlayerPlaying + " WON THE GAME!" + "\n");
                        return;
                    }
                    swapTurns();
                } else {
                    JOptionPane.showMessageDialog(view, "\nCANT MOVE THIS PAWN.\n");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        private ImageIcon originalIcon;

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() == view.getYELLOW_PAWNS()[0] || e.getSource() == view.getYELLOW_PAWNS()[1] || e.getSource() == view.getRED_PAWNS()[0] || e.getSource() == view.getRED_PAWNS()[1]) {
                JButton button = (JButton) e.getSource();
                originalIcon = (ImageIcon) button.getIcon();
                BufferedImage image = new BufferedImage(originalIcon.getIconWidth(), originalIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics g = image.createGraphics();
                originalIcon.paintIcon(null, g, 0, 0);
                g.dispose();
                RescaleOp op = new RescaleOp(0.8f, 0, null);
                BufferedImage darkerImage = op.filter(image, null);
                button.setIcon(new ImageIcon(darkerImage));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() == view.getYELLOW_PAWNS()[0] || e.getSource() == view.getYELLOW_PAWNS()[1] || e.getSource() == view.getRED_PAWNS()[0] || e.getSource() == view.getRED_PAWNS()[1]) {
                JButton button = (JButton) e.getSource();
                button.setIcon(originalIcon);
            }
        }
    }

    /**
     * <b>Observer:</b> This method prints the victory window.
     * @param string The string that will be printed.
     *
     */
    private void printVictoryWindow(String string) {
        JOptionPane.showMessageDialog(view, string);
    }

    private boolean GameIsFinished() {
        return PlayerPlaying.isWinner();
    }

    public static void NewGame() {
        Controller controller = new Controller();
        controller.initGame();
    }

}
