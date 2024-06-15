package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.Square;

import java.io.Serializable;

/**
 * <b>NumberTwoCard:</b> The NumberTwoCard class represents a Number Two Card. Extends the NumberCard class.
 * and has the ability to move the Pawn from the start (2 spaces). Player that draws the card has to play again.
 * @version 1.0
 * @author CSD5254
 */

public class NumberTwoCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Number Two Card.
     * @pre-condition  None.
     * @post-condition  Creates a new Number Two Card.
     */
    public NumberTwoCard() {
        setNumber(2);
    }


    /**
     * <b>Transformer:</b> Moves the card 2 spaces forward, can Move from the Start.
     * @pre-condition  The Pawn must be able to make the move and Can also Move from the Start.
     * @post-condition  Moves the pawn 2 space forward, clockwise.
     * @param pawn The pawn that will be moved.
     * @param table The deck that the card belongs to.
     */
    public void executeMove(Pawn pawn, Deck table) {
        if (pawn.getPosition() == 72 || pawn.getPosition() == 73) {
            int targetPosition = (pawn.getPosition() == 72) ? 4 : 34;
            Square targetSquare = table.getBoard().get(targetPosition);

            if (targetSquare.isOccupied()) {
                sendToStartSquare(targetSquare.getIsOccupied(), table);
            }

            pawn.setPosition(targetPosition);
            pawn.setActive();
            targetSquare.setOccupied(pawn);
        } else {
            super.executeMove(pawn, table);
        }
    }

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved.
     * @pre-condition  The Pawn must be initialized.
     * @post-condition  Checks if the Pawn can be moved.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     * @return True if the Pawn can be moved, false otherwise.
     */
    public boolean canMove(Pawn pawn, Deck table) {
        if (pawn.getPosition() == 72) {
            return isMoveAllowed(table.getBoard().get(4), PAWNS_COLOR.RED);
        } else if (pawn.getPosition() == 73) {
            return isMoveAllowed(table.getBoard().get(34), PAWNS_COLOR.YELLOW);
        }

        return super.canMove(pawn, table);
    }

    /**
     * <b>Observer:</b> Checks if the move is allowed.
     * @pre-condition  The Square must be initialized.
     * @post-condition  Checks if the move is allowed.
     * @param targetSquare The square that will be checked.
     * @param colorToCheck The color of the Pawn that will be checked.
     * @return True if the move is allowed, false otherwise.
     */
    private boolean isMoveAllowed(Square targetSquare, PAWNS_COLOR colorToCheck) {
        if (targetSquare.isOccupied()) {
            return targetSquare.getIsOccupied().getColor() != colorToCheck;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }

}
