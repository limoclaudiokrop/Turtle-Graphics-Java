package kit101.turtle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.Collection;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;

/**
 * A turtle graphics world that can be placed in any container.
 *
 * Supports drawing straight lines only.
 *
 * @version 2021-01-29
 */
public class TurtlePanel extends JPanel implements TurtleWorld {
    protected Collection<GraphicElement> elements;
    /** If in batch mode then does not repaint after each new line. */
    protected boolean batchMode = false;

    /** Create a new, thread-safe TurtlePanel. */
    public TurtlePanel() {
        this(true);
    }

    /**
     * Create a new, optionally thread-safe TurtlePanel. If thread-safe then
     * cached drawing commands are stored in a CopyOnWriteArray, which is safe
     * but slow during a long sequence of drawing commands.
     */
    public TurtlePanel(boolean threadSafe) {
        setBackground( Color.white );
        elements = threadSafe ? new CopyOnWriteArrayList<>() : new ArrayList<>();
    }
    
    /**
     * Suspend repainting after each new line until {@link #resumeRepaint()} is
     * called. Use when the TurtlePanel was created without being thread-safe.
     */
    public synchronized void suspendRepaint() {
        batchMode = true;
    }

    /** Resume repaint on new line behaviour and immediately repaint the panel. */
    public synchronized void resumeRepaint() {
        batchMode = false;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        Dimension size = getSize();
        if (!batchMode) { // Redraw all lines
            for (GraphicElement el : elements) {
                el.draw(g, size);
            }
        }
    }

    /** Clears all the lines drawn on the panel. */
    public void clear() {
        elements.clear();
        repaint();
    }

    /** Draws a line in pen colour between the given coordinates. */
    public void drawLine(int x1, int y1, int x2, int y2, Color pen) {
        elements.add( new LineSegment(x1, y1, x2, y2, pen) );
        if (! batchMode) {
            repaint();
        }
    }

    public Point flipYCoordinate(Point p) {
        p.y = getSize().height - p.y;
        return p;
    }

    protected static abstract class GraphicElement {
        abstract void draw(Graphics g, Dimension size);
    }

    protected static class LineSegment extends GraphicElement {
        private Point p1, p2;
        private Color c;

        LineSegment(int x1, int y1, int x2, int y2, Color c) {
            this.p1 = new Point(x1, y1);
            this.p2 = new Point(x2, y2);
            this.c = c;
        }

        void draw(Graphics g, Dimension size) {
            g.setColor(c);
            g.drawLine(p1.x, size.height - p1.y, p2.x, size.height - p2.y); //flips the y coordinate
        }
        
    }
}
