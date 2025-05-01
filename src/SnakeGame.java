import javax.swing.*;

public class SnakeGame extends JFrame {
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 20;
    public static final int DELAY = 100;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new);
    }

    public SnakeGame() {
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(new GamePanel());
    }
}
