package arkanoid;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader {

	private static ImageLoader instance;
	
	private BufferedImage[] sprites;
	
	private ImageLoader()
	{
		loadSprites();
	}
	
	public static ImageLoader getImageLoader()
	{
		if (instance == null)
		{
			instance = new ImageLoader();				
		}
		return instance;
	}
	
	public void loadSprites()
	{
		sprites = new BufferedImage[3];
		
		try
		{
			URL url = getClass().getClassLoader().getResource("sprites/sprites.png");
			BufferedImage loadedSprites = ImageIO.read(url);
			
			// sprites[0] = loadedSprites.getSubimage(0, 0, 90, 90);
			
			sprites[0] = loadedSprites.getSubimage(0, 0, 60, 30);
			sprites[1] = loadedSprites.getSubimage(60, 0, 30, 30);
			sprites[2] = loadedSprites.getSubimage(0, 30, 120, 30);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int n) throws IllegalArgumentException
	{
		return sprites[n];
	}
		
}
