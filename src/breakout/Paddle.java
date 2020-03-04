package breakout;

import breakout.Game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {

    private Game game;

    private static final int y = 255;
    public static final int width = 40;
    public static final int height = 6;
    int x = 0;
    int xa = 0;

    public synchronized void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public synchronized void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -2;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = 2;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - width)
            x = x + xa;
    }

    public void paint(Graphics2D g) {
        Color prevColor = g.getColor();
        g.setColor(Color.WHITE);

        g.fillRect(x, y, width, height);

        g.setColor(prevColor);
    }

    public Paddle(Game game) {
        this.game = game;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getTop() {
        return y;
    }
}
