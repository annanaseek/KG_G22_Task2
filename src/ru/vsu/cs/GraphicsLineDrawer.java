package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;

import java.awt.*;

public class GraphicsLineDrawer implements LineDrawer {
    private Graphics g;

    public GraphicsLineDrawer(Graphics g) {
        this.g = g;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
    }
}