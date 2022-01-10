package dev.bdon.engine.graphics;

import dev.bdon.engine.entity.EntitySupplier;
import dev.bdon.engine.events.AwtKeySource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Java2d {

    private JFrame frame;
    private MainPanel panel;
    public static boolean closed = false;

    public void initialize() {
        frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        panel = new MainPanel();
        panel.setBackground(Color.BLACK);
        contentPane.add(panel);
        frame.setSize(1280,720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing");
                closed = true;
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Closed");
                closed = true;
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        frame.invalidate();
    }

    public AwtKeySource getKeySource() {
        AwtKeySource awtKeySource = new AwtKeySource();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                awtKeySource.pressKey(e.getKeyCode());
            }
            @Override
            public void keyReleased(KeyEvent e) {
                awtKeySource.releaseKey(e.getKeyCode());
            }
        });
        return awtKeySource;
    }

    public void draw() {
        panel.repaint();
    }
}
