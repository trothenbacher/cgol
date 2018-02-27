package de.rothenbacher.cgol;

import java.awt.*;

/**
 * Created by thomasr on 16.05.15.
 */
public class Cell {

    protected static int size = 10;
    private int x, y;
    private boolean alive, nextRound;


    public Cell(final int x, final int y) {
        this.x = x;
        this.y = y;

    }

    public void nextRound() {
        alive = nextRound;
    }

    public void draw(final Graphics g) {
        g.drawRect(x*size, y*size, size, size);
        g.fillRect(x*size, y*size, size-1, size-1);
        if(alive) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean isAlive) {
        this.alive = isAlive;
    }

    public boolean isNextRound() {
        return nextRound;
    }

    public void setNextRound(final boolean nextRound) {
        this.nextRound = nextRound;
    }
}
