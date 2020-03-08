package breakout;

import java.awt.Color;
import breakout.Game;
import breakout.Block;

public class Ball extends Entity {

    private Game game;

    public double traj = 1.0;
    public int xa = 1;
    public int ya = 1;

    public Ball(Game game) {
        this.width = 6;
        this.height = 6;

        this.game = game;
        this.color = Color.black;
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
            y = game.paddle.getTop() - height;
            ya = -1;
        }

        for (int i = 0; i < game.blocks.size(); i++) {
            int collisionType = 0;
            Block block = game.blocks.get(i);
            if (!block.alive)
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

    public void tick() {
        if (paddleCollision()) {
            System.out.println("Collision w/ paddle");
        }
        move();
    }

    private boolean paddleCollision() {
        // return this.getBounds().intersects(game.paddle.getBounds());
        return collisionWith(game.paddle);
    }

    private boolean blockCollision(Block block) {
        return block.getBounds().intersects(this.getBounds());
    }
}
