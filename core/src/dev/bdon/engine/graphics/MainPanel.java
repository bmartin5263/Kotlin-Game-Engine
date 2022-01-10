package dev.bdon.engine.graphics;

import dev.bdon.engine.Engine;
import dev.bdon.engine.entity.Entity;
import dev.bdon.engine.entity.EntitySupplier;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        for (Entity entity : Engine.INSTANCE.entities()) {
            Graphics2D g2 = (Graphics2D) g.create();
            entity.draw(new AwtGraphics(g2));
            g2.dispose();
        }
    }
}
