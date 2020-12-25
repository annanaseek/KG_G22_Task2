package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.ArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.testing.TestArcs;
import ru.vsu.cs.pixel_lines.BresenheimLineDrawer;

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
        try {
            TestArcs.startTest(
                    new MyFactoryImplementation(),
                    TestArcs.IMG_DIFF,
                    TestArcs.TEST_PIE | TestArcs.TEST_ARC,
                    true,
                    "C:\\Users\\79525\\IdeaProjects\\кг\\KG_G22_Task2\\src\\ru\\vsu\\cs\\img"
                    );
        } catch (Exception e) {
            System.out.println("oshibka");
        }
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gr = bi.createGraphics();

        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);

        PixelDrawer pd = new GraphicsPixelDrawer(gr);
        LineDrawer ld = new BresenheimLineDrawer(pd);
        drawAll(pd, ld);

        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }

    private void drawSnowFlake(LineDrawer ld, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double a = da * i;
            double dx = r * Math.cos(a);
            double dy = r * Math.sin(a);
            ld.drawLine(x, y, x + (int) dx, y + (int) dy, Color.cyan);
        }
    }

    private void drawArc(PixelDrawer pd, LineDrawer ld) {
        ArcDrawer bresenheimArcDrawer = new BresenheimArcDrawer(pd);
        bresenheimArcDrawer.drawArc(400, 250, 300, 150, 0.5 * Math.PI,  Math.PI, Color.black);
    }

    private void drawPie(PixelDrawer pd, LineDrawer ld) {
        PieDrawer bresenheimPieDrawer = new BresenheimPieDrawer(pd, ld);
        bresenheimPieDrawer.drawPie(100, 100, 300, 150, 0, 1.7*Math.PI, Color.black);
    }

    private void drawAll(PixelDrawer pd, LineDrawer ld) {
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
