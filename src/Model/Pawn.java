package Model;

import java.io.Serializable;

/**
 * <b>Public Class</b> Represents a Pawn.
 * <br><b>Superclass: </b> None
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @author CSD5254
 */
public class Pawn implements Serializable {
    /**
     * <b>Enum</b> that represents the color of the Pawn.
     */
    PAWNS_COLOR PAWNSColor;

    /**
     * <b>Integer Variable</b> that represents the position of the Pawn.
     */
    private int position;

    /**
     * <b>Boolean Variable</b> that represents if the Pawn is active.
     */
    private boolean isActive;

    /**
     * <b>Boolean Variable</b> that represents if the Pawn is at its final destination.
     */
    private boolean isAtHome = false;


    /**
     * <b>Constructor:</b> Instantiates the Pawn with the given color.
     * The position of the Pawn is set to 0 and it is set as active.
     *
     * @param PAWNSColor the color of the Pawn.
     */
    public Pawn(PAWNS_COLOR PAWNSColor, int position){
        setColor(PAWNSColor);
        setPosition(position);
        setInactive();
        this.isAtHome = false;
    }

    /**
     * <b>Observer: </b>returns the color of the Pawn
     * @return the color of the Pawn
     */
    public PAWNS_COLOR getColor() {
        return PAWNSColor;
    }

    /**
     * <b>Transformer: </b>Sets the color of the Pawn
     * @param PAWNSColor the color of the Pawn
     */
    public void setColor(PAWNS_COLOR PAWNSColor) {
        this.PAWNSColor = PAWNSColor;
    }

    /**
     * <b>Observer: </b>returns the position of the Pawn
     * @return the position of the Pawn
     */
    public int getPosition() {
        return position;
    }

    /**
     * <b>Transformer: </b>Sets the position of the Pawn
     * @param position the position of the Pawn
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * <b>Observer: </b>returns if the Pawn is active
     * @return true if the Pawn is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * <b>Transformer: </b>Sets the Pawn as active.
     */
    public void setActive() {
        this.isActive = true;
    }

    /**
     * <b>Transformer: </b>Sets the Pawn as inactive.
     */
    public void setInactive(){
        this.isActive = false;
    }

    /**
     * <b>Transformer: </b>Sets the Pawn as at home or not
     */
    public void setAtHome() {
        System.out.println("Pawn " + PAWNSColor + " finished!");
        this.isAtHome = true;
    }

    /**
     * <b>Observer: </b>returns if the Pawn is at home
     * @return true if the Pawn is at home, false otherwise
     */
    public boolean isAtHome() {
    	return isAtHome;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "PAWNSColor=" + PAWNSColor +
                ", position=" + position +
                ", isAtHome=" + isAtHome +
                '}';
    }
}
