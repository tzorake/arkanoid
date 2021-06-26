package arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener
{
	public static final int BORDER = 30;

	public static final int WIDTH = 13;
	public static final int HEIGHT = 1;
	
	public static final int BRICK_WIDTH = 60;
	public static final int BRICK_HEIGHT = 30;
	
	public static final int BALL_SIZE = 20;
	
	public static final int PADDLE_WIDTH = 120;
	public static final int PADDLE_HEIGHT = 30;
	
	public static final int SCREEN_WIDTH = 2 * BORDER + WIDTH * BRICK_WIDTH - WIDTH;
	public static final int SCREEN_HEIGHT = 800;
	
	public static final int LIFEICON_WIDTH = 30;
	public static final int LIFEICON_HEIGHT = 30;
	
	public static final int FPS = 60;

	ImageLoader iml;
	Timer timer;
	
	Paddle paddle;
	Brick[] bricks;
	Ball ball;
	
	int score = 0;
	
	boolean running;
	boolean won;
	
	int lives;
	
	
	
	Game()
	{
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(new Color(0, 127, 127));
		this.setFocusable(true);
		this.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT:
						paddle.left();
						break;
						
					case KeyEvent.VK_RIGHT:
						paddle.right();
						break;
					
					case KeyEvent.VK_SPACE:
						ball.setStopped(false);
						break;
				}
			}
		});
		
		startGame();
	}
	
	
	private void startGame()
	{
		iml = ImageLoader.getImageLoader();
		
		paddle = new Paddle(SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2, SCREEN_HEIGHT - 2 * BORDER);
		
		bricks = new Brick[WIDTH * HEIGHT];
		for (int j = 0; j < HEIGHT; j++)
		{
			for (int i = 0; i < WIDTH; i++)
			{				
				bricks[j * WIDTH + i] = new Brick(BORDER + BRICK_WIDTH * i - i, BORDER + BRICK_HEIGHT * j - j);
					
			}
		}

		ball = new Ball(SCREEN_WIDTH / 2 - BALL_SIZE / 2, 700);
		
		lives = 2;
		
		timer = new Timer(1000/FPS, this);
		timer.start();
		
		running = true;
	}
	
	private void gameOver(Graphics g)
	{
		running = false;
		timer.stop();
		
		if (won)
		{
			this.setBackground(new Color(0, 255, 0));
			
			g.setColor(Color.BLACK);
			g.setFont( new Font("Roboto",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("You won!", SCREEN_WIDTH / 2 - metrics.stringWidth("You won!") / 2, SCREEN_HEIGHT/2 - g.getFont().getSize());
		}
		else 
		{
			this.setBackground(new Color(0, 0, 0));
			
			g.setColor(Color.RED);
			g.setFont( new Font("Roboto",Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Game over.", SCREEN_WIDTH / 2 - metrics.stringWidth("Game over.") / 2, SCREEN_HEIGHT/2 - g.getFont().getSize());
		}
	}
	
	private boolean intersects(Rectangle rect1, Rectangle rect2)
	{
		return rect1.getX() < rect2.getX() + rect2.getWidth() &&
			   rect1.getX() + rect1.getWidth() > rect2.getX() &&
			   rect1.getY() < rect2.getY() + rect2.getHeight() &&
			   rect1.getY() + rect1.getHeight() > rect2.getY();
	}
	
	private void update()
	{
		if (!ball.isStopped())
			ball.move();
		
		for (int i = 0; i < bricks.length; i++)
		{
			if (intersects(ball, bricks[i]) && !bricks[i].isHit())
			{
				ball.setVelocityY(-ball.getVelocityY());
				bricks[i].setHit(true);
				score++;
			}
		}
		
		if (intersects(ball, paddle))
		{
			ball.setVelocityY(-ball.getVelocityY());
		}
		
		if (ball.getY() < 0)
		{
			ball.setVelocityY(-ball.getVelocityY());
		}
		
		if (ball.getX() < 0 || ball.getX() >= SCREEN_WIDTH - BALL_SIZE)
		{
			ball.setVelocityX(-ball.getVelocityX());
		}
		
		if (ball.getY() >= SCREEN_HEIGHT - BALL_SIZE)
		{
			if (lives <= 0)
			{
				running = false;
			}
			reset();
		}
	}
	
	private void reset()
	{
		lives--;
		paddle = new Paddle(SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2, SCREEN_HEIGHT - 2 * BORDER);
		ball = new Ball(SCREEN_WIDTH / 2 - BALL_SIZE / 2, 700);
		
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) 
	{
		if (running)
		{
			ball.draw(g);
			paddle.draw(g);
		
			for (Brick b : bricks)
			{
				b.draw(g);
			}
			
			for (int i = 0; i < lives; i++)
			{
				g.drawImage(ImageLoader.getImageLoader().getSprite(1), i * LIFEICON_WIDTH, SCREEN_HEIGHT - LIFEICON_HEIGHT, null);
			}
			
			g.setColor(Color.black);
			g.setFont( new Font("Roboto",Font.BOLD, 16));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("score: " + score, 6, 2 + g.getFont().getSize());
			
			if (score == bricks.length)
			{
				won = true;
				running = false;
			}
		}
		else
		{
			gameOver(g);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		
		repaint();
	}
}