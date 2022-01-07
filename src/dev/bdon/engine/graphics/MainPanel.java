package dev.bdon.engine.graphics;

import dev.bdon.engine.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {

    private final List<Entity> entityList = new ArrayList<>();

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.red);
//        g2.drawRect(0,0,100,100);
//        g2.setColor(Color.blue);
//        g2.fillRect(200,0,100,100);
        for (Entity graphic : entityList) {
            graphic.draw(g2);
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }
}
