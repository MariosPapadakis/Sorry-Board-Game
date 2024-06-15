package Model.Card;


import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;

import java.awt.*;
import java.io.Serializable;

/**
 * Contains the methods that all the cards must have.
 * @version 1.0
 * @author CSD5254
 */
public abstract class Card implements Serializable {

    /**
     * <b>Boolean Variable:</b> True if the card is played, False otherwise.
     */
    private boolean isPlayed = false;

    /**
     * <b>String Variable:</b> Stores the Image of the card.
     */
    private String cardImage;

    /**
     * <b>String Variable:</b> Stores the path of the Image of the card.
     * <br><b>Use: </b> Used to load the Image of the card, while loading the saved game.
     */
    private String imagePath;

    /**
     * <b>Image Variable:</b> Stores the Image of the card for the Save Game feature.
     */
    private transient Image image; // transient means it won't be saved

    /**
     * <b>Transformer:</b> Sets the Path of the Image of the card.
     * <br><b>Use: </b>Used to load the Image of the card, while loading the saved game.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the Path of the Image of the card.
     * @param imagePath The path of the Image of the card.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * <b>Getter:</b> Returns the Path of the Image of the card.
     * <br><b>Use: </b>Used for the Load Game feature.
     * @pre-condition The card must be initialized.
     * @post-condition Returns the Path of the Image of the card.
     * @return The Path of the Image of the card.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * <b>Observer:</b> Checks if the card has been played.
     * @pre-condition The card must be initialized.
     * @post-condition True if the card is played, False otherwise.
     * @return True if the card is played, False otherwise.
     */
    public boolean cardIsPlayed() {
        return isPlayed;
    }

    /**
     * <b>Transformer:</b> Sets the card as played so that it can't be used again.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the card as played.
     */
    public void cancelCard(){
        isPlayed = true;
    }

    /**
     * <b>Transformer:</b> Sets the card as not played so that it can be used again.
     * <br><B>Use: </B></B>Used when shuffling the cards.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the card as not played.
     */
    public void activateCard(){
        isPlayed = false;
    }

    /**
     * <b>Observer:</b> Moves the pawn according to the card's value.
     * @pre-condition The Pawn must be able to make the move.
     * @post-condition Moves the pawn according to the card's value.
     * @param pawn The pawn that will be moved.
     * @param deck The deck that the card belongs to.
     */
    public abstract void executeMove(Pawn pawn, Deck deck);

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Returns true if the Pawn can be moved by using the card, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param deck The deck that the card belongs to.
     * @return True if the Pawn can be moved by using the card, false otherwise.
     */
    public abstract boolean canMove(Pawn pawn, Deck deck);

    /**
     * <b>Transformer:</b> Sets the Image of the card to the given one.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the Image of the card.
     * @param cardImage The Image of the card.
     */
    public void setImage(String cardImage) {
        this.cardImage = cardImage;
    }

    /**
     * <b>Observer:</b> Gets the URL of the cardImage of the card.
     * @pre-condition The card must have an cardImage.
     * @post-condition Returns the cardImage of the card.
     * @return The cardImage of the card.
     */
    public String getImage(){
        return cardImage;
    }

    /**
     * <b>Transformer:</b> Sets the Image of the card to the given one.
     * <br><b>Use: </b>Used for the Save Game feature.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the Image of the card.
     * @param image The Image of the card.
     */
    public void setActualImage(Image image) {
        this.image = image;
    }

    /**
     * <b>Transformer:</b> Sets the Position of the Pawn to the Start Square.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Sets the Position of the Pawn to the Start Square.
     * @param pawn The Pawn that will be moved.
     * @param deck The deck that the card belongs to.
     */
    public void sendToStartSquare(Pawn pawn, Deck deck) {
        int position = pawn.getPosition();
        Pawn currentPawn = deck.getBoard().get(position).getIsOccupied();

        currentPawn.setInactive();

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            currentPawn.setPosition(72);
        } else {
            currentPawn.setPosition(73);
        }

        deck.getBoard().get(position).setOccupied(null);
    }
}
