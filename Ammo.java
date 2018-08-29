import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
    private int speed;

    public Ammo()
    {
        this(0, 0, 0);
    }

    public Ammo(int x, int y)
    {
        //add code
        super(x, y);
    }

    public Ammo(int x, int y, int s)
    {
        //add code
        setPos(x, y);
        setSpeed(s);
    }

    public void setSpeed(int s)
    {
       //add code
       speed = s;
    }

    public int getSpeed()
    {
       return speed;
    }

    public void draw(Graphics window)
    {
        //add code to draw the ammo
        window.setColor(Color.YELLOW);
        window.fillOval(getX(), getY(), 5, 5);
    }
    
    public void move(String direction)
    {
        //add code to draw the ammo
        setY(getY() - speed);
    }

    public String toString()
    {
        return "";
    }
}
