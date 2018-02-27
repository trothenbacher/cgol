package de.rothenbacher.cgol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thomasr on 16.05.15.
 */
public class Frame extends JFrame {

    private Screen screen;
    private Simulation simulation;
    private double tslu;

    Frame() {
        this.setUndecorated(true);
        this.setExtendedState(MAXIMIZED_BOTH);

        String input = JOptionPane.showInputDialog(this, "Cell size?");

        try {
            Cell.size = Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createScreen() {
        simulation = new Simulation();
        this.addMouseListener(simulation);
        this.addMouseMotionListener(simulation);
        this.addKeyListener(simulation);
        screen = new Screen();
        screen.setBounds(0, 0, Main.width, Main.height);
        this.add(screen);
    }

    public void update(final double tslf) {
        final double pausetime = 0.1F;
        tslu += tslf;
        if(tslu > pausetime) {
            simulation.update();
            tslu = 0;
        }
    }

    private class Screen extends JLabel {
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            simulation.draw(g);
        }
    }
}
