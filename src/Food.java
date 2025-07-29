// Food.java

import java.awt.*;
import java.util.Random;

public class Food {
    private int x;
    private int y;
    private final int unitSize;

    public Food(Random random, int screenWidth, int screenHeight, int unitSize) {
        this.unitSize = unitSize;
        respawn(random, screenWidth, screenHeight, unitSize);
    }

    public void respawn(Random random, int screenWidth, int screenHeight, int unitSize) {
        x = random.nextInt(screenWidth / unitSize) * unitSize + GamePanel.spaceX;
        y = random.nextInt(screenHeight / unitSize) * unitSize + GamePanel.spaceY;
    }

    public void draw(Graphics g) {
        // Púhý kruh
        g.setColor(Color.RED);
        g.fillOval(x, y, unitSize, unitSize);

        // Odlesk na jablku
        g.setColor(Color.WHITE);
        g.fillOval(x + unitSize / 4, y + unitSize / 4, unitSize / 6, unitSize / 6);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}