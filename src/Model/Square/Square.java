package Model.Square;

import Model.Pawn;

import java.awt.*;

import java.awt.Color;

/**
 * <b>Abstract Class:</b> Abstract class that represents a Square of the Board.
 * <br><b>Responsibility:</b> Represent the Squares of the Board.
 * <br><b>Superclass:</b> None
 * <br><b>Subclasses:</b> EndSlideSquare, EndSquare, HomeSquare, SlideSquare, StartSquare
 * @version 1.0
 * @since 1.0
 * @author CSD5254
 */
public abstract class Square {

    /**
     * <b>Private Variable: </b> Stores the position of the Square.
     */
    private int position;

    /**
     * <b>Private Variable: </b> Stores the color of the Square.
     */
    private Color color;

    /**
     * <b>Private Variable: </b> Stores the Pawn that occupies the Square.
     */
    private Pawn occupied;

    /**
     * <b>Observer:</b> Returns if the Square is occupied with a Pawn or not.
     * @pre-condition The Square must be instantiated.
     * @post-condition Returns if the Square is occupied with a Pawn or not.
     * @return true if the Square is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return occupied != null;
    }

    /**
     * <b>Observer:</b> Returns the Pawn that occupies the Square.
     * @return the Pawn that occupies the Square.
     */
    public Pawn getIsOccupied(){
        return occupied;
    }

    /**
     * <b>Transformer:</b> Sets the Square as occupied with the given Pawn.
     * @param pawn
     */
    public void setOccupied(Pawn pawn){
        this.occupied = pawn;
    }

    /**
     * <b>Transformer:</b> Sets the position of the Square to the given one.
     * @pre-condition  The Square must be instantiated.
     * @post-condition  Sets the position of the Square to the given one.
     */
    public void setPosition(int position){
        this.position = position;
    }

    /**
     * <b>Observer:</b> Returns the position of the Square.
     * @pre-condition The Square must be instantiated.
     * @post-condition Returns the position of the Square.
     * @return the position of the Square.
     */
    public int getPosition(){
        return position;
    }

    /**
     * <b>Transformer:</b> Sets the color of the Square to the given one.
     * @pre-condition  The Square must be instantiated.
     * @post-condition  Sets the color of the Square to the given one.
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * <b>Observer:</b> Returns the color of the Square.
     * @pre-condition The Square must be instantiated.
     * @post-condition Returns the color of the Square.
     * @return the color of the Square.
     */
    public Color getColor() {
        return color;
    }
}
