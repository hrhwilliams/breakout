package breakout;

import java.awt.Color;

public class Block extends Entity {

    protected boolean alive = true;

    public Block(int x, int y, Color c) {
        this.width = 20;
        this.height = 6;

        this.color = c;
        this.x = x;
        this.y = y;
    }

    public void tick() {

    }

    public void collision() {
        this.alive = false;
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
