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
    private static int temporaryCounter;
    private static int temporaryCounter2;
    private static int timerMultiply;

    public static int terrariumWidth =700;
    public static int terrariumHeight = 500;
    public static int spaceX = (SnakeGame.SCREEN_WIDTH -terrariumWidth) / 2;
    public static int spaceY = (SnakeGame.SCREEN_HEIGHT -terrariumHeight) / 2;


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
        food = new Food(random, this.terrariumWidth,this.terrariumHeight, SnakeGame.UNIT_SIZE);
        score = 0;
        running = true;
        timerMultiply =20;
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
            // Set terrarium limits

            //draw terrarium for snake
            // Snake is dangerous animal and must be closed in terrarium"

            // fill terrarium with squares for better orientation
            g.setColor(Color.blue);
            for (int x = spaceX; x < (spaceX+terrariumWidth) / SnakeGame.UNIT_SIZE; x++) {

            }
            g.setColor(Color.DARK_GRAY);
            for (int y = 0; y <= terrariumHeight / SnakeGame.UNIT_SIZE; y++) {
                int yCoord = spaceY + y * SnakeGame.UNIT_SIZE;
                g.drawLine(spaceX ,yCoord, spaceX + terrariumWidth, yCoord);
            }

            for (int x = 0; x <= terrariumWidth / SnakeGame.UNIT_SIZE; x++) {
                int xCoord = spaceX + x * SnakeGame.UNIT_SIZE;
                g.drawLine(xCoord ,spaceY, xCoord , spaceY + terrariumHeight);
            }

            // draw terrarium shapes
            g.setColor(Color.BLUE);
            g.drawLine(spaceX ,spaceY, spaceX , spaceY + terrariumHeight);
            g.drawLine(spaceX ,spaceY + terrariumHeight, spaceX + terrariumWidth , spaceY + terrariumHeight);

            g.drawLine(spaceX + terrariumWidth,spaceY, spaceX + terrariumWidth , spaceY + terrariumHeight);
            g.drawLine(spaceX ,spaceY, spaceX + terrariumWidth , spaceY);

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
// nebyl jsem schopny prenastavit timer interval
        // proto jsem nabastlil tuhle prasarnu.
        //Az se to naucim, tak to zrusim!
        if (++temporaryCounter> timerMultiply){
            if(++temporaryCounter2> 30-timerMultiply){
                temporaryCounter2=0;
                temporaryCounter=0;
                timerMultiply--;

            if(timerMultiply >1){
                timerMultiply--;
            }else {
                timerMultiply =1;
                // not necessary, but
                //better save than sorry
            }}

            if (running) {
                System.out.println(temporaryCounter);
                System.out.println(timerMultiply);
                temporaryCounter=0;
                snake.move();
                checkFoodCollision();
                checkCollisions();

            }
            repaint();
        }


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
        int terrariumTop=GamePanel.spaceY;
        int terrariumBottom=GamePanel.terrariumHeight+GamePanel.spaceY;
        int terrariumLeft=GamePanel.spaceX;
        int terrariumRight=GamePanel.terrariumWidth+GamePanel.spaceX;

        if (snake.getHeadX() < terrariumLeft || snake.getHeadX() >= terrariumRight ||
        snake.getHeadY() < terrariumTop || snake.getHeadY() >= terrariumBottom) {
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
            food.respawn(random, this.terrariumWidth, this.terrariumHeight, SnakeGame.UNIT_SIZE);
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