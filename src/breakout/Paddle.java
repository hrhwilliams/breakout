package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Game;
import breakout.InputHandler;

public class Paddle extends Entity {

    private Game game;
    private InputHandler input;

    public int paddleSpeed = 3;
    public int xa = 0;

    public Paddle(Game game, InputHandler input) {
        this.width = 40;
        this.height = 6;
        this.y = Game.height - 60;

        this.game = game;
        this.input = input;
    }

    public void tick() {

        if (input.left.down)
            xa = -paddleSpeed;
        else if (input.right.down)
            xa = paddleSpeed;
        else
            xa = 0;

        move();
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - width)
            x += xa;
    }

    public void paint(Graphics2D g) {
        Color prevColor = g.getColor();
        g.setColor(Color.black);

        g.fillRect(x, y, width, height);

        g.setColor(prevColor);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }

    public int getTop() {
        return y;
    }
}
