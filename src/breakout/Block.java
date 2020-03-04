package breakout;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;

import breakout.Ball;

public class Block {

	private boolean alive = true;
	public static final int width = 20;
	public static final int height = 8;
	public int x, y;
	private Color color;

	public Block(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void paint(Graphics2D g) {
		Color prevColor = g.getColor();

		g.setColor(this.color);
		g.fillRect(x, y, width, height);

		g.setColor(prevColor);
	}
	
	public void collision() {
		this.alive = false;
	}
	
	public boolean alive() {
		return this.alive;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public int getCollisionType(Ball ball) {
		if (ball.y > y)
			return 0;
		else
			return 1;
	}
	
	public int getTop() {
		return y;
	}
	
	public int getBot() {
		return y + height;
	}
}
