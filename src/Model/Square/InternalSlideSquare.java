package Model.Square;

import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Public Class</b> that represents an Internal Slide Square.
 * <br><p>An <b>Internal slide Square </b> is a Square that is part of the slide but is not the start or the end of the slide.</p>
 * <br><b>Superclass: </b> SlideSquare
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public class InternalSlideSquare extends Square implements Serializable {

    /**
     * <b>Constructor:</b> Instantiates the Internal Slide Square
     *
     * @param position the position of the Square.
     * @param color the color of the Square.
     */
    public InternalSlideSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }
}
