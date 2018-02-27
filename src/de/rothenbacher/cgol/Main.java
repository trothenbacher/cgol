package de.rothenbacher.cgol;

import javax.swing.*;

/**
 * Created by thomasr on 16.05.15.
 */
public class Main {

    static int width;
    static int height;

    public static void main(String... args) {
        final Frame frame = new Frame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        width = frame.getWidth();
        height = frame.getHeight();
        frame.createScreen();

        long lastFrame =  System.currentTimeMillis();

        while (true) {
            long thisFrame =  System.currentTimeMillis();
            frame.repaint();

            double tslf = (thisFrame - lastFrame) / 1000.0;
            lastFrame = thisFrame;

            frame.update(tslf);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
