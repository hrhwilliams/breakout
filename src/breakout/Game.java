package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import breakout.Ball;
import breakout.Paddle;
import breakout.Block;
import breakout.InputHandler;

@SuppressWarnings("serial")
public class Game extends JPanel {

    public static final int width = 400;
    public static final int height = 400;
    private boolean running = true;

    protected Ball ball;
    protected Paddle paddle;
    protected List<Block> blocks;
    protected InputHandler input;

    public Game() {
        setFocusable(true);
    }

    public void init() {
        final int blockWidth = 20;
        final int blockHeight = 6;

        input = new InputHandler(this);
        ball = new Ball(this);
        paddle = new Paddle(this, input);
        blocks = new ArrayList<Block>();

        ball.x = (width - ball.width) / 2;
        ball.y = 100;

        paddle.x = (width - paddle.width) / 2;

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

            for (int cols = 0; cols < (width / blockWidth); cols++) {
                blocks.add(new Block(cols * blockWidth, rows * blockHeight + 10, currColor));
            }
        }
    }

    public void run() {
        long prevTime = System.nanoTime();
        int ticks = 0;
        double unprocessedTicks = 0.0;
        double nsPerTick = 1000000000.0 / 60.0;
        long lastTimer = System.currentTimeMillis();

        init();

        while (running) {
            long currentTime = System.nanoTime();

            while (unprocessedTicks < 1) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentTime = System.nanoTime();
                unprocessedTicks = (currentTime - prevTime) / nsPerTick;
            }

            prevTime = currentTime;

            while (unprocessedTicks >= 1) {
                tick();
                repaint();
                unprocessedTicks--;
                ticks++;
            }

            long timeDelta = System.currentTimeMillis() - lastTimer;
            if (timeDelta > 1000) {
                lastTimer = System.currentTimeMillis();
                System.out.println("processed " + ticks + " ticks in " + timeDelta + " ms");
                ticks = 0;
            }
        }
    }

    public void tick() { //
        ball.tick();
        paddle.tick();

        blocks.stream().filter(block -> block.alive).forEach(block -> block.tick());
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

        blocks.stream().filter(block -> block.alive).forEach(block -> block.paint(g2d));
    }

    public void gameOver() {
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Breakout");
        Game game = new Game();
        frame.add(game);

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.run();
    }
}
