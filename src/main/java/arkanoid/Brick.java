package arkanoid;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick  extends Rectangle {
	private boolean hit;
	
	Brick(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = Game.BRICK_WIDTH;
		this.height = Game.BRICK_HEIGHT;
	}

	public void draw(Graphics g)
	{
		if (!isHit())
			g.drawImage(ImageLoader.getImageLoader().getSprite(0), (int)getX(), (int)getY(), null);
	}
	
	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}
}
