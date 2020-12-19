package ru.vsu.cs;

import ru.vsu.cs.pixel_lines.BresenheimLineDrawer;
import ru.vsu.cs.pixel_lines.DDALineDrawer;
import ru.vsu.cs.pixel_lines.WuLineDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener {
    private Point2D position = new Point(0, 0);

    DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bi.createGraphics();

        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        //gr.setColor(new Color(204, 99, 200));

        PixelDrawer pd = new GraphicsPixelDrawer(gr);
       // LineDrawer ld = new DDALineDrawer(pd);
        LineDrawer ld = new BresenheimLineDrawer(pd);
//        LineDrawer ld = new WuLineDrawer(pd);
        drawAll(ld, pd);

        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }

    private void drawSnowFlake(LineDrawer ld, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double dx = r * Math.cos(a);
            double dy = r * Math.sin(a);
            ld.drawLine(x, y, x + (int) dx, y + (int) dy);
        }
    }

    private void drawArc(PixelDrawer pd, LineDrawer ld) {
        BresenheimArcDrawer bresenheimArcDrawer = new BresenheimArcDrawer(pd, ld);
        bresenheimArcDrawer.drawArc(400, 250, 300, 150, 0, 1.7*Math.PI, Color.black);
    }

    private void drawPie(PixelDrawer pd, LineDrawer ld) {
        BresenheimPieDrawer bresenheimPieDrawer = new BresenheimPieDrawer(pd, ld);
        bresenheimPieDrawer.drawPie(100, 100, 300, 150, 0, 1.7*Math.PI, Color.black);
    }

    private void drawAll(LineDrawer ld, PixelDrawer pd) {
        //drawSnowFlake(ld, getWidth() / 2, getHeight() / 2, 150, 28);
        //ld.drawLine(getWidth() / 2, getHeight() / 2, (int) position.getX(), (int) position.getY());
        drawArc(pd, ld);
        drawPie(pd, ld);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point (e.getX(), e.getY());
        repaint();
    }
}
