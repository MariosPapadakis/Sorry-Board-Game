package Model;

import Model.Card.*;
import Model.Square.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <b>Public Class:</b> that initializes the Deck of the game.
 * <br><b>Responsibility:</b> Instantiate the Deck of Cards, Pawns and the Board. Will also shuffle the cards, draw the top card and move the pawns.
 * <br><b>Superclass:</b> None
 * <br><b>Subclasses:</b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public class Deck implements Serializable {

    /**
     * <b>Variable:</b> Stores the top card of the deck.
     */
    private Card currentCard;

    /**
     * <b>Variable:</b> Stores the squares of the board.
     * The ArrayList holds Square objects representing each square in the board.
     */
    private ArrayList<Square> squares;

    /**
     * <b>Variable:</b> Stores the game cards in the deck.
     * The ArrayList holds Card objects representing each card in the game deck.
     */
    ArrayList<Card> gameCards = new ArrayList<>();

    /**
     * <b>Variable:</b> Stores the number of cards left in the deck.
     */
    int cardsLeft;

    /**
     * <b>Getter:</b> Returns the squares of the board.
     * @pre-condition The squares must be instantiated.
     * @post-condition Returns the squares of the board.
     * @return The squares of the board.
     */
    public ArrayList<Square> getBoard() {
        return squares;
    }

    /**
     * <b>Transformer:</b> Initializes the Cards and the Board.
     * @pre-condition  None.
     * @post-condition  Creates a new Deck.
     */
    public void initDeck() {
        initCards();
        initBoard();
    }

    /**
     * <b>Getter:</b> Returns the cards of the game.
     * @return The cards of the game.
     */
    public ArrayList<Card> getGameCards() {
        return gameCards;
    }

    /**
     * <b>Transformer:</b> Initializes the cards of the game.
     * @pre-condition None.
     * @post-condition Initializes the cards of the game.
     */
    public void initCards() {
        gameCards = new ArrayList<>();

        String[] cardImages = {
                
                "src/images/cards/card1.png",
                "src/images/cards/card2.png",
                "src/images/cards/card3.png",
                "src/images/cards/card4.png",
                "src/images/cards/card5.png",
                "src/images/cards/card7.png",
                "src/images/cards/card8.png",
                "src/images/cards/card10.png",
                "src/images/cards/card11.png",
                "src/images/cards/card12.png",
                "src/images/cards/cardSorry.png"
        };

        //Set the image Path to every card


        int[] cardNumbers = {1, 2, 3, 4, 5, 7, 8, 10, 11, 12, -1}; // -1 represents SorryCard

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cardNumbers.length; j++) {
                Card card = getCard(cardNumbers, j, cardImages);
                gameCards.add(card);
            }
        }

        cardsLeft = gameCards.size();
        shuffleCards();
    }

    /**
     * <b>Transformer:</b> Initializes the cards of the game.
     * @param cardNumbers The numbers of the cards.
     * @param j The index of the card in the array.
     * @param cardImages The images of the cards.
     * @return The card.
     */
    private static Card getCard(int[] cardNumbers, int j, String[] cardImages) {
        Card card = switch (cardNumbers[j]) {
            case 1 -> new NumberOneCard();
            case 2 -> new NumberTwoCard();
            case 3 -> new SimpleNumberCard(3);
            case 4 -> new NumberFourCard();
            case 5 -> new SimpleNumberCard(5);
            case 7 -> new NumberSevenCard();
            case 8 -> new SimpleNumberCard(8);
            case 10 -> new NumberTenCard();
            case 11 -> new NumberElevenCard();
            case 12 -> new SimpleNumberCard(12);
            default -> new SorryCard();
        };
        card.setImage(cardImages[j]);
        card.setImagePath(cardImages[j]);
        return card;
    }

    /**
     * <b>Transformer:</b> Initializes the squares of each player.
     * @pre-condition  None.
     * @post-condition  Initializes the squares of each player.
     */
    public void initBoard() {
        squares = new ArrayList<>();
        int position = 0;
        for (int i = 0; i < 4; i++) {
            Color color = switch (i) {
                case 0 -> Color.RED;
                case 1 -> Color.BLUE;
                case 2 -> Color.YELLOW;
                case 3 -> Color.GREEN;
                default -> null;
            };
            squares.add(new SimpleSquare(position, Color.WHITE));
            position++;
            squares.add(new StartSlideSquare(position, color));
            position++;
            squares.add(new InternalSlideSquare(position, color));
            position++;
            squares.add(new InternalSlideSquare(position, color));
            position++;
            squares.add(new EndSlideSquare(position, color));
            position++;
            for(int j=0; j<4;j++){
                squares.add(new SimpleSquare(position, Color.WHITE));
                position++;
            }
            squares.add(new StartSlideSquare(position, color));
            position++;
            for(int j=0; j<3;j++) {
                squares.add(new InternalSlideSquare(position, color));
                position++;
            }
            squares.add(new EndSlideSquare(position, color));
            position++;
            squares.add(new SimpleSquare(position, Color.WHITE));
            position++;
        }

        for(int i=0; i<2; i++){
            Color color;
            if(i==0){
                color = Color.RED;
            }else{
                color = Color.YELLOW;
            }
            squares.add(new SafetyZoneSquare(position, color));
            position++;
            squares.add(new SafetyZoneSquare(position, color));
            position++;
            squares.add(new SafetyZoneSquare(position, color));
            position++;
            squares.add(new SafetyZoneSquare(position, color));
            position++;
            squares.add(new SafetyZoneSquare(position, color));
            position++;
            squares.add(new HomeSquare(position, color));
            position++;
        }
        squares.add(new StartSquare(position, Color.RED));
        position++;
        squares.add(new StartSquare(position, Color.YELLOW));
    }

    /**
     * <b>Transformer:</b> Draws the top card of the deck.
     * @pre-condition  The Deck of cards must be instantiated.
     * @post-condition  Draws the top card of the deck.
     */
    public void drawCard() {
        if (cardsLeft == 0) shuffleCards();
        currentCard = gameCards.get(--cardsLeft);
    }

    /**
     * <b>Transformer:</b> Shuffles the cards of the deck.
     * @pre-condition The Deck of cards must be instantiated.
     * @post-condition Shuffles the cards of the deck.
     */
    public void shuffleCards() {
        for (int i = cardsLeft; i < gameCards.size(); i++) {
            gameCards.get(i).activateCard();
        }
        cardsLeft = gameCards.size();
        Collections.shuffle(gameCards);
    }

    /**
     * <b>Observer:</b> Returns the current card.
     * @return The current card.
     */
    public Card getCurrentCard(){
        return currentCard;
    }

    /**
     * <b>Observer:</b> Returns the number of cards left in the deck.
     * @return The number of cards left in the deck.
     */
    public int getCardsRemaining(){
        return cardsLeft;
    }
}
