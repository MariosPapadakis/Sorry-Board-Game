package Model.Card;

import Model.Deck;
import Model.PAWNS_COLOR;
import Model.Pawn;
import Model.Square.StartSquare;

public abstract class NumberCard extends Card {

    /**
     * <b>Variable: </b>Stores the number of the card.
     */
    private int number;

    /**
     * <b>Transformer: </b>Sets the number of the card to the given number.
     * @pre-condition The card must be initialized.
     * @post-condition Sets the number of the card to the given number.
     * @param number The number of the card.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * <b>Getter: </b>Returns the number of the card.
     */
    public int getNumber() {
        return number;
    }

    /**
     * <b>Transformer: </b>Moves the given pawn depending on the number of the card.
     * @pre-condition The card must be able to make the move.
     * @post-condition Moves the given pawn depending on the number of the card.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     */
    public void executeMove(Pawn pawn, Deck table) {
        table.getBoard().get(pawn.getPosition()).setOccupied(null);

        if (isInSafetyZone(pawn)) {
            handleSafetyZoneMove(pawn);
        } else {
            handleNonSafetyZoneMove(pawn, table);
        }

        table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
    }

    /**
     * <b>Transformer: </b>Handles the move of the pawn while in the safety zone.
     * @pre-condition The card must be able to make the move.
     * @pre-condition The pawn must be in the safety zone.
     * @param pawn The pawn that will be moved.
     */
    private void handleSafetyZoneMove(Pawn pawn) {
        int targetPosition = pawn.getPosition() + number;

        if (pawn.getColor() == PAWNS_COLOR.RED && targetPosition == 65) {
            pawn.setPosition(65);
            pawn.setAtHome();
        } else if (pawn.getColor() == PAWNS_COLOR.YELLOW && targetPosition == 71) {
            pawn.setPosition(71);
            pawn.setAtHome();
        } else {
            if (pawn.getColor() == PAWNS_COLOR.RED && targetPosition < 65 && pawn.isActive()) {
                pawn.setPosition(targetPosition);
            } else if (pawn.getColor() == PAWNS_COLOR.YELLOW && targetPosition < 71 && pawn.isActive()) {
                pawn.setPosition(targetPosition);
            }
        }
    }

    /**
     * <b>Transformer: </b>Handles the move of the pawn while not in the safety zone.
     * <br><b>Use: </b>Used in the executeMove method.
     * @pre-condition The card must be able to make the move.
     * @pre-condition The pawn must not be in the safety zone.
     * @param pawn  The pawn that will be moved.
     * @param table The table of the game.
     */
    private void handleNonSafetyZoneMove(Pawn pawn, Deck table) {
        int originalPosition = pawn.getPosition();
        int targetPosition = calculateTargetPosition(pawn);

        if(targetPosition == -1) {
            return;
        }

        pawn.setPosition(targetPosition);

        if (table.getBoard().get(targetPosition).isOccupied()) {
            sendToStartSquare(table.getBoard().get(targetPosition).getIsOccupied(), table);
        }

        if (pawn.getPosition() == 65 || pawn.getPosition() == 71) {
            pawn.setInactive();
        }

        if (((originalPosition <= 2 || (originalPosition >= 56 && originalPosition<=59)) && pawn.getPosition() == 65) ||
                (originalPosition <= 32 && pawn.getPosition() == 71)) {
            pawn.setAtHome();
        }

        handleSlides(pawn, table, pawn.getPosition());
    }

    /**
     * <b>Transformer: </b>Handles the slides of the pawn.
     * <br><b>Use: </b>Used in the executeMove method.
     * @pre-condition The card must be able to make the move.
     * @post-condition Handles the slides of the pawn.
     * @param pawn  The pawn that will be moved.
     * @param table The table of the game.
     * @param originalPosition  The original position of the pawn.
     */
    public void handleSlides(Pawn pawn, Deck table, int originalPosition) {
        if(pawn.getColor() == PAWNS_COLOR.RED) {
            handleRedSlides(pawn, table, originalPosition);
        } else {
            handleYellowSlides(pawn, table, originalPosition);
        }
    }

    /**
     * <b>Transformer: </b>Handles the slides of the yellow pawn.
     * <br><b>Use: </b>Used in the handleSlides method.
     * @pre-condition The card must be able to make the move.
     * @post-condition Handles the slides of the Yellow pawn.
     * @param pawn  The pawn that will be moved.
     * @param table The table of the game.
     */
    private void handleYellowSlides(Pawn pawn, Deck table, int originalPosition) {
        if (originalPosition == 17 || originalPosition == 46 || originalPosition == 1) {
            pawn.setPosition(pawn.getPosition() + 3);
            for (int i = 1; i <= 3; i++) {
                if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                    sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                }
            }
            System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
            table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
        } else if (originalPosition == 25 || originalPosition == 54 || originalPosition == 9) {
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

    /**
     * <b>Transformer: </b>Handles the slides of the red pawn.
     * <br><b>Use: </b>Used in the handleSlides method.
     * @pre-condition The card must be able to make the move.
     * @post-condition Handles the slides of the Red pawn.
     * @param pawn  The pawn that will be moved.
     * @param table The table of the game.
     * @param originalPosition  The original position of the pawn.
     */
    private void handleRedSlides(Pawn pawn, Deck table, int originalPosition) {
        if (originalPosition == 17 || originalPosition == 31 || originalPosition == 46) {
            pawn.setPosition(pawn.getPosition() + 3);
            for (int i = 1; i <= 3; i++) {
                if (table.getBoard().get(pawn.getPosition() + i).isOccupied()) {
                    sendToStartSquare(table.getBoard().get(pawn.getPosition() + i).getIsOccupied(), table);
                }
            }
            System.out.println("\nPlayer " + pawn.getColor() + " went through a slide!\n");
            table.getBoard().get(pawn.getPosition()).setOccupied(pawn);
        } else if (originalPosition == 25 || originalPosition == 39 || originalPosition == 54) {
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

    /**
     * <b>Observer: </b>Returns the target position that the Pawn will be moved to.
     * <br><b>Use: </b>Used in the handleNonSafetyZoneMove method.
     * @pre-condition The pawn must be initialized.
     * @post-condition Returns the target position that the Pawn will be moved to.
     * @param pawn The pawn that will be moved.
     * @return the target position that the Pawn will be moved to.
     */
    private int calculateTargetPosition(Pawn pawn) {
        int targetPosition=0;

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            if (pawn.getPosition() <= 2) {
                if (pawn.getPosition() + number <= 2) {
                    targetPosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number <= 8) {
                    targetPosition = pawn.getPosition() + number + 57;
                } else {
                    targetPosition = -1;
                }
            } else if (pawn.getPosition() <= 59) {
                if (pawn.getPosition() + number <= 59) {
                    targetPosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number < 63) {
                    targetPosition = pawn.getPosition() + number - 60;
                } else if (pawn.getPosition() + number <= 68) {
                    targetPosition = pawn.getPosition() + number - 3;
                } else {
                    targetPosition = -1;
                }
            }
        }
        else {
            if (pawn.getPosition() <= 32) {
                if (pawn.getPosition() + number <= 32) {
                    targetPosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number <= 38) {
                    targetPosition = pawn.getPosition() + number + 33;
                } else {
                    targetPosition = -1;
                }
            } else if (pawn.getPosition() <= 59 && pawn.getPosition() >= 33)
                if (pawn.getPosition() + number <= 59) {
                    targetPosition = pawn.getPosition() + number;
                } else {
                    targetPosition = pawn.getPosition() + number - 60;
                }
        }
        return targetPosition;
    }

    /**
     * <b>Observer: </b>Returns true if the pawn is in the safety zone, false otherwise.
     * @pre-condition The pawn must be initialized.
     * @post-condition Returns true if the pawn is in the safety zone, false otherwise.
     * @param pawn The pawn that will be checked.
     * @return True if the pawn is in the safety zone, false otherwise.
     */
    private boolean isInSafetyZone(Pawn pawn) {
        return (pawn.getPosition() >= 60 && pawn.getPosition() < 65) || (pawn.getPosition() >= 66 && pawn.getPosition() < 71);
    }

    /**
     * <b>Observer: </b>Returns true if the pawn can be moved by using the card, false otherwise.
     * @pre-condition The pawn must be initialized.
     * @post-condition Returns true if the pawn can be moved by using the card, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     * @return True if the pawn can be moved by using the card, false otherwise.
     */
    public boolean canMove(Pawn pawn, Deck table) {
        if (pawn.isAtHome()) {
            return false;
        }

        if (!pawn.isActive()) {
            if (table.getBoard().get(pawn.getPosition()) instanceof StartSquare) {
                return false;
            }

            if (isInSafetyZone(pawn)) {
                int targetPosition = pawn.getPosition() + number;
                System.out.println("Target position: " + targetPosition + " for pawn " + pawn.getColor());

                System.out.println("\nThe result: " + isValidMoveWithinSafetyZone(pawn, table, targetPosition));
                return isValidMoveWithinSafetyZone(pawn, table, targetPosition);
            }
        }

        int possiblePosition = calculatePossiblePosition(pawn);

        if(possiblePosition == -1) {
            return false;
        }

        if (table.getBoard().get(possiblePosition).isOccupied()) {
            return table.getBoard().get(possiblePosition).getIsOccupied().getColor() != pawn.getColor();
        } else {
            return true;
        }
    }

    /**
     * <b>Observer: </b> Checks if the  move is valid within the safety zone.
     * @pre-condition The card must be initialized.
     * @post-condition Returns true if the move is valid within the safety zone, false otherwise.
     * @param pawn The pawn that will be moved.
     * @param table The table of the game.
     * @param targetPosition The position that the pawn will be moved to.
     */
    private boolean isValidMoveWithinSafetyZone(Pawn pawn, Deck table, int targetPosition) {

        if(pawn.getColor() == PAWNS_COLOR.RED && targetPosition > 65) {
            System.out.println("target position > 65 for red");
            return false;
        } else if(pawn.getColor() == PAWNS_COLOR.YELLOW && targetPosition > 71) {
            System.out.println("target position > 71 for yellow");
            return false;
        }

        if (table.getBoard().get(targetPosition).isOccupied()) {
            System.out.println("target position is occupied");
            return false;
        }

        if (targetPosition == 71 && pawn.getColor() == PAWNS_COLOR.YELLOW) {
            return true;
        } else if(targetPosition == 65 && pawn.getColor() == PAWNS_COLOR.RED) {
            return true;
        }

        System.out.println("Nothin' special");
        return false;
    }

    /**
     * <b>Observer: </b>Calculates the possible position that the Pawn will be moved to.
     * @pre-condition The Pawn must be initialized.
     * @post-condition Returns the possible position that the Pawn will be moved to.
     * @param pawn The Pawn that will be moved.
     * @return The possible position that the Pawn will be moved to.
     */
    private int calculatePossiblePosition(Pawn pawn) {
        int possiblePosition = 0;

        if (pawn.getColor() == PAWNS_COLOR.RED) {
            if (pawn.getPosition() <= 2) {
                if (pawn.getPosition() + number <= 2) {
                    possiblePosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number <= 8) {
                    if (pawn.getPosition() + number == 8) {
                        return 65;
                    }
                    possiblePosition = pawn.getPosition() + number + 57;
                } else {
                    return -1;
                }
            } else if (pawn.getPosition() <= 59) {
                if (pawn.getPosition() + number <= 59) {
                    possiblePosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number < 63) {
                    possiblePosition = pawn.getPosition() + number - 60;
                } else if (pawn.getPosition() + number <= 68) {
                    if (pawn.getPosition() + number == 68) {
                        return 65;
                    }
                    possiblePosition = pawn.getPosition() + number - 3;
                } else {
                    return -1;
                }
            }
        } else {
            if (pawn.getPosition() <= 32) {
                if (pawn.getPosition() + number <= 32) {
                    possiblePosition = pawn.getPosition() + number;
                } else if (pawn.getPosition() + number <= 38) {
                    if (pawn.getPosition() + number == 38) {
                        return 71;
                    }
                    possiblePosition = pawn.getPosition() + number + 33;
                } else {
                    return -1;
                }
            } else if (pawn.getPosition() <= 59) {
                if (pawn.getPosition() + number <= 59) {
                    possiblePosition = pawn.getPosition() + number;
                } else {
                    possiblePosition = pawn.getPosition() + number - 60;
                }
            }
        }

        return possiblePosition;
    }

    @Override
    public String toString() {
        return "Simple Card: " + "{ " + number + " }";
    }
}