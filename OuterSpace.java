import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
public class OuterSpace extends Canvas implements KeyListener, Runnable
{
    private Ship ship;
    private Alien alienOne;
    private Alien alienTwo;
    private Ammo shot;
    //uncomment once you are ready for this part

    private int count1;
    private int count2;

    private AlienHorde horde;
    private Bullets shots;

    private boolean[] keys;
    private BufferedImage back;

    public OuterSpace()
    {
        setBackground(Color.black);

        keys = new boolean[5];

        //instantiate other instance variables
        //Ship, Alien

        ship = new Ship(380, 500, 50, 50, 7);
        alienOne = new Alien(100, 100, 30, 30, 4);
        alienTwo = new Alien(600, 100, 30, 30, 5);

        shot = new Ammo(ship.getX() + 22, ship.getY(), 10);
        count1 = 0;
        count2 = 0;

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    public void update(Graphics window)
    {
        paint(window);
    }

    public void paint(Graphics window, int x)
    {
        try
        {
            URL intro = getClass().getResource("./images/Gill.gif");
            Image introImage = ImageIO.read(intro);
            window.drawImage(introImage, 0, 0, 800, 600, null);
        }
        catch(Exception e)
        {
            System.out.println("Error 911: The FBI knows about this program and deleted a critical file!!");
        }
    }

    public void paint(Graphics window)
    {
        //set up the double buffering to make the game animation nice and smooth
        Graphics2D twoDGraph = (Graphics2D)window;

        //take a snap shot of the current screen and save it as an image
        //that is the exact same width and height as the current screen
        if(back == null)
        {
            back = (BufferedImage)(createImage(getWidth(), getHeight()));
        }

        //create a graphics reference to the back ground image
        //we will draw all changes on the background image
        Graphics graphToBack = back.createGraphics();
        graphToBack.setColor(Color.BLACK);
        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600);
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("StarFighter ", 25, 50);
        graphToBack.setColor(Color.BLUE);
        graphToBack.drawString("Kill 3 aliens", 600, 550);

        ship.draw(graphToBack);
        alienOne.draw(graphToBack);
        alienTwo.draw(graphToBack);

        if(keys[0] == true)
        {
            if(ship.getX() > 10 && ship.getX() < 800)
            {
                ship.move("LEFT");
            }
        }
        if(keys[1] == true)
        {
            if(ship.getX() > 0 && ship.getX() < 735)
            {
                ship.move("RIGHT");
            }
        }
        if(keys[2] == true)
        {
            if(ship.getY() > 10)
            {
                ship.move("UP");
            }
        }
        if(keys[3] == true)
        {
            if(ship.getY() < 510)
            {
                ship.move("DOWN");
            }
        }
        if(keys[4] == true )
        {
            shot = new Ammo(ship.getX() + 22, ship.getY(), 10);
            if(ship.getX() == 380 && ship.getY() == 500)
            {
                shot.draw(graphToBack);
            }
        }

        if(ship.getX() != 380 || ship.getY() != 500)
        {
            shot.draw(graphToBack);
        }

        //add code to move Ship, Alien, etc.
        shot.move("SPACE");
        alienOne.move("DOWN");
        alienTwo.move("DOWN");

        if(alienOne.getY() >= 600)
        {
            alienOne.setY(-10);
            alienOne.setX(randomX());
            alienOne.setSpeed(randomSpeed());
            if(alienOne.getX() <= 0 || alienOne.getX() >= 800)
            {
                alienOne.setX(randomX());
                alienOne.setSpeed(randomSpeed());
            }
        }
        if(alienTwo.getY() >= 600)
        {
            alienTwo.setY(-10);
            alienTwo.setX(randomX());
            alienTwo.setSpeed(randomSpeed());
            if(alienTwo.getX() <= 0 || alienTwo.getX() >= 800)
            {
                alienTwo.setX(randomX());
                alienTwo.setSpeed(randomSpeed());
            }
        }

        //add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship

        if(shot.getX() >= alienOne.getX() && shot.getX() <= alienOne.getX() + 50)
        {
            if(shot.getY() >= alienOne.getY() && shot.getY() <= alienOne.getY() + 50)
            {
                alienOne.setX(-100);
                count1++;
            }
        }
        if(shot.getX() >= alienTwo.getX() && shot.getX() <= alienTwo.getX() + 50)
        {
            if(shot.getY() >= alienTwo.getY() && shot.getY() <= alienTwo.getY() + 50)
            {
                alienTwo.setX(-100);
                count2++;
            }
        }

        if(alienOne.getX() <= ship.getX() && ship.getX() <= alienOne.getX() + 50)
        {
            if(alienOne.getY() <= ship.getY() && ship.getY() <= alienOne.getY() + 50)
            {
                ship.setX(-100);
            }
        }
        if(alienTwo.getX() <= ship.getX() && ship.getX() <= alienTwo.getX() + 50)
        {
            if(alienTwo.getY() <= ship.getY() && ship.getY() <= alienTwo.getY() + 50)
            {
                ship.setX(-100);
            }
        }

        if(ship.getX() < 0)
        {
            alienOne.setX(-150);
            alienTwo.setX(-150);
            alienOne.setSpeed(0);
            alienTwo.setSpeed(0);
            
            try
            {
                URL url = getClass().getResource("./images/Gill.gif");
                Image image = ImageIO.read(url);
                graphToBack.drawImage(image, 0, 0, 800, 600, null);
            }
            catch(Exception e)
            {
                System.out.println("Error 8-8: The CIA has captured the alien and taken him to Area 51!");
            }
            graphToBack.setColor(Color.BLACK);
            graphToBack.setFont(new Font("Stencil", Font.PLAIN, 50));
            graphToBack.drawString("You Failed Me :(", 250, 500);
        }

        if(count1 == 3)
        {
            alienOne.setX(-150);
            alienOne.setSpeed(0);
        }
        if(count2 == 3)
        {
            alienTwo.setX(-150);
            alienTwo.setSpeed(0);
        }
        if(count1 == 3 && count2 == 3)
        {
            graphToBack.setColor(Color.RED);
            graphToBack.drawString("Mission Complete", 350, 300);
            ship.setX(900);
        }

        twoDGraph.drawImage(back, null, 0, 0);
    }

    public int randomX()
    {
        double a = Math.random()*735;
        int ab = (int)a;
        return ab;
    }

    public int randomSpeed()
    {
        double a = Math.random()*5;
        int ab = (int)a + 3;
        return ab;
    }

    public void keyPressed(KeyEvent key)
    {
        if (key.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = true;
        }
        if (key.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = true;
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
        //no code needed here
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch(Exception e)
        {

        }
    }
}

