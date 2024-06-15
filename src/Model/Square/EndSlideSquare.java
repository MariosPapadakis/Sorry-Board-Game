package Model.Square;
import java.awt.Color;
import java.io.Serializable;

/**
 * <b>Public Class</b> that represents an End Slide Square.
 * <br><p>A <b>End slide Square </b> is a Square that is the End of the slide.</p>
 * <br><b>Superclass: </b> SlideSquare
 * <br><b>Subclasses: </b> None
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public class EndSlideSquare extends Square implements Serializable {

    /**
     * <b>Constructor:</b> Instantiates the End Slide Square
     *
     * @param position the position of the Square.
     * @param color the image of the Square.
     */
    public EndSlideSquare(int position, Color color) {
        super.setOccupied(null);
        setColor(color);
        setPosition(position);
    }
}
