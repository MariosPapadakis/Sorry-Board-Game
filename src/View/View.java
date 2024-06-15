package View;

import Model.Card.Card;
import Model.Pawn;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;


public class View extends JFrame implements Serializable {
    private JMenuBar menuBar;
    private JButton currentCardButton;
    private JButton FoldButton;
    private JButton deckOfCards;
    private JLayeredPane mainPanel;
    private JLabel[] squares;
    private JLabel boardBackground;
    private JLabel tableBackground;
    private JTextArea outputBox;
    private JButton[] YELLOW_PAWNS;
    private JButton[] RED_PAWNS;

    /**
     * <b>Constructor: </b>Constructs most Graphical Elements of the game
     */
    public View(){
        initComponents();
        this.setSize(994, 730);
        this.setLayout(null);
        this.setLayeredPane(mainPanel);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * <b>Transformer: </b>Initializes the components of the game
     * @pre-condition None
     * @post-condition The components of the game are initialized
     */
    public void initComponents(){
        mainPanel = new JLayeredPane();
        initBackground();
        setInfoBox();
        setMenu();
        initTableBackground();
        createFoldButton();
        BoardSquares();
        createDeckOfCards();
        initCurrentCardButton();
        initPawns();

        mainPanel.add(boardBackground);
        mainPanel.add(FoldButton, 0);
        mainPanel.add(deckOfCards, 0);
        mainPanel.add(currentCardButton, 0);
        mainPanel.add(tableBackground, 0);
        for (JLabel square : squares) {
            mainPanel.add(square, 0);
        }
        for (JButton YELLOW_PAWN : YELLOW_PAWNS) {
            mainPanel.add(YELLOW_PAWN, 0);
        }
        for (JButton RED_PAWN : RED_PAWNS) {
            mainPanel.add(RED_PAWN, 0);
        }
        mainPanel.add(outputBox, 0);
    }

    /**
     * <b>Transformer: </b>Initializes the pawns of the game
     * @pre-condition None
     * @post-condition The pawns of the game are initialized
     */
    private void initPawns() {
        YELLOW_PAWNS = new JButton[2];
        RED_PAWNS = new JButton[2];

        YELLOW_PAWNS[0] = new JButton(new ImageIcon(new ImageIcon("src/images/pawns/yellowPawn1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        YELLOW_PAWNS[0].setBounds(440,590,40,40);
        YELLOW_PAWNS[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        YELLOW_PAWNS[1] = new JButton(new ImageIcon(new ImageIcon("src/images/pawns/yellowPawn2.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        YELLOW_PAWNS[1].setBounds(485,590,40,40);
        YELLOW_PAWNS[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        RED_PAWNS[0] = new JButton(new ImageIcon(new ImageIcon("src/images/pawns/redPawn1.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        RED_PAWNS[0].setBounds(166,89,40,40);
        RED_PAWNS[0].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        RED_PAWNS[1] = new JButton(new ImageIcon(new ImageIcon("src/images/pawns/redPawn2.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH)));
        RED_PAWNS[1].setBounds(210,89,40,40);
        RED_PAWNS[1].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        for(JButton YELLOW_PAWN : YELLOW_PAWNS)
            YELLOW_PAWN.setCursor(new Cursor(Cursor.HAND_CURSOR));

        for(JButton RED_PAWN : RED_PAWNS)
            RED_PAWN.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JButton[] getYELLOW_PAWNS() {
        return YELLOW_PAWNS;
    }

    public JButton[] getRED_PAWNS() {
        return RED_PAWNS;
    }

    /**
     * <b>Transformer: </b> Sends the pawn to the start
     * @pre-condition None
     * @post-condition The pawn is sent to the start
     * @param pawn
     */
    public void sendToStart(JButton pawn){
        if (pawn.equals(RED_PAWNS[0])) {
            setPawnBounds(RED_PAWNS[0], 166, 89);
        } else if (pawn.equals(RED_PAWNS[1])) {
            setPawnBounds(RED_PAWNS[1], 210, 89);
        } else if (pawn.equals(YELLOW_PAWNS[0])) {
            setPawnBounds(YELLOW_PAWNS[0], 440, 590);
        } else if (pawn.equals(YELLOW_PAWNS[1])) {
            setPawnBounds(YELLOW_PAWNS[1], 485, 590);
        }
    }

    /**
     * <b>Transformer: </b> Sends the pawn to the home
     * @pre-condition None
     * @post-condition The pawn is sent to the home
     * @param pawn
     */
    public void sendToHome(JButton pawn){
        if (pawn.equals(RED_PAWNS[0])) {
            setPawnBounds(RED_PAWNS[0], 86, 289);
        } else if (pawn.equals(RED_PAWNS[1])) {
            setPawnBounds(RED_PAWNS[1], 130, 289);
        } else if (pawn.equals(YELLOW_PAWNS[0])) {
            setPawnBounds(YELLOW_PAWNS[0], 518, 379);
        } else if (pawn.equals(YELLOW_PAWNS[1])) {
            setPawnBounds(YELLOW_PAWNS[1], 562, 379);
        }
    }

    /**
     * <b>Transformer: </b> Sets the bounds of the pawn
     * @pre-condition None
     * @post-condition The bounds of the pawn are set
     * @param pawn The pawn
     * @param x The x coordinate
     * @param y The y coordinate
     */
    private void setPawnBounds(JButton pawn, int x, int y) {
        pawn.setBounds(x, y, 40, 40);
    }

    /**
     * <b>Transformer: </b> Updates the pawns
     * @pre-condition None
     * @post-condition The pawns are updated
     * @param red1 The first red pawn
     * @param red2 The second red pawn
     * @param yellow1 The first yellow pawn
     * @param yellow2 The second yellow pawn
     */
    public void updatePawn(Pawn red1, Pawn red2, Pawn yellow1, Pawn yellow2) {
        updateSinglePawn(red1, RED_PAWNS[0]);
        updateSinglePawn(red2, RED_PAWNS[1]);
        updateSinglePawn(yellow1, YELLOW_PAWNS[0]);
        updateSinglePawn(yellow2, YELLOW_PAWNS[1]);
        mainPanel.repaint();
    }

    /**
     * <b>Transformer: </b> Updates a single pawn
     * @pre-condition None
     * @post-condition The pawn is updated
     * @param pawn The pawn
     * @param pawnButton The pawn button
     */
    private void updateSinglePawn(Pawn pawn, JButton pawnButton) {
        if (pawn.getPosition() == 72 || pawn.getPosition() == 73) {
            sendToStart(pawnButton);
        } else if (pawn.getPosition() == 65 || pawn.getPosition() == 71) {
            sendToHome(pawnButton);
        } else {
            int x = squares[pawn.getPosition()].getX();
            int y = squares[pawn.getPosition()].getY();
            pawnButton.setLocation(x, y);
        }
    }

    /**
     * <b>Transformer: </b> Creates the current card button
     */
    private void initCurrentCardButton() {
        currentCardButton = new JButton();
        currentCardButton.setBounds(826, 175, 110, 160);
        currentCardButton.setBackground(Color.WHITE);
    }

    /**
     * <b>Transformer: </b> Sets the current card button
     * @param card The card
     */
    public void repaintLastCard(Card card){
        currentCardButton.setIcon(new ImageIcon(new ImageIcon(card.getImage()).getImage().getScaledInstance(110, 160, java.awt.Image.SCALE_SMOOTH)));
        mainPanel.repaint();
    }

    /**
     * <b>Transformer: </b> Creates the deck of cards
     */
    private void createDeckOfCards() {
        deckOfCards = new JButton("");
        deckOfCards.setBounds(706, 175, 110, 160);
        deckOfCards.setHorizontalAlignment(JButton.CENTER);
        deckOfCards.setIcon(new ImageIcon(new ImageIcon("src/images/cards/backCard.png").getImage().getScaledInstance(110, 160, java.awt.Image.SCALE_SMOOTH)));

        deckOfCards.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * <b>Transformer: </b> Sets the Background.
     */
    public void initBackground(){
        ImageIcon image = new ImageIcon("src/images/background.png");
        boardBackground = new JLabel(image);
        boardBackground.setBounds(0, 21, 1000, 750);
    }

    /**
     * <b>Transformer: </b>Initializes the starting units of the game
     */
    public void initTableBackground(){
        tableBackground = new JLabel("");
        tableBackground.setBackground(Color.getHSBColor(51.0F, 16.0F, 100.0F));
        tableBackground.setBounds(60,80,560,560);
        tableBackground.setOpaque(true);

        tableBackground.setIcon(new ImageIcon(new ImageIcon("src/images/sorryImage.png").getImage().getScaledInstance(280, 80, java.awt.Image.SCALE_SMOOTH)));
        tableBackground.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * <b>Transformer: </b> Sets the info Box
     */
    public void setInfoBox(){
        outputBox = new JTextArea("Info Box");
        outputBox.setBounds(700, 450, 250, 120);
        outputBox.setEditable(false);
        outputBox.setFont(new Font("Monospace", Font.BOLD, 15));
        outputBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    }

    /**
     * <b>Observer: </b> Returns the info Box
     * @return The info Box
     */
    public JTextArea getInfoBox() {
        return outputBox;
    }

    /**
     * <b>Transformer: </b> Repaints the info Box
     * @param text The text
     */
    public void repaintInfoBox(String text) {
        outputBox.setText(text);
        mainPanel.repaint();
    }

    /**
     * <b>Transformer: </b> Creates the fold button
     */
    private void createFoldButton() {
        FoldButton = new JButton("Fold Button") {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setPaint(getBackground());
                    g2.fill(((RoundedBorder) getBorder()).getBorderShape(
                            0, 0, getWidth() - 1, getHeight() - 1));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        FoldButton.setBounds(700, 380, 250, 50);
        FoldButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        FoldButton.setBackground(Color.RED);
        FoldButton.setOpaque(false);
        FoldButton.setBorder(new RoundedBorder(40)); // 25 is the radius of the border.

        FoldButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        FoldButton.addMouseListener(new MouseAdapter() {
            final Color originalColor = FoldButton.getBackground();

            @Override
            public void mousePressed(MouseEvent e) {
                FoldButton.setBackground(originalColor.darker());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                FoldButton.setBackground(originalColor);
            }
        });
    }

    class RoundedBorder implements Border, Serializable {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2)); // Set border width to 2px
            g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }

        public Shape getBorderShape(int x, int y, int width, int height) {
            return new RoundRectangle2D.Double(x, y, width, height, radius, radius);
        }
    }

    public JButton getFoldButton() {
        return FoldButton;
    }

    public JButton getDeckOfCards() {
        return deckOfCards;
    }

    /**
     * <b>Transformer: </b> Sets the menu
     */
    public void setMenu(){
        menuBar = new JMenuBar();

        JMenu menu1 = new JMenu("New Game");
        JMenu menu2 = new JMenu("Save Game");
        JMenu menu3 = new JMenu("Continue Saved Game");
        JMenu menu4 = new JMenu("Exit Game");

        menu1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menu4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);
    }

    /**
     * <b>Transformer: </b> Initialises the Squares of the Board.
     */
    public void BoardSquares(){
        squares = new JLabel[74];

        //Top Squares
        for (int i = 0; i < 16; i++) {
            squares[i] = createSquare(20 + 40 * i, 40, getIcon(i, "red"));
        }

        //Right Squares
        for (int i = 16; i < 30; i++) {
            squares[i] = createSquare(620, 40 + 40 * (i - 15), getIcon(i - 16, "blue"));
        }

        //Bellow Squares
        for (int i = 30; i < 45; i++) {
            squares[i] = createSquare(620 - 40 * (i - 30), 640, getIcon(i - 30, "yellow"));
        }

        //Left Squares
        for (int i = 45; i < 60; i++) {
            squares[i] = createSquare(20, 640 - 40 * (i - 45), getIcon(i - 45, "green"));
        }

        //Red Safety Zone
        for (int i = 60; i < 66; i++) {
            if(i == 65){
                squares[i] = createSpecialSquare(78, 40 + 40 * (i - 59), Color.RED, "Home");
            } else {
                squares[i] = createSquare(100, 40 + 40 * (i - 59), null);
                squares[i].setBackground(Color.RED);
            }
        }

        //Yellow Safety Zone
        for (int i = 66; i < 72; i++) {
            if(i == 71){
                squares[i] = createSpecialSquare(510, 610 - 40 * (i - 65), Color.YELLOW, "Home");
            } else {
                squares[i] = createSquare(540, 640 - 40 * (i - 65), null);
                squares[i].setBackground(Color.YELLOW);
            }

        }

        // Start Squares
        squares[72] = createSpecialSquare(158, 80, Color.RED, "Start");
        squares[73] = createSpecialSquare(432, 560, Color.YELLOW, "Start");
        squares[73].setVerticalAlignment(JLabel.TOP);

        for (JLabel square : squares) {
            mainPanel.add(square, 0);
        }
    }

    /**
     * <b>Transformer: </b> Creates a square
     * @param x The x coordinate
     * @param y The y coordinate
     * @param icon The icon
     * @return The square
     */
    private JLabel createSquare(int x, int y, ImageIcon icon) {
        JLabel square = new JLabel();
        square.setBounds(x, y, 40, 40);
        square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        square.setIcon(icon);
        square.setOpaque(true);
        return square;
    }

    /**
     * <b>Transformer: </b> gets the icon
     * @param i The index
     * @param color The color
     * @return The square
     */
    private ImageIcon getIcon(int i, String color) {
        String imagePath = "src/images/slides/" + color;
        if (i == 1 || i == 9) {
            return new ImageIcon(new ImageIcon(imagePath + "SlideStart.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        } else if (i == 2 || i == 3 || i == 10 || i == 11 || i == 12) {
            return new ImageIcon(new ImageIcon(imagePath + "SlideMedium.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        } else if (i == 4 || i == 13) {
            return new ImageIcon(new ImageIcon(imagePath + "SlideEnd.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        } else {
            return null;
        }
    }

    /**
     * <b>Transformer: </b> Creates a special square
     * @param x The x coordinate
     * @param y The y coordinate
     * @param color The color
     * @param text The text
     * @return The square
     */
    private JLabel createSpecialSquare(int x, int y, Color color, String text) {
        JLabel square = new JLabel(text);
        square.setBounds(x, y, 100, 80);
        square.setBorder(BorderFactory.createLineBorder(color, 4));
        square.setBackground(Color.getHSBColor(0.0F, 0.0F, 90.2F));
        square.setFont(new Font("SansSerif", Font.BOLD, 20));
        square.setForeground(Color.BLACK); // Set text color to white
        square.setVerticalAlignment(JLabel.BOTTOM);
        square.setHorizontalAlignment(JLabel.CENTER);
        square.setOpaque(true);
        return square;
    }
}
