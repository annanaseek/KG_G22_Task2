package ru.vsu.cs.pixel_lines;

import ru.vsu.cs.LineDrawer;
import ru.vsu.cs.PixelDrawer;

import java.awt.*;

public class BresenheimLineDrawer implements LineDrawer {

    private PixelDrawer pd;

    public BresenheimLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int x = x1;
        int y = y1;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = (int) Math.signum(x2 - x1);
        int sy = (int) Math.signum(y2 - y1);
        boolean change = false;

        if (dy > dx) {
            int z = dx;
            dx = dy;
            dy = z;
            change = true;
        }

        int e = 2 * dy - dx;

        for (int i = 1; i <= dx; i++) {
            pd.colorPixel(x, y, Color.black);
            while (e >= 0) {
                if (change) x += sx;
                else y += sy;
                e -= 2 * dx;
            }
            if (change) y += sy;
            else x += sx;
            e += 2 * dy;
        }
        pd.colorPixel(x, y, Color.black);
    }
}
