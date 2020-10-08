package ru.vsu.cs.pixel_lines;

import ru.vsu.cs.LineDrawer;
import ru.vsu.cs.PixelDrawer;

import java.awt.*;

public class
WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
            /*int x = x1;
            int y = y1;
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int e = 2 * dy - dx;
            float d;
            //SolidBrush b1, b2;

            for (int i = 1; i <= dx; i++)
            {
                d = -1F * e / (dy + dx) / 1.15F;
                if (e >= 0)
                {
                   *//* b1 = new SolidBrush(SetColor(1F / 2 - d));
                    b2 = new SolidBrush(SetColor(1F / 2 + d));*//*

                    pd.colorPixel(x, y + 1, Color.black);
                    y++;
                    e += -2 * dx + 2 * dy;
                }
                else
                {
                    *//*b1 = new SolidBrush(SetColor(1F / 2 + d));
                    b2 = new SolidBrush(SetColor(1F / 2 - d));
                    g.FillRectangle(b2, x, y, 1, 1);
                    g.FillRectangle(b1, x, y - 1, 1, 1);
                    *//*
                    pd.colorPixel(x, y + 1, Color.black);
                    pd.colorPixel(x, y - 1, Color.black);
                    e += 2 * dy;
                }
                x++;
                *//*b1.Dispose();
                b2.Dispose();*//*
            }*/

        Color c = Color.BLACK;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int signX = 1;
        int signY = 1;
        int x = x1;
        int y = y1;
        int err = 0;
        if (dx < 0) {
            signX = -1;
        }
        if (dy < 0) {
            signY = -1;
        }
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        int gradient;
        Color c1, c2;
        if (Math.abs(dx) >= Math.abs(dy)) {
            for (int i = 0; i <= dx; i++) {
                if (dx == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * err) / (2 * dx);
                }
                c1 = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255 - Math.abs(gradient));
                c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.abs(gradient));
                pd.colorPixel(x, y, c1);
                if (gradient > 0) {
                    pd.colorPixel(x, y + signY, c2);
                } else {
                    pd.colorPixel(x, y - signY, c2);
                }

                err += 2 * dy;
                if (err > dx) {
                    y += signY;
                    err -= 2 * dx;
                } else if (err < -dx) {
                    y -= signY;
                }
                x += signX;
            }
        } else {
            for (int i = 0; i <= dy; i++) {
                if (dy == 0) {
                    gradient = 255;
                } else {
                    gradient = (255 * err) / (2 * dy);
                }
                c1 = new Color(c.getRed(), c.getGreen(), c.getBlue(), 255 - Math.abs(gradient));
                c2 = new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.abs(gradient));
                pd.colorPixel(x, y, c1);
                if (gradient > 0) {
                    pd.colorPixel(x + signX, y, c2);
                } else {
                    pd.colorPixel(x - signX, y, c2);
                }

                err += 2 * dx;
                if (err > dy) {
                    x += signX;
                    err -= 2 * dy;
                } else if (err < -dy) {
                    x -= signX;
                }
                y += signY;

            }
        }

    }
}
