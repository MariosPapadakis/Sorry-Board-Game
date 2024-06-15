package Model.Square;

import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Public Class</b> that represents a Start Slide Square.
 * <br><p>A <b>Start slide Square </b> is a Square that is the start of the slide.</p>
 * <br><b>Superclass: </b> SlideSquare
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public class StartSlideSquare extends Square implements Serializable {

    /**
     * <b>Constructor:</b> Instantiates the Start Slide Square
     * @param position the position of the Square.
     * @param color the color of the Square.
     */
    public StartSlideSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }
}
