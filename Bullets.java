import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class Bullets
{
    private List<Ammo> ammo;

    public Bullets()
    {   
        ammo = new ArrayList<Ammo>();
    }

    public void add(Ammo al)
    {
        ammo.add(al);
    }

    //post - draw each Ammo
    public void drawEmAll(Graphics window)
    {
        for(int x = 0; x < ammo.size(); x++)
        {
            ammo.get(x).draw(window);
        }
    }

    public void moveEmAll()
    {
        for(int x = 0; x < ammo.size(); x++)
        {
            ammo.get(x).move("SPACE");
        }
    }

    public void cleanEmUp()
    {
        for(int x = 0; x < ammo.size(); x++)
        {
            ammo.remove(x);
        }
    }

    public List<Ammo> getList()
    {
        return ammo;
    }

    public String toString()
    {
        return "";
    }
}
