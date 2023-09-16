package kit101.turtle;

import java.awt.Point;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * The turtle graphics window.
 *
 * @version 2021-01-29
 */
public class TurtleWindow extends JFrame {
    /** Default window width. */
    public static final int DEFAULT_W = 500;
    /** Default window height. */
    public static final int DEFAULT_H = 500;

    /** Initial location of next {@code TurtleWindow}. */
    protected static Point pos = new Point(100, 100);

    protected TurtlePanel world;

    /**
     * Returns a TurtleWorld (actually a TurtlePanel) to use when creating
     * a Turtle.
     */
    public TurtleWorld getWorld() { return world; }

    /**
     * Creates a window with title "KIT101 Turtle" with the default width and
     * height.
     */
    public TurtleWindow() {
        this("KIT101 Turtle");
    }
    
    /**
     * Creates a window with the title "KIT101 Turtle" with the default width
     * and height, and where the thread-safe behaviour of the embedded
     * TurtleWorld is determined by the parameter threadSafe.
     */
    public TurtleWindow(boolean threadSafe) {
        this("KIT101 Turtle", DEFAULT_W, DEFAULT_H, threadSafe);
    }

    /**
     * Creates a window with the given title and default width and height.
     * The embedded TurtleWorld is created to be thread-safe.
     */
    public TurtleWindow(String title) {
        this(title, DEFAULT_W, DEFAULT_H, true);
    }

    /**
     * Creates a window with the given title and dimensions.
     */
    public TurtleWindow(String title, int width, int height, boolean threadSafe) {
        super(title);
        setSize(width, height);
        setLocation(pos);
        //So the next window won't be precisely covering this one
        pos.translate(20, 20);

        getContentPane().setLayout( new BorderLayout() );
        getContentPane().add( world = new TurtlePanel(threadSafe) );

        setDefaultCloseOperation( EXIT_ON_CLOSE );
    }
}
