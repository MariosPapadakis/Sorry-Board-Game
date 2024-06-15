package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.Square;

import java.awt.*;
import java.io.Serializable;

/**
 * <b>Number One Card:</b> Public Class for the Number One Card. Extends the Number Card and has the ability
 * to move the Pawn 1 space forward. And can also Move from the Start.
 * @version 1.0
 * @author CSD5254
 */

public class NumberOneCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Number 1 card.
     * @pre-condition  None.
     * @post-condition  Creates a new Number 1 card.
     */
    public NumberOneCard() {
        setNumber(1);
    }

    /**
     * <b>Transformer:</b> Moves the Pawn 1 space forward, clockwise.
     * @pre-condition  The Pawn must be able to make the move and Can also Move from the Start.
     * @post-condition  Moves the pawn 1 space forward, clockwise.
     * @param pawn The pawn that will be moved.
     */
    public void executeMove(Pawn pawn, Deck table) {
        int newPosition;

        if (pawn.getPosition() == 72) {
            newPosition = 4;
        } else if (pawn.getPosition() == 73) {
            newPosition = 34;
        } else {
            super.executeMove(pawn, table);
            return;
        }

        if (table.getBoard().get(newPosition).isOccupied()) {
            sendToStartSquare(table.getBoard().get(newPosition).getIsOccupied(), table);
        }

        pawn.setPosition(newPosition);
        pawn.setActive();
        table.getBoard().get(newPosition).setOccupied(pawn);
    }

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Returns true if the Pawn can be moved by using the card, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     * @return True if the Pawn can be moved by using the card, false otherwise.
     */
    public boolean canMove(Pawn pawn, Deck table) {
        if (pawn.getPosition() == 72) {
            return checkSquare(table.getBoard().get(4), PAWNS_COLOR.RED);
        } else if (pawn.getPosition() == 73) {
            return checkSquare(table.getBoard().get(34), PAWNS_COLOR.YELLOW);
        }

        return super.canMove(pawn, table);
    }

    /**
     * <b>Observer:</b> Checks if there is a Pawn in the Square that is the same color as the Pawn that will be moved.
     * @pre-condition  The Square must be initialized.
     * @post-condition  Returns true if there is a Pawn in the Square that is the same color as the Pawn that will be moved, false otherwise.
     * @param square The Square that will be checked.
     * @param color The color of the Pawn that will be moved.
     * @return  True if there is a Pawn in the Square that is the same color as the Pawn that will be moved, false otherwise.
     */
    private boolean checkSquare(Square square, PAWNS_COLOR color) {
        if (square.isOccupied() && square.getIsOccupied().getColor() == color) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }

}
