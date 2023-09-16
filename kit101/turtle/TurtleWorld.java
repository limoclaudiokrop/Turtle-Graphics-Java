package kit101.turtle;

import java.awt.Color;
import java.awt.Dimension;

/**
 * The minimum requirements for a class capable of drawing turtle graphics,
 * augmented by pragmatic commands to suspend and resume repainting after
 * each new line.
 *
 * @see Turtle
 * @version 2021-01-29
 */
public interface TurtleWorld {
    /**
     * Cleans up the drawing area
     */
    public void clear();

    /**
     * Draws a line from (x1,y1) to (x2,y2) with the pen color {@code pen}.
     * @param x1 x coordinate of point 1
     * @param y1 y coordinate of point 1
     * @param x2 x coordinate of point 2
     * @param y2 y coordinate of point 2
     * @param pen line colour
     */
    public void drawLine(int x1, int y1, int x2, int y2, Color pen);

    /**
     * Return the dimension of the drawing area.
     */
    public Dimension getSize();
    
    /**
     * Suspend repaint on new line behaviour until {@link #resumeRepaint()} is called.
     */
    public void suspendRepaint();

    /**
     * Resume repaint on new line behaviour and immediately repaint the world.
     */
    public void resumeRepaint();
    
}

