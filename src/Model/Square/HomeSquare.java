package Model.Square;

import Model.Player;

import java.awt.*;
import java.io.Serializable;

/**
 * <b>Public Class</b> that represents the Home of a player.
 * <br><b>Superclass: </b> Square
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 * @see Square
 */
public class HomeSquare extends Square implements Serializable {

    /**
     * <b>Constructor:</b> Instantiates the Home Square
     *
     * @param position the position of the Square.
     * @param color the color of the Square.
     */
    public HomeSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }
}
