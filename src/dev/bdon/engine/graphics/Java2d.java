package dev.bdon.engine.graphics;

import dev.bdon.engine.Keyboard;
import dev.bdon.engine.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Java2d {

    private JFrame frame;
    private MainPanel panel;

    public List<Entity> initialize() {
        frame = new JFrame();
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        panel = new MainPanel();
        panel.setBackground(Color.BLACK);
        contentPane.add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.invalidate();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                Keyboard.INSTANCE.pressKeyImpl(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Keyboard.INSTANCE.releaseKeyImpl(e.getKeyCode());
            }
        });
        return panel.getEntityList();
    }

    public void draw() {
        panel.repaint();
    }
}
