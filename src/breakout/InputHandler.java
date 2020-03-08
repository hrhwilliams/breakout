package breakout;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import breakout.Game;

public class InputHandler implements KeyListener {

    public class Key {
        public boolean down = false;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean press) {
            if (press != down) {
                down = press;
            }
        }
    }

    public List<Key> keys = new ArrayList<Key>();
    public Key left = new Key();
    public Key right = new Key();

    public InputHandler(Game game) {
        game.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }

    private void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            right.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            left.toggle(pressed);

        if (e.getKeyCode() == KeyEvent.VK_D)
            right.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_A)
            left.toggle(pressed);
    }

}
