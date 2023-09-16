import java.awt.Color;
import kit101.turtle.Turtle;

/**
 * 2.1PP Turtle Graphics task to read some existing code that uses a
 * predefined class (the turtle graphics device) and then to modify
 * the code to change the colour of each shape that the turtle draws.
 *
 * @author Your name here
 */
public class RainbowTurtle {

    public static void main(String[] args) {
        Turtle t = new Turtle();
    
        /*************/
        t.setColor(Color.blue);
        t.penUp();
        t.turn(-135);
        t.move(310);
        t.turn(135);
        t.penDown();
        t.move(100);
        t.turn(90);
        System.out.println("At the end of the indicated section the Turtle object is:");
        System.out.println("Located at (" + t.getX() + ", " + t.getY() + ")");
        System.out.println("Pointing at an angle of " + t.getDirection() + " degrees");
        /*************/
    
        t.move(100);
        t.turn(90);
        t.move(100);
        t.turn(90);
        t.move(100);
        t.turn(180);
        t.penUp();
        t.setColor(Color.green);
        t.move(350);
        t.turn(-90);
        t.penDown();
        t.move(100);
        t.turn(120);
        t.move(100);
        t.turn(120);
        t.move(100);
        t.turn(120);
        t.penUp();
        t.move(300);
        t.setColor(Color.yellow);
        t.penDown();
        t.move(100);
        t.turn(90);
        t.move(50);
        t.turn(90);
        t.move(100);
        t.turn(90);
        t.move(50);
        t.penUp();
        t.move(240);
        t.setColor(Color.red);
        t.penDown();
        t.turn(45);
        t.move(150);
        t.penUp();
        t.turn(135);
        t.move(105);
        t.penDown();
        t.turn(135);
        t.move(150);
    }

}
