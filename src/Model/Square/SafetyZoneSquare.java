package Model.Square;

import Model.Player;

import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Public Class:</b> Represents a Safety Zone Square.
 * <br><p>A Safety Zone Square is a square that can only be accessed by a specific player</p>
 * <br><b>Superclass: </b> Square
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 * @see Square
 */
public class SafetyZoneSquare extends Square implements Serializable {

    /**
     * <b>Constructor:</b> Instantiates the Safety Square
     *
     * @param position the position of the Square.
     * @param color the color of the Square.
     */
    public SafetyZoneSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }
}
