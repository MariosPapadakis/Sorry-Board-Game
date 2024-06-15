package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.StartSquare;

import java.io.Serializable;

/*
* Transformer: Is a Setter.
* Observer: Is a Getter.
*
* */

public class NumberFourCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Number Four Card with the given number.
     * @pre-condition  None.
     * @post-condition  Creates a new Number Four Card.
     */
    public NumberFourCard() {
        setNumber(4);
    }

    /**
     * <b>Transformer:</b> Moves the Pawn 4 spaces backwards.
     * @pre-condition  The Pawn must be able to make the move.
     * @pre-condition  Moves the pawn 4 spaces backwards.
     * @param pawn The pawn that will be moved.
     * @param table The deck that the card belongs to.
     */
    public void executeMove(Pawn pawn, Deck table) {
        int currentPosition = pawn.getPosition();
        table.getBoard().get(currentPosition).setOccupied(null);

        if (currentPosition >= 60 && currentPosition < 65) { // red pawn safety zone
            if (currentPosition == 60) {
                pawn.setActive();
                pawn.setPosition(59);
                table.getBoard().get(59).setOccupied(pawn);
            } else if (currentPosition == 64) {
                pawn.setPosition(currentPosition - getNumber());
                table.getBoard().get(60).setOccupied(pawn);
            } else {
                pawn.setActive();
                pawn.setPosition(currentPosition - getNumber() - 57);
                table.getBoard().get(currentPosition - getNumber() - 57).setOccupied(pawn);
            }
            super.handleSlides(pawn, table, pawn.getPosition());
            return;
        }

        if (currentPosition >= 66 && currentPosition < 71) { // yellow pawn safety zone
            if (currentPosition == 70) {
                pawn.setPosition(currentPosition - getNumber());
                table.getBoard().get(66).setOccupied(pawn);
            } else {
                pawn.setActive();
                pawn.setPosition(currentPosition - getNumber() - 33);
                table.getBoard().get(currentPosition - getNumber() - 33).setOccupied(pawn);
            }
            super.handleSlides(pawn, table, pawn.getPosition());
            return;
        }

        if (currentPosition <= 3) { // simple square with position 0, 1, 2, or 3
            pawn.setPosition(currentPosition - getNumber() + 60);
            table.getBoard().get(currentPosition - getNumber() + 60).setOccupied(pawn);
            super.handleSlides(pawn, table, pawn.getPosition());
            return;
        }

        pawn.setPosition(currentPosition - getNumber());
        table.getBoard().get(currentPosition - getNumber()).setOccupied(pawn);
        super.handleSlides(pawn, table, pawn.getPosition());
    }

    /**
     * <b>Observer:</b> Checks if the Pawn can be moved by using the card.
     * @pre-condition  The Pawn must be initialized.
     * @post-condition  Returns true if the Pawn can be moved by using the card, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     * @return True if the Pawn can be moved by using the card, false otherwise.
     */
    public boolean canMove(Pawn pawn, Deck table) {
        if (pawn.isAtHome()) {
            return false; // reached home
        }

        if (!pawn.isActive() && table.getBoard().get(pawn.getPosition()) instanceof StartSquare) {
            return false; // on start square
        }

        if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn safety zone
            if (pawn.getPosition() == 60) {
                return table.getBoard().get(59).isOccupied() && table.getBoard().get(59).getIsOccupied().getColor() == PAWNS_COLOR.YELLOW;
            } else if (pawn.getPosition() == 64) {
                return !table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied();
            } else {
                return (!table.getBoard().get(pawn.getPosition() - getNumber() - 57).isOccupied() || (table.getBoard().get(pawn.getPosition() - getNumber() - 57).isOccupied() &&
                        table.getBoard().get(pawn.getPosition() - getNumber() - 57).getIsOccupied().getColor() == PAWNS_COLOR.YELLOW));
            }
        }

        if (pawn.getPosition() >= 66) { // yellow pawn safety zone
            if (pawn.getPosition() == 70) {
                return !table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied();
            } else {
                return (!table.getBoard().get(pawn.getPosition() - getNumber() - 33).isOccupied()) || (table.getBoard().get(pawn.getPosition() - getNumber() - 33).isOccupied() &&
                        table.getBoard().get(pawn.getPosition() - getNumber() - 33).getIsOccupied().getColor() == PAWNS_COLOR.RED);
            }
        }

        if (pawn.getColor() == PAWNS_COLOR.RED) { // red pawn on a simple square
            if (pawn.getPosition() <= 3) {
                return !table.getBoard().get(pawn.getPosition() - getNumber() + 60).isOccupied() ||
                        table.getBoard().get(pawn.getPosition() - getNumber() + 60).getIsOccupied().getColor() != PAWNS_COLOR.RED;
            } else {
                return !table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied() || table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied() &&
                        table.getBoard().get(pawn.getPosition() - getNumber()).getIsOccupied().getColor() != PAWNS_COLOR.RED;
            }
        } else { // yellow pawn on a simple square

            if (pawn.getPosition() <= 3) {
                return (!table.getBoard().get(pawn.getPosition() - getNumber() + 60).isOccupied() || (table.getBoard().get(pawn.getPosition() - getNumber() + 60).isOccupied() &&
                        table.getBoard().get(pawn.getPosition() - getNumber() + 60).getIsOccupied().getColor() != PAWNS_COLOR.YELLOW));
            } else {
                return !table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied() || (table.getBoard().get(pawn.getPosition() - getNumber()).isOccupied() && table.getBoard().get(pawn.getPosition() - getNumber()).getIsOccupied().getColor() != PAWNS_COLOR.YELLOW);
            }
        }
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }

}
