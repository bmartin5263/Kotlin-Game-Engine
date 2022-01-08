package dev.bdon.engine.graphics;

import dev.bdon.engine.entity.Entity;
import dev.bdon.engine.entity.EntitySupplier;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final EntitySupplier entityList;

    public MainPanel(EntitySupplier entities) {
        this.entityList = entities;
    }

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Entity entity : entityList.getEntities()) {
            entity.draw(new AwtGraphics((Graphics2D) g2.create()));
        }
    }
}
