package kit101.turtle;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Draws turtle graphics in a {@link TurtleWorld}.
 * <p>
 * Example usage:
 * <pre>
 * Turtle t;
 * t = new Turtle();
 * t.turn(45);
 * t.move(100);
 * </pre>
 *
 * @see TurtleWorld
 * @see TurtleWindow
 *
 * @author James Montgomery, UTAS, 2014-
 * @author Robyn Gibson, UTAS, 2001-2
 * @author Michael Caspersen, University of Aahus (original author)
 *
 * @version 2021-01-29
 */
public class Turtle implements Cloneable {
    /**
     * If a Turtle is created without a world, a default world is created and
     * used by future Turtles too.
     */
    protected static TurtleWorld sharedWorld;
    /** World in which the Turtle is placed. */
    TurtleWorld world;
    /** Direction (in degrees) the Turtle is pointing (0 is east). */
    protected double direction;
    /** Turtle's x position. */
    protected double x;
    /** Turtle's y position. */
    protected double y;
    /** Current pen colour. */
    protected Color pen;
    /** {@code true} when the pen is down. */
    protected boolean down;
    
    
    /**
     * Creates a new Turtle in the given {@link TurtleWorld}.
     */
    public Turtle(TurtleWorld tw) {
        setWorld(tw);
        reset();
        center();
    }
    
    /**
     * Creates a new Turtle in a new, default world with dimensions 500 x 500
     * that is thread-safe (hence, good for relatively small numbers (approx
     * 100) of drawing commands that immediately show up on the screen).
     * The Turtle starts at the centre of the drawing area, facing east
     * (direction 0), with its pen down.
     */
    public Turtle() {
        this(true);
    }

    /**
     * Creates a new Turtle in a new, default world with dimensions 500 x 500,
     * with thread safety optionally turned on or off. The Turtle starts at the
     * centre of the drawing area, facing east (direction 0), with its pen down.
     * <p>
     * Initialising the Turtle without thread safety is useful if a <em>very</em>
     * large number of drawing commands are to be given, <em>but</em> the Turtle
     * must be told to {@link #startBatchDrawing()} beforehand and to
     * {@link #endBatchDrawing()} after, to repaint the lines on screen. This is
     * a pragmatic workaround for the problem that the current thread-safe
     * implementation is extremely slow when a large number of lines are drawn.
     */
    public Turtle(boolean threadSafe) {
        this( getSharedWorld(threadSafe) );
    }

    /**
     * Creates a new Turtle in the given TurtleWindow's world.
     */
    public Turtle(TurtleWindow w)
    {
        this(w.getWorld());
        w.setVisible(true);
    }    
    
    /**
     * Resets the Turtle's position to (0, 0), direction to 0 (east), pen
     * colour to black and pen to down.
     */
    private void reset() {
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        setColor(Color.black);
        setPenDown(true);
    }
    
    private static synchronized TurtleWorld getSharedWorld(boolean threadSafe) {
        if (sharedWorld == null) {
            TurtleWindow tw = new TurtleWindow(threadSafe);
            tw.setVisible(true);
            sharedWorld = tw.getWorld();
        }
        return sharedWorld;
    }
    
    /**
     * Turns the Turtle anticlockwise by the given number of degrees.
     *
     * @param delta - number of degrees to turn. Can be negative, in which
     * case Turtle will turn clockwise.
     */
    public void turn(double delta) { direction += delta; }
    
    /**
     * Turns Turtle to the given angle, where 0 corresponds to east, 90 to north,
     * 180 west and 270 south.
     *
     * @param deg the new angle to point.
     */
    public void setDirection(double deg) { direction = deg; }
    
    /**
     * Moves this Turtle the given distance in the direction it is facing. If the
     * pen is down it will draw a line between its starting and ending locations.
     *
     * @param dist the number of pixels to move
     */
    public void move(double dist) {
        double x2, y2;
        x2 = x + (Math.cos(direction * (Math.PI / 180.0))*dist);
        y2 = y + (Math.sin(direction * (Math.PI / 180.0))*dist);
        if (down) {
            world.drawLine((int)(x+.5),(int)(y+.5),(int)(x2+.5),(int)(y2+.5),pen);
        }
        x = x2;
        y = y2;
    }
    
    /**
     * Moves Turtle to the position given by  parameters; if the pen is down,
     * the Turtle will draw a line as it goes.
     * @param x coordinate - double
     * @param y coordinate - double
     */
    public void moveTo(double x, double y)
    {
        if (down) {
            world.drawLine((int)(this.x+.5),(int)(this.y+.5),(int)(x+.5),(int)(y+.5),pen);
        }
        this.x = x;
        this.y = y;
    }
    
    /**
     * Clears all the drawing in the Turtle's world.
     */
    public void clear() { world.clear(); }
    
    /**
     * Changes Turtle's pen to the up position (so no line will be drawn when it
     * moves. Equivalent to calling {@code setPenDown(false)}.
     *
     * @see #penDown()
     * @see #setPenDown(boolean)
     */
    public void penUp() { down = false; }
    
    /**
     * Changes Turtle's pen to the down position (so a line will be drawn when it
     * moves. Equivalent to calling {@code setPenDown(true)}.
     *
     * @see #penUp()
     * @see #setPenDown(boolean)
     */
    public void penDown() { down = true; }
    
    /**
     * Returns {@code true} if the Turtle's pen is currently down, {@code false}
     * otherwise.
     */
    public boolean isDown() { return down; }
    
    /**
     * Sets the Turtle's pen position.
     *
     * @param down if {@code true} then pen will be put in down position;
     * otherwise pen will be in up position.
     *
     * @see #penUp()
     * @see #penDown()
     */
    public void setPenDown(boolean down) { this.down = down; }
    
    /**
     * Sets the pen colour. Example usage:<br>
     * <pre>
     * Turtle t = new Turtle();
     * t.setColor(Color.blue);
     * </pre>
     * Requires that {@link java.awt.Color} is imported, as in:<br>
     * {@code import  java.awt.Color;}
     */
    public void setColor(Color c) { pen = c; }
    
    /**
     * Moves Turtle to the middle of its world without drawing a line.
     */
    public void center() {
        Dimension d = world.getSize();
        boolean wasDown = isDown();
        penUp();
        moveTo(d.width/2, d.height/2);
        setPenDown(wasDown);
    }
    
    /**
     * Moves Turtle to the position 0,0 of its world (generally the bottom left)
     * without drawing a line.
     */
    public void origin() {
        boolean wasDown = isDown();
        penUp();
        moveTo(0,0);
        setPenDown(wasDown);
    }
    
    /**
     * Pauses for number of milliseconds indicated by parameter.
     */
    public void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch(Exception e) { /* ignore any exceptions */ }
    }
    
    /**
     * Returns the currrent pen color
     *
     * @return pen's {@linkplain java.awt.Color colour}.
     */
    public Color getColor() { return pen; }
    
    /**
     * Returns the Turtle's current x position.
     */
    public double getX() { return x; }
    
    /**
     * Returns the Turtle's current y position.
     */
    public double getY() { return y; }
    
    /**
     * Returns the Turtle's current direction (in degrees), where 0 corresponds to
     * east, 90 to north, 180 to west and 270 to south.
     */
    public double getDirection() { return direction; }
    
    /**
     * Sets the Turtle's {@linkplain TurtleWorld world} and returns a refeerence
     * to this Turtle.
     *
     * @param world the new {@code TurtleWorld}
     * @return this Turtle
     */
    public Turtle setWorld(TurtleWorld world) {
        this.world = world;
        return this;
    }

    /**
     * Stops rendering lines on the screen until {@link #endBatchDrawing()}
     * is called. Only required when the Turtle was created in non-thread-safe
     * mode.
     */
    public void startBatchDrawing() {
        world.suspendRepaint();
    }
    
    /**
     * Causes all lines created since {@link #startBatchDrawing()} was called to
     * be drawn. Only required when the Turtle was created in non-thread-safe
     * mode.
     */
    public void endBatchDrawing() {
        world.resumeRepaint();
    }
    
    /**
     * Returns this Turtle's {@linkplain TurtleWorld world}.
     */
    public TurtleWorld getWorld() { return world; }
    
    /**
     * Returns a clone of this Turtle. The new Turtle will share the same world
     * as this one. Although the clone will also share the same pen colour
     * object (initially), this is safe because {@code Color} is immutable. The
     * result must be cast to Turtle to be used, as in:<p>
     * <pre>
     * Turtle t = new Turtle();
     * Turtle t2 = (Turtle) t.clone();
     * </pre>
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // Exception should *never* occur, since Object does not throw it,
            // so if it does then it is likely a more serious system error;
            // downgrade to a RuntimeException.
            e.printStackTrace();
            throw new RuntimeException("Unexpected error cloning Turtle", e);
        }
    }
    
}

