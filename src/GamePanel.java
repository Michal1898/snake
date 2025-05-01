import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

// Pridať na koniec SnakeGame.java
public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;
    private boolean running = false;
    private Snake snake;
    private Food food;
    private Random random;
    private int score;
    private boolean gameOver;

    public GamePanel() {
        random = new Random();
        score = 0;
        gameOver = false;
        this.setPreferredSize(new Dimension(SnakeGame.SCREEN_WIDTH, SnakeGame.SCREEN_HEIGHT)); // PRIDAJTE TOTO
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        snake = new Snake(3, SnakeGame.UNIT_SIZE);
        food = new Food(random, SnakeGame.SCREEN_WIDTH,SnakeGame.SCREEN_HEIGHT, SnakeGame.UNIT_SIZE);
        score = 0;
        running = true;
        timer = new Timer(SnakeGame.DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Vykreslenie mriežky (voliteľné, pomáha pri vývoji)
            g.setColor(Color.DARK_GRAY);
            for (int i = 0; i < SnakeGame.SCREEN_WIDTH/ SnakeGame.UNIT_SIZE; i++) {
                g.drawLine(i * SnakeGame.UNIT_SIZE, 0, i * SnakeGame.UNIT_SIZE, SnakeGame.SCREEN_WIDTH);
                g.drawLine(0, i * SnakeGame.UNIT_SIZE, SnakeGame.SCREEN_WIDTH, i * SnakeGame.UNIT_SIZE);
            }

            food.draw(g);

            snake.draw(g);

            // Zobraz skóre
            g.setColor(Color.WHITE);
            g.setFont(new Font("Calibri", Font.BOLD, 20));
            g.drawString("Score: " + score, 10, 30);
        } else {
            gameOver(g);
        }
    }

    // Pridať metódu pre Game Over obrazovku
    private void gameOver(Graphics g) {
        // Text "Game Over"
        g.setColor(Color.RED);
        g.setFont(new Font("Calibri", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SnakeGame.SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SnakeGame.SCREEN_WIDTH / 2);

        // Zobraz skóre
        g.setColor(Color.WHITE);
        g.setFont(new Font("Calibri", Font.BOLD, 30));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (SnakeGame.SCREEN_WIDTH - metrics.stringWidth("Score: " + score)) / 2, SnakeGame.SCREEN_WIDTH / 2 + 50);

        // Reštart
        g.setFont(new Font("Calibri", Font.BOLD, 20));
        metrics = getFontMetrics(g.getFont());
        g.drawString("Press SPACE to play again", (SnakeGame.SCREEN_WIDTH - metrics.stringWidth("Press SPACE to play again")) / 2, SnakeGame.SCREEN_WIDTH / 2 + 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkFoodCollision();
            checkCollisions();
        }
        repaint();
    }

    // Pridať metódu pre kontrolu kolízií
    private void checkCollisions() {
        // Kontrola kolízie s telom
        for (int i = 1; i < snake.getBodyParts(); i++) {
            if (snake.getHeadX() == snake.getX(i) && snake.getHeadY() == snake.getY(i)) {
                running = false;
                gameOver = true;
            }
        }

        // Kontrola kolízie so stenou
        if (snake.getHeadX() < 0 || snake.getHeadX() >= SnakeGame.SCREEN_WIDTH ||
                snake.getHeadY() < 0 || snake.getHeadY() >= SnakeGame.SCREEN_HEIGHT) {
            running = false;
            gameOver = true;
        }

        if (!running) {
            timer.stop();
        }
    }

    // Pridať metódu pre kontrolu kolízie s jedlom
    private void checkFoodCollision() {
        if (snake.getHeadX() == food.getX() && snake.getHeadY() == food.getY()) {
            snake.grow();
            food.respawn(random, SnakeGame.SCREEN_WIDTH, SnakeGame.SCREEN_HEIGHT, SnakeGame.UNIT_SIZE);
            score++;
        }
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (gameOver) {
                        startGame();
                    }
                    break;
            }
        }
    }
}