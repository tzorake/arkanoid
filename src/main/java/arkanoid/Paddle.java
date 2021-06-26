package arkanoid;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle extends Rectangle {
	private int velocityX = 10;
	
	Paddle(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = Game.PADDLE_WIDTH;
		this.height = Game.PADDLE_HEIGHT;
	}

	public void left()
	{
		x -= velocityX;
		if (x < 0 || x > Game.SCREEN_WIDTH - Game.PADDLE_WIDTH)
		{			
			x += velocityX;
		}
	}
	
	public void right()
	{
		x += velocityX;
		if (x < 0 || x > Game.SCREEN_WIDTH - Game.PADDLE_WIDTH)
		{
			x -= velocityX;
		}
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(ImageLoader.getImageLoader().getSprite(2), (int)getX(), (int)getY(), null);
	}
	
	public int getVelocityX() {
		return velocityX;
	}
}

