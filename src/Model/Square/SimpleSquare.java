package Model.Square;

import java.awt.*;
import java.io.Serializable;

/**
 * <b>Public Class</b> Represents a Simple Square.
 * <br><p>A simple Square is a Square that does not have any special effect.</p>
 * <br><b>Superclass: </b> Square
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 * @see Square
 */
public class SimpleSquare extends Square implements Serializable {
    /**
     * <b>Constructor:</b> Instantiates the Simple Square
     *
     * @param position the position of the Square.
     */
    public SimpleSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }


}
