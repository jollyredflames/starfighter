import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
	private List<Alien> aliens;

	public AlienHorde(int size)
	{
	    aliens = new ArrayList<Alien>();
	}

	public void add(Alien al)
	{
	    aliens.add(al);
	}

	public void drawEmAll(Graphics window)
	{
	    for(int x = 0; x < aliens.size(); x++)
	    {
	        aliens.get(x).draw(window);
	    }
	}

	public void moveEmAll()
	{
	    for(int x = 0; x < aliens.size(); x++)
	    {
	        aliens.get(x).move("MOVE");
	    }
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
	    for(int x = 0; x < aliens.size(); x++)
	    {
	        aliens.remove(x);
	    }
	}

	public String toString()
	{
		return "";
	}
}
