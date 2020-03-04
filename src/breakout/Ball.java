package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import breakout.Game;
import breakout.Block;

public class Ball {

    private Game game;

    public static final int height = 6;
    public static final int width = 6;
    int x = Game.WIDTH / 2;
    int y = 50;
    int xa = 1;
    int ya = 1;

    public Ball(Game game) {
        this.game = game;
    }

    void move() {
        if (x + xa < 0)
            xa = 1;
        if (x + xa > game.getWidth() - width)
            xa = -1;
        if (y + ya < 0)
            ya = 1;
        if (y + ya > game.getHeight() - width)
            game.gameOver();
        if (paddleCollision()) {
            ya = -1;
            y = game.paddle.getTop() - width;
        }

        for (int i = 0; i < game.blocks.size(); i++) {
            int collisionType = 0;
            Block block = game.blocks.get(i);
            if (!block.alive())
                continue;
            if (blockCollision(block)) {
                // 0 = collided with bottom
                // 1 = collided with top
                // 2 = collided with right side
                // 3 = collided with left side
                collisionType = block.getCollisionType(this);
                switch (collisionType) {
                case 0:
                    ya = 1;
                    y = block.getBot();
                    break;
                case 1:
                    ya = -1;
                    y = block.getTop() - width;
                    break;
                }

                block.collision();
            }
        }

        x += xa;
        y += ya;
    }

    public void paint(Graphics2D g) {
        Color prevColor = g.getColor();
        g.setColor(Color.WHITE);

        g.fillRect(x, y, width, height);

        g.setColor(prevColor);
    }

    private boolean paddleCollision() {
        return game.paddle.getBounds().intersects(getBounds());
    }

    private boolean blockCollision(Block block) {
        return block.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }
}
