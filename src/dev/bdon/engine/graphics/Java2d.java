package dev.bdon.engine.graphics;

import dev.bdon.engine.entity.EntitySupplier;
import dev.bdon.engine.events.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Java2d {

    private JFrame frame;
    private MainPanel panel;

    public void initialize(EntitySupplier entitySupplier) {
        frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        panel = new MainPanel(entitySupplier);
        panel.setBackground(Color.BLACK);
        contentPane.add(panel);
        frame.setSize(1280,720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.invalidate();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                Keyboard.INSTANCE.pressKey(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Keyboard.INSTANCE.releaseKey(e.getKeyCode());
            }
        });
    }

    public void draw() {
        panel.repaint();
    }
}
