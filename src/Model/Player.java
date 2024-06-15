package Model;

import java.io.Serializable;

/**
 * <b>Public Class</b> Represents a Player.
 * <br><b>Superclass: </b> None
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public class Player implements Serializable {
    /**
     * <b>Enum</b> that represents the color of the pawns of the player.
     * <br><b>Values: </b> RED, BLUE, GREEN, YELLOW
     */
    PAWNS_COLOR PAWNSColor;

    /**
     * <b>String Variable</b> that represents the name of the player.
     */
    private String playerName;

    /**
     * <b>The Pawns</b> of the player.
     */
    Pawn[] pawns = new Pawn[2];

    /**
     * <b>Boolean Variable</b> that represents if it's the player's turn.
     */
    private boolean playerTurn;

    /**
     * <b>Constructor:</b> Instantiates the Player.
     *
     * @param PAWNSColor the color of the pawns of the player.
     * @param playerName the name of the player.
     */
    public Player(PAWNS_COLOR PAWNSColor, String playerName, Pawn[] pawns) {
        setColor(PAWNSColor);
        setPlayerName(playerName);
        this.playerTurn = false;

        this.pawns = pawns;

    }

    /**
     * <b>Observer: </b>returns the pawns of the player
     * @return the pawns of the player
     */
    public Pawn[] getPawns() {
        return pawns;
    }
    public Pawn getPawn(int i){
        return pawns[i-1];
    }

    /**
     * <b>Transformer: </b>Sets the pawns of the player
     * @param pawns the pawns of the player
     */
    public void setPawns(Pawn[] pawns) {
        this.pawns = pawns;
    }

    /**
     * <b>Observer: </b>returns the color of the pawns of the player
     * @return the color of the pawns of the player
     */
    public PAWNS_COLOR getColor() {
        return PAWNSColor;
    }

    /**
     * <b>Transformer: </b>Sets the color of the pawns of the Pawns of the Player
     * @param PAWNSColor the color of the pawns of the player
     */
    public void setColor(PAWNS_COLOR PAWNSColor) {
        this.PAWNSColor = PAWNSColor;
    }

    /**
     * <b>Observer: </b>returns the name of the player
     * @return the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * <b>Transformer: </b>Sets the name of the player
     * @param playerName the name of the player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * <b>Transformer: </b>Changes the turn of the players
     */
    public void swapTurns(){
        playerTurn = !playerTurn;
    }

    /**
     * Observer:<br>returns if it's player's turn
     * @return true if it's player's turn, false if it's not
     */
    public boolean getTurn(){
        return playerTurn;
    }

    /**
     * <b>Observer: </b>Overrides the equals method from the Object class.
     * <br>This method is used to <b>compare</b> two Player objects for equality.
     * <br>Two Player objects are considered equal if they have the same attributes.
     *
     * @param obj the object to be compared for equality with this Player
     * @return true if the specified object is equal to this Player, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Player player = (Player) obj;
        return false;
    }

    public boolean isWinner(){
        return pawns[0].isAtHome() && pawns[1].isAtHome();
    }

    @Override
    public String toString() {
        String playerColor;
        playerColor = (this.getColor() == PAWNS_COLOR.RED) ? "Red" : "Yellow";
        return this.playerName + " (" + playerColor + ")";
    }
}
