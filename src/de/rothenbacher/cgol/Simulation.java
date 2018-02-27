package de.rothenbacher.cgol;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * Created by thomasr on 16.05.15.
 */
public class Simulation implements MouseMotionListener, MouseListener, KeyListener {

    private Cell[][] cells;
    private Random random;
    private int generation;
    private int width = Main.width / Cell.size;
    private int height = Main.height / Cell.size;
    private boolean go;

    public Simulation() {
        random = new Random();
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
                cells[x][y].setAlive(random.nextBoolean());
            }
        }
    }

    public void update() {
        if (go) {
            generation++;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int aliveCounter = 0;
                    int mx = x - 1;
                    int my = y - 1;
                    int gx = (x + 1) % width;
                    int gy = (y + 1) % height;

                    if (mx < 0) {
                        mx = width - 1;
                    }

                    if (my < 0) {
                        my = height - 1;
                    }

                    if (cells[mx][my].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[mx][y].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[mx][gy].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[x][my].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[x][gy].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[gx][my].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[gx][y].isAlive()) {
                        aliveCounter++;
                    }
                    if (cells[gx][gy].isAlive()) {
                        aliveCounter++;
                    }

                    //rules
                    if (aliveCounter < 2 || aliveCounter > 3) {
                        cells[x][y].setNextRound(false);
                    } else if (aliveCounter == 3) {
                        cells[x][y].setNextRound(true);
                    }

                }
            }

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    cells[x][y].nextRound();
                }
            }
        }
    }

    public void draw(final Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].draw(g);
            }
        }
        g.setColor(Color.RED);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        g.drawString(Integer.toString(generation), 10, (10 + g.getFont().getSize()));
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (!go) {
            int mx = e.getX() / Cell.size;
            int my = e.getY() / Cell.size;
            cells[mx][my].setAlive(true);

        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseEntered(final MouseEvent e) {

    }

    @Override
    public void mouseExited(final MouseEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {

    }

    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {

    }

    @Override
    public void keyReleased(final KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            go = !go;
        }
    }
}
