package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.HomeSquare;
import Model.Square.SafetyZoneSquare;
import Model.Square.Square;
import Model.Square.StartSquare;

import java.io.Serializable;

/**
 * <b>Sorry Card:</b> Public Class for the Sorry Card. Extends the Card and has the ability
 * to Swap the position of 1 of the pawns in the Start with another player's pawn.
 * This card can move from the start.
 * @version 1.0
 * @author CSD5254
 */
public class SorryCard extends Card implements Serializable {

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition  None.
     * @post-condition  Creates a new Sorry card.
     * @param pawn The pawn that will be moved.
     * @param oppPawn The Opponents Pawn that will be swapped.
     * @param deck The deck of the game.
     * @return True if the Pawn can be moved, false otherwise.
     */
    public boolean canMove(Pawn pawn, Pawn oppPawn, Deck deck) {
        Square currentSquare = deck.getBoard().get(pawn.getPosition());
        Square opponentSquare = deck.getBoard().get(oppPawn.getPosition());

        if (currentSquare instanceof StartSquare) {
            return !(opponentSquare instanceof StartSquare || opponentSquare instanceof SafetyZoneSquare || opponentSquare instanceof HomeSquare);
        }

        return false;
    }

    /**
     * <b>Transformer:</b> Moves the card.
     * @pre-condition The Opponents Pawn must NOT be in the Safe zone. The player must have a Pawn in the Start.
     * @post-condition Puts the Pawn in the position of the opponent's Pawn and the opponent's Pawn in the Start.
     * @param pawn The Pawn of the player.
     * @param opponent The Pawn of the opponent.
     * @param deck  The deck of the game.
     */
    public void executeMove(Pawn pawn, Pawn opponent, Deck deck) {
        pawn.setActive();
        pawn.setPosition(opponent.getPosition());
        sendToStartSquare(opponent, deck);
        handleSlides(pawn, deck, pawn.getPosition());

        deck.getBoard().get(pawn.getPosition()).setOccupied(pawn);
    }

    /**
     * <b>Transformer:</b> Handles the Slides of the Pawn.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Moves the Pawn to the Start Square.
     * @param pawn The Pawn that will be moved.
     * @param table The deck that the card belongs to.
     */
    private void handleSlides(Pawn pawn, Deck table, int originalPosition) {
        if(pawn.getColor() == PAWNS_COLOR.RED) {
            handleRedSlides(pawn, table, originalPosition);
        } else {
            handleYellowSlides(pawn, table, originalPosition);
        }
    }

    /**
     * <b>Transformer:</b> Handles the Yellow Slides of the Pawn.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Moves the Pawn to the Start Square.
     * @param pawn The Pawn that will be moved.
     * @param table The deck that the card belongs to.
     * @param originalPosition The original position of the Pawn.
     */
    private void handleYellowSlides(Pawn pawn, Deck table, int originalPosition) {
        switch (originalPosition) {
            case 17, 46, 1 -> {
                pawn.setPosition(pawn.getPosition() + 3);
                for (int i = 1; i <= 3; i++) {
                    if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                        sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                    }
                }
                System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
                table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
            }
            case 25, 54, 9 -> {
                pawn.setPosition(pawn.getPosition() + 4);
                for (int i = 1; i <= 4; i++) {
                    if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                        sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                    }
                }
                System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
                table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
            }
        }
    }

    /**
     * <b>Transformer:</b> Handles the Red Slides of the Pawn.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Moves the Pawn to the Start Square.
     * @param pawn The Pawn that will be moved.
     * @param table The deck that the card belongs to.
     * @param originalPosition The original position of the Pawn.
     */
    private void handleRedSlides(Pawn pawn, Deck table, int originalPosition) {
        switch (originalPosition) {
            case 17, 31, 46 -> {
                pawn.setPosition(pawn.getPosition() + 3);
                for (int i = 1; i <= 3; i++) {
                    if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                        sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                    }
                }
                System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
                table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
            }
            case 25, 39, 54 -> {
                pawn.setPosition(pawn.getPosition() + 4);
                for (int i = 1; i <= 4; i++) {
                    if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                        sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                    }
                }
                System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
                table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
            }
        }
    }

    public void executeMove(Pawn pawn, Deck deck) {}
    public boolean canMove(Pawn pawn, Deck deck) {
        return false;
    }

    @Override
    public String toString() {
        return "Special Card: " + "{ SorryCard }";
    }
}

