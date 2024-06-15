package Model.Card;

import Model.Deck;
import Model.Pawn;
import Model.Square.HomeSquare;
import Model.Square.SafetyZoneSquare;
import Model.Square.Square;
import Model.Square.StartSquare;

import java.io.Serializable;


/**
 * <b>Number Eleven Card:</b> Public Class for the Number Eleven Card. Extends the Number Card and has the ability
 * to move the Pawn 11 spaces clockwise. OR it can swap the position of 1 of the pawns with another player's pawn.
 * This card can NOT move from the start.
 * @version 1.0
 * @author CSD5254
 */
public class NumberElevenCard extends NumberCard implements Serializable {


    /**
     * <b>Constructor:</b> Creates a new Number 11 card.
     * @pre-condition  None.
     * @post-condition  Creates a new Number 11 card.
     */
    public NumberElevenCard() {
        setNumber(11);
    }

    /**
     * <b>Transformer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition  The Pawn must be able to make the move.
     * @post-condition  Exchanges the position of the Pawn with the opponent's Pawn.
     * @param pawn The pawn that will be moved.
     * @param table The deck that the card belongs to.
     */
    public void executeMove(Pawn pawn, Pawn opponentPawn, Deck table) {
        swapPositions(pawn, opponentPawn, table);
        super.handleSlides(pawn, table, pawn.getPosition());
    }

    /**
     * <b>Transformer:</b> Responsible for swapping the positions of the two pawns.
     * @pre-condition  The Pawn must be able to make the move.
     * @post-condition  Exchanges the position of the Pawn with the opponent's Pawn.
     * @param pawn1 The pawn that will be swapped.
     * @param pawn2 The Opponents Pawn that will be swapped.
     * @param table The table of the game.
     */
    private void swapPositions(Pawn pawn1, Pawn pawn2, Deck table) {
        int tmpPosition = pawn1.getPosition();
        pawn1.setPosition(pawn2.getPosition());
        pawn2.setPosition(tmpPosition);
        table.getBoard().get(pawn1.getPosition()).setOccupied(pawn1);
        table.getBoard().get(pawn2.getPosition()).setOccupied(pawn2);
    }

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition  The Pawn must be initialized.
     * @post-condition  Returns true if the Pawn can be moved by using the card, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param opponentPawn The Opponents Pawn that will be moved.
     * @param table The deck that the card belongs to.
     * @return True if the Pawn can be moved by using the card, false otherwise.
     */
    public boolean canMove(Pawn pawn, Pawn opponentPawn, Deck table) {
        return !isRestrictedSquare(table.getBoard().get(pawn.getPosition())) &&
                !isRestrictedSquare(table.getBoard().get(opponentPawn.getPosition()));
    }

    /**
     * <b>Observer:</b> Checks if the Square is a Start, Home or Safety Zone Square.
     * @pre-condition  The Square must be initialized.
     * @post-condition  Returns true if the Square is a Start, Home or Safety Zone Square, false otherwise.
     * @param square The Square that will be checked.
     * @return True if the Square is a Start, Home or Safety Zone Square, false otherwise.
     */
    private boolean isRestrictedSquare(Square square) {
        return square instanceof StartSquare || square instanceof HomeSquare || square instanceof SafetyZoneSquare;
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }

}

