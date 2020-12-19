package ru.vsu.cs.pixel_lines;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;

import java.awt.*;

public class
WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int dx = x2 - x1;
        int dy = y2 - y1;
        int signX = (int)Math.signum(dx);
        int signY = (int)Math.signum(dy);
        int x = x1;
        int y = y1;
        int e = 0;

        dx = Math.abs(dx);
        dy = Math.abs(dy);
        int gradient;
        Color c1, c2;
        if (Math.abs(dx) >= Math.abs(dy)) {
            for (int i = 0; i <= dx; i++) {
                if (dx == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * e) / (2 * dx);
                }
                c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - Math.abs(gradient));
                c2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.abs(gradient));
                pd.setPixel(x, y, c1);
                if (gradient > 0) {
                    pd.setPixel(x, y + signY, c2);
                } else {
                    pd.setPixel(x, y - signY, c2);
                }

                e += 2 * dy;
                if (e > dx) {
                    y += signY;
                    e -= 2 * dx;
                } else if (e < -dx) {
                    y -= signY;
                }
                x += signX;
            }
        } else {
            for (int i = 0; i <= dy; i++) {
                if (dy == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * e) / (2 * dy);
                }
                c1 = new Color(color.getRed(), color.getGreen(), color.getBlue(), 255 - Math.abs(gradient));
                c2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.abs(gradient));
                pd.setPixel(x, y, c1);
                if (gradient > 0) {
                    pd.setPixel(x + signX, y, c2);
                } else {
                    pd.setPixel(x - signX, y, c2);
                }

                e += 2 * dx;
                if (e > dy) {
                    x += signX;
                    e -= 2 * dy;
                } else if (e < -dy) {
                    x -= signX;
                }
                y += signY;

            }
        }

    }
}
