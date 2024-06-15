package Model.Square;

import Model.Player;

import java.awt.*;
import java.io.Serializable;

/**
 * <b>Public class</b> representing the Start Square.
 * <br><b>Responsibility:</b> Represent the Squares of the Board.
 * <br><b>Superclass:</b> Square
 * <br><b>Subclasses:</b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 * @see Square
 */
public class StartSquare extends Square implements Serializable {
    /**
     * <b>Constructor:</b> Instantiates the Start Square
     * @param position the position of the Square.
     * @param color    the color of the Square.
     */
    public StartSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }

}
