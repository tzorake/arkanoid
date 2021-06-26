package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends Rectangle {
	private int velocityX = 2, velocityY = 5; 
	private boolean stopped;

	Ball (int x, int y)
	{
		this.x = x;
		this.y = y;
		this.width = Game.BALL_SIZE;
		this.height = Game.BALL_SIZE;
		this.stopped = true;
		
	}
	
	public void move()
	{
		x += velocityX;
		y += velocityY;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
	}
	
	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
}
