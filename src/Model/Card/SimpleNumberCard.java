package Model.Card;

import java.io.Serializable;


/**
 * Contains the methods that all simple cards must have (3,5,8,12)
 * All simple cards move the Pawn by the value of the card.
 * In cards 8, 12 you have the option to draw another card instead of moving the pawn.
 * @version 1.0
 * @since 1.0
 */
public class SimpleNumberCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Simple Number Card with the given number.
     * @pre-condition None.
     * @post-condition Creates a new Simple Number Card with the given number.
     * @param number The number of the card.
     */
    public SimpleNumberCard(int number) {
        setNumber(number);
    }

}
