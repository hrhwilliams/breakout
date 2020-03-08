package breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

abstract class Entity {

    public int width;
    public int height;
    public int x;
    public int y;

    public Color color;

    public void paint(Graphics2D g) {
        Color prevColor = g.getColor();
        g.setColor(this.color);

        g.fillRect(x, y, width, height);

        g.setColor(prevColor);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, height, width);
    }

    public boolean collisionWith(Entity e) {
        return this.getBounds().intersects(e.getBounds());
    }
}
