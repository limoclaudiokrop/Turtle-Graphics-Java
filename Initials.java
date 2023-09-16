import kit101.turtle.Turtle;
import java.awt.Color;
/**
 * 2.1PP Turtle Graphics task to draw the author's initials.
 * Some of the code below has been _over_ commented to help
 * you understand what is happening.
 *
 * @author Your name here
 */
public class Initials {

    public static void main(String[] args) {
        Turtle painter; //the turtle graphics object

        painter = new Turtle(); //create the turtle

        //paint initials; remembering to use painter.penUp() to move without drawing a line...
        painter.setColor(Color.blue);
        //Draw K
        painter.penUp();
        painter.turn(180);
        painter.move(30);

        painter.turn(-90);
        painter.penDown();
        painter.move(50);
        painter.penUp();
        painter.turn(180);
        painter.move(25);
        painter.turn(45);
        painter.penDown();
        painter.move(35);
        painter.penUp();
        painter.turn(180);
        painter.move(35);
        painter.turn(-90);
        painter.penDown();
        painter.move(35);

        //Draw N
        painter.penUp();
        painter.turn(-45);
        painter.move(20);
        painter.penDown();
        painter.turn(-90);
        painter.move(50);
        painter.penUp();
        painter.turn(180);
        painter.move(50);
        painter.turn(-145);
        painter.penDown();
        painter.move(60);
        painter.turn(180);
        painter.turn(-35);
        painter.move(50);

    }

}
