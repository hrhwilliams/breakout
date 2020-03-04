package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
// import javax.imageio.ImageIO;

import breakout.Ball;
import breakout.Paddle;
import breakout.Block;

public class Game extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final int HEIGHT = 300;
    public static final int WIDTH = 380;

    Ball ball = new Ball(this);
    Paddle paddle = new Paddle(this);
    List<Block> blocks = new ArrayList<Block>();

    private void init() {
        ball.x = (WIDTH / 2) - (Ball.width / 2);
        ball.y = Block.width * 4;
        paddle.x = (WIDTH / 2) - (Paddle.width / 2);

//        try {
//            BufferedImage img = ImageIO.read(new File("/bg.png"));
//        } catch (IOException e) {
//            // say uh-oh
//        }

        Color currColor = Color.red;
        for (int rows = 0; rows < 8; rows++) {
            if (rows == 0)
                currColor = Color.red;
            if (rows == 2)
                currColor = Color.orange;
            if (rows == 4)
                currColor = Color.yellow;
            if (rows == 6)
                currColor = Color.blue;

            for (int cols = 0; cols < WIDTH / Block.width; cols++) {
                blocks.add(new Block(cols * Block.width, rows * Block.height + 10, currColor));
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // background
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        ball.paint(g2d);
        paddle.paint(g2d);

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i).alive())
                blocks.get(i).paint(g2d);
        }
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public synchronized void keyTyped(KeyEvent e) {
            }

            @Override
            public synchronized void keyReleased(KeyEvent e) {
                paddle.keyReleased(e);
            }

            @Override
            public synchronized void keyPressed(KeyEvent e) {
                paddle.keyPressed(e);
            }
        });
        setFocusable(true);
        init();
    }

    private void move() {
        ball.move();
        paddle.move();
    }

    public void gameOver() {
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Breakout");
        Game game = new Game();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        while (true) {
            game.move();
            game.repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {

            }
        }
    }
}
