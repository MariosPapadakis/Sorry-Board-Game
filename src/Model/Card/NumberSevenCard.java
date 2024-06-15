package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.Square;
import Model.Square.StartSquare;

import java.io.Serializable;

/**
 * <b>Number Seven Card:</b> Public Class for the Number Seven Card. Extends the Number Card and has the ability
 * to move the Pawn 7 spaces clockwise. OR it can split the move between two pawns.
 * This card can NOT move backwards and can NOT move from the start.
 * @version 1.0
 * @author CSD5254
 */
public class NumberSevenCard extends NumberCard implements Serializable {

    /**
     * <b>Constructor:</b> Creates a new Number 7 card.
     * @pre-condition  None.
     * @post-condition  Creates a new Number 7 card.
     */
    public NumberSevenCard() {
        setNumber(7);
    }

    /**
     * <b>Transformer:</b> Split the move between two pawns.
     * @pre-condition  The pawns are not at home and are not on the start square.
     * @post-condition  The pawns are moved accordingly.
     * @param pawn1 The first pawn to be moved.
     * @param move1 The number of spaces to move the first pawn.
     * @param pawn2 The second pawn to be moved.
     * @param move2 The number of spaces to move the second pawn.
     * @param table The deck of the game.
     */
    public void executeMove(Pawn pawn1, int move1, Pawn pawn2, int move2, Deck table) {
        executeMove(pawn1, move1, table);
        executeMove(pawn2, move2, table);
    }

    /**
     * <b>Transformer:</b> Move the pawn by move spaces.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     */
    public void executeMove(Pawn pawn, int move, Deck table) {
        Square currentSquare = table.getBoard().get(pawn.getPosition());
        currentSquare.setOccupied(null);

        if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // Red pawn
            handleSafetyZone(pawn, move);
            handleRedPawnMove(pawn, move, table);
        } else if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // Yellow pawn
            handleSafetyZone(pawn, move);
            handleYellowPawnMove(pawn, move, table);
        } else {
            handleRegularMove(pawn, move, table);
        }

        table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
    }

    /**
     * <b>Transformer:</b> Move the pawn by move spaces.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     */
    private void handleRegularMove(Pawn pawn, int move, Deck table) {
        Square currentSquare = table.getBoard().get(pawn.getPosition());
        currentSquare.setOccupied(null);

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            handleRedPawnMove(pawn, move, table);
        } else {
            handleYellowPawnMove(pawn, move, table);
        }
    }

    /**
     * <b>Transformer:</b> Handle the move is the pawn is in the safety zone.
     * @pre-condition  The pawn is not at home and is not on the start square and is in the safety zone.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     */
    private void handleSafetyZone(Pawn pawn, int move) {

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            if (pawn.getPosition() + move == 65) {
                pawn.setPosition(65);
                pawn.setAtHome();
            } else {
                if (pawn.getPosition() + move < 65) {
                    pawn.setPosition(pawn.getPosition() + move);
                }
            }
        } else {
            if (pawn.getPosition() + move == 71) {
                pawn.setPosition(71);
                pawn.setAtHome();
            } else {
                if (pawn.getPosition() + move < 71) {
                    pawn.setPosition(pawn.getPosition() + move);
                }
            }
        }
    }

    /**
     * <b>Transformer:</b> Move the Red pawn by move spaces, depending on the pawn's position.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     */
    private void handleRedPawnMove(Pawn pawn, int move, Deck table) {
        if (pawn.getPosition() <= 2) {
            if (pawn.getPosition() + move <= 2) {
                handleNormalMove(pawn, move, table);
                super.handleSlides(pawn, table, pawn.getPosition());
            } else {
                handleSpecialMoveRedStart(pawn, move);
                super.handleSlides(pawn, table, pawn.getPosition());
            }
        } else if (pawn.getPosition() <= 59) {
            handleNormalMove(pawn, move, table);
            super.handleSlides(pawn, table, pawn.getPosition());
        }
    }

    /**
     * <b>Transformer:</b> Move the Yellow pawn by move spaces, depending on the pawn's position.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     */
    private void handleYellowPawnMove(Pawn pawn, int move, Deck table) {
        if (pawn.getPosition() <= 32) {
            if (pawn.getPosition() + move <= 32) {
                handleNormalMove(pawn, move, table);
                super.handleSlides(pawn, table, pawn.getPosition());
            } else {
                handleSpecialMoveYellowStart(pawn, move);
                super.handleSlides(pawn, table, pawn.getPosition());
            }
        } else if (pawn.getPosition() <= 59) {
            handleNormalMove(pawn, move, table);
            super.handleSlides(pawn, table, pawn.getPosition());
        }
    }

    /**
     * <b>Transformer:</b> Move the pawn by move spaces.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     */
    private void handleNormalMove(Pawn pawn, int move, Deck table) {
        pawn.setPosition(pawn.getPosition() + move);

        if (table.getBoard().get(pawn.getPosition()).isOccupied()) {
            sendToStartSquare(table.getBoard().get(pawn.getPosition()).getIsOccupied(), table);
        }
    }

    /**
     * <b>Transformer:</b> Handle the special move of the Red pawn if it is on the start square.
     * @pre-condition  The pawn is not at home and is on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     */
    private void handleSpecialMoveRedStart(Pawn pawn, int move) {
        if (pawn.getPosition() + move == 8) {
            pawn.setPosition(65);
            pawn.setAtHome();
        } else {
            pawn.setPosition(pawn.getPosition() + move + 57);
        }
        pawn.setInactive();
    }

    /**
     * <b>Transformer:</b> Handle the special move of the Red pawn if it is on the start square.
     * @pre-condition  The pawn is not at home and is on the start square.
     * @post-condition  The pawn is moved accordingly.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     */
    private void handleSpecialMoveYellowStart(Pawn pawn, int move) {
        if (pawn.getPosition() + move == 38) {
            pawn.setPosition(71);
            pawn.setAtHome();
        } else {
            pawn.setPosition(pawn.getPosition() + move + 33);
        }
        pawn.setInactive();
    }

    /**
     * <b>Observer:</b> Returns true if the pawn can be moved by move1 and move2 spaces.
     * @pre-condition  The pawns are not at home and are not on the start square.
     * @post-condition  Returns true if the pawn can be moved by move1 and move2 spaces.
     * @param pawn1 The first pawn to be moved.
     * @param move1 The number of spaces to move the first pawn.
     * @param pawn2 The second pawn to be moved.
     * @param move2 The number of spaces to move the second pawn.
     * @param table The deck of the game.
     * @return  True if the pawn can be moved by move1 and move2 spaces.
     */
    public boolean canMove(Pawn pawn1, int move1, Pawn pawn2, int move2, Deck table) {
        if (pawn1.isAtHome() || pawn2.isAtHome()) {
            return false; // reached home
        }

        if (table.getBoard().get(pawn1.getPosition()) instanceof StartSquare
                || table.getBoard().get(pawn2.getPosition()) instanceof StartSquare) {
            return false; // on start square
        }

        int possiblePosition1 = calculatePosition(pawn1, move1);
        int possiblePosition2 = calculatePosition(pawn2, move2);

        if (possiblePosition1 == -1 || possiblePosition2 == -1
                || (possiblePosition1 == possiblePosition2 && (possiblePosition1 != 65 && possiblePosition1 != 71))) {
            return false;
        }

        return canMove(pawn1, move1, table) && canMove(pawn2, move2, table);
    }

    /**
     * <b>Observer:</b> Calculates the possible position of the pawn.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  Calculates the possible position of the pawn.
     * @param pawn  The pawn to be moved.
     * @param move  The number of spaces to move the pawn.
     * @return  The possible position of the pawn.
     */
    private int calculatePosition(Pawn pawn, int move) {
        int possiblePosition = -1;

        if (pawn.getColor() == PAWNS_COLOR.RED && pawn.getPosition() <= 64) {
            if (pawn.getPosition() <= 2) {
                possiblePosition = (pawn.getPosition() + move <= 2) ?
                        pawn.getPosition() + move :
                        (pawn.getPosition() + move <= 8) ? pawn.getPosition() + move + 57 : -1;
            } else if (pawn.getPosition() <= 59) {
                possiblePosition = (pawn.getPosition() + move <= 59) ?
                        pawn.getPosition() + move :
                        (pawn.getPosition() + move <= 62) ? pawn.getPosition() + move - 60 :
                                (pawn.getPosition() + move <= 65) ? pawn.getPosition() + move - 3 : -1;
            } else if (pawn.getPosition() <= 64) {
                possiblePosition = (pawn.getPosition() + move <= 65) ? pawn.getPosition() + move : -1;
            }
        } else if (pawn.getColor() == PAWNS_COLOR.YELLOW && pawn.getPosition() <= 70) {
            if (pawn.getPosition() <= 32) {
                possiblePosition = (pawn.getPosition() + move <= 32) ?
                        pawn.getPosition() + move :
                        (pawn.getPosition() + move <= 38) ? pawn.getPosition() + move + 33 : -1;
            } else if (pawn.getPosition() <= 59) {
                possiblePosition = (pawn.getPosition() + move <= 59) ?
                        pawn.getPosition() + move :
                        (pawn.getPosition() + move <= 70) ? pawn.getPosition() + move - 60 : -1;
            } else if (pawn.getPosition() <= 70) {
                possiblePosition = (pawn.getPosition() + move <= 71) ? pawn.getPosition() + move : -1;
            }
        }

        return possiblePosition;
    }

    /**
     * <b>Observer:</b> Returns true if the pawn can be moved by move spaces.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  Returns true if the pawn can be moved by move spaces.
     * @param pawn  The pawn to be moved.
     * @param move  The number of spaces to move the pawn.
     * @param table The deck of the game.
     * @return  True if the pawn can be moved by move spaces.
     */
    public boolean canMove(Pawn pawn, int move, Deck table) {
        if (!pawn.isActive()) {
            if (pawn.getPosition() >= 60 && pawn.getPosition() < 65) { // red pawn
                return isMoveValid(pawn, move, table, 65);
            } else if (pawn.getPosition() >= 66 && pawn.getPosition() < 71) { // yellow pawn
                return isMoveValid(pawn, move, table, 71);
            }
        }

        int possiblePosition = calculatePossiblePosition(pawn, move);

        if (table.getBoard().get(possiblePosition).isOccupied()) {
            return pawn.getColor() == table.getBoard().get(possiblePosition).getIsOccupied().getColor();
        }

        return true;
    }

    /**
     * <b>Observer:</b> Checks if the move is valid.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @param table The deck of the game.
     * @param destination The destination of the pawn.
     * @return True if the move is valid.
     */
    private boolean isMoveValid(Pawn pawn, int move, Deck table, int destination) {
        if (pawn.getPosition() + move <= destination) {
            if (pawn.getPosition() + move == destination) {
                return true;
            }
            return !table.getBoard().get(pawn.getPosition() + move).isOccupied();
        } else {
            return false;
        }
    }

    /**
     * <b>Observer:</b> Calculates the possible position of the pawn.
     * @pre-condition  The pawn is not at home and is not on the start square.
     * @post-condition  Calculates the possible position of the pawn.
     * @param pawn The pawn to be moved.
     * @param move The number of spaces to move the pawn.
     * @return The possible position of the pawn.
     */
    private int calculatePossiblePosition(Pawn pawn, int move) {
        int possiblePosition = 0;

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            if (pawn.getPosition() <= 2) {
                if (pawn.getPosition() + move <= 2) {
                    possiblePosition = pawn.getPosition() + move;
                } else if (pawn.getPosition() + move <= 8) {
                    if (pawn.getPosition() + move == 8) {
                        return 0; // Returning 0 signifies a special case.
                    }
                    possiblePosition = pawn.getPosition() + move + 57;
                }
            } else if (pawn.getPosition() <= 59) {
                if (pawn.getPosition() + move <= 59) {
                    possiblePosition = pawn.getPosition() + move;
                } else if (pawn.getPosition() + move < 63) {
                    possiblePosition = pawn.getPosition() + move - 60;
                } else if (pawn.getPosition() + move <= 68) {
                    if (pawn.getPosition() + move == 68) {
                        return 0; // Returning 0 signifies a special case.
                    }
                    possiblePosition = pawn.getPosition() + move - 3;
                }
            }
        } else { // Yellow pawn
            if (pawn.getPosition() <= 32) {
                if (pawn.getPosition() + move <= 32) {
                    possiblePosition = pawn.getPosition() + move;
                } else if (pawn.getPosition() + move <= 38) {
                    if (pawn.getPosition() + move == 38) {
                        return 0; // Returning 0 signifies a special case.
                    }
                    possiblePosition = pawn.getPosition() + move + 33;
                }
            } else if (pawn.getPosition() <= 59) {
                if (pawn.getPosition() + move <= 59) {
                    possiblePosition = pawn.getPosition() + move;
                } else {
                    possiblePosition = pawn.getPosition() + move - 60;
                }
            }
        }

        return possiblePosition;
    }

    @Override
    public String toString() {
        return "Special Card " + "{ " + super.getNumber() + " }";
    }

}
