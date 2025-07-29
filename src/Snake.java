// Snake.java

import java.awt.*;

public class Snake {
    private final int[] x;
    private final int[] y;
    private int bodyParts;
    private Direction direction = Direction.RIGHT;
    private final int unitSize;

    // Konštanta pre maximálny počet segmentov hada
    private static final int GAME_UNITS = 90000;

    public Snake(int initialSize, int unitSize) {
        this.unitSize = unitSize;
        x = new int[GAME_UNITS];
        y = new int[GAME_UNITS];
        bodyParts = initialSize;

        // Inicializácia počiatočnej pozície hada
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 200 - i * unitSize;
            y[i] = 250;
        }
    }

    public void move() {
        // Posun tela
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Posun hlavy podľa smeru
        switch (direction) {
            case UP:
                y[0] -= unitSize;
                break;
            case DOWN:
                y[0] += unitSize;
                break;
            case LEFT:
                x[0] -= unitSize;
                break;
            case RIGHT:
                x[0] += unitSize;
                break;
        }
    }

    public void draw(Graphics g) {
        // Vylepšená grafika hlavy
        g.setColor(new Color(0, 180, 0));
        g.fillRect(x[0], y[0], unitSize, unitSize);

        // Oči hada
        g.setColor(Color.BLACK);

        // Pravé oko
        g.fillRect(x[0] + unitSize / 4, y[0] + unitSize / 4, unitSize / 8, unitSize / 8);
        // Ľavé oko
        g.fillRect(x[0] + unitSize * 3 / 4 - unitSize / 8, y[0] + unitSize / 4, unitSize / 8, unitSize / 8);

        // Nakresli telo s rôznymi odtieňmi pre lepší efekt
        for (int i = 1; i < bodyParts; i++) {
            // Change color of snake according its length
            int red = (2 * i) % 256;
            int green = (150 - i * 6) % 256;
            if (green <= 0) {
                green += 200;
            }

            int blue = (0 + i * 6) % 256;
            g.setColor(new Color(red, green, blue));
            g.fillRect(x[i], y[i], unitSize, unitSize);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow() {
        bodyParts++;
    }

    public int getHeadX() {
        return x[0];
    }

    public int getHeadY() {
        return y[0];
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }
}