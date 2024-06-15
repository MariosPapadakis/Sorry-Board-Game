
package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.*;

import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Number Ten Card:</b> Public Class for the Number Ten Card. Extends the Number Card and has the ability
 * to move the Pawn 10 spaces clockwise. OR it can move 1 space backwards.
 * This card can NOT move from the start.
 * @version 1.0
 * @author CSD5254
 */
public class NumberTenCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Number 10 card.
     * @pre-condition None.
     * @post-condition Creates a new Number 10 card.
     */
    public NumberTenCard() {
        setNumber(10);
    }

    /**
     * <b>Transformer:</b> Moves the Pawn 1 space backwards.
     * @pre-condition The Pawn must be able to make the move.
     * @post-condition Moves the Pawn 1 space backwards.
     * @param pawn The pawn that will be moved.
     * @param table The deck that the card belongs to.
     */
    public void movePawnBackwards(Pawn pawn, Deck table) {
        int newPosition = calculateNewPosition(pawn, pawn.getPosition());

        table.getBoard().get(pawn.getPosition()).setOccupied(null);

        if (table.getBoard().get(newPosition).isOccupied()) {
            sendToStartSquare(table.getBoard().get(newPosition).getIsOccupied(), table);
        }

        pawn.setPosition(newPosition);
        super.handleSlides(pawn, table, pawn.getPosition());

        table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
    }

    /**
     * <b>Observer:</b> Calculates the new position of the Pawn.
     * @pre-condition None.
     * @post-condition Calculates the new position of the Pawn.
     * @param pawn The pawn that will be moved.
     * @param currentPosition The current position of the Pawn.
     * @return The new position of the Pawn.
     */
    private int calculateNewPosition(Pawn pawn, int currentPosition) {
        int newPosition;

        if (currentPosition == 0) {
            newPosition = currentPosition - 1 + 60;
        } else if (currentPosition == 60 || currentPosition == 66) {
            pawn.setActive();
            newPosition = currentPosition - 1 - (currentPosition == 60 ? 57 : 33);
        } else {
            newPosition = currentPosition - 1;

            if (pawn.getColor() == PAWNS_COLOR.RED && (currentPosition == 16 || currentPosition == 31 || currentPosition == 46)) {
                newPosition += 3;
            } else if (pawn.getColor() == PAWNS_COLOR.RED && (currentPosition == 24 || currentPosition == 39 || currentPosition == 54)) {
                newPosition += 4;
            }
        }
        return newPosition;
    }

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition  The Pawn must be able to make the move and Can also Move from the Start.
     * @post-condition  Moves the pawn 1 space backwards.
     * @param pawn The pawn that will be moved.
     * @param deck The deck that the card belongs to.
     */
    public boolean canMoveBackwards(Pawn pawn, Deck deck) {
        Square currentSquare = deck.getBoard().get(pawn.getPosition());

        if (currentSquare instanceof StartSquare || currentSquare instanceof HomeSquare) {
            return false;
        }

        if (currentSquare instanceof SafetyZoneSquare) {
            int offset = (pawn.getPosition() == 60) ? -57 : -33;
            Square targetSquare = deck.getBoard().get(pawn.getPosition() - 1 + offset);

            return !targetSquare.isOccupied() || (pawn.getColor() != targetSquare.getIsOccupied().getColor());
        }
        // On a simple square
        int offset = (pawn.getPosition() == 0) ? 60 : 0;
        Square targetSquare = deck.getBoard().get(pawn.getPosition() - 1 + offset);

        return !targetSquare.isOccupied() || (pawn.getColor() != targetSquare.getIsOccupied().getColor());
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }
}
