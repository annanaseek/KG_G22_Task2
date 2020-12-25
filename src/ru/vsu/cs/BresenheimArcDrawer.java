package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.ArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;

import java.awt.*;

public class BresenheimArcDrawer implements ArcDrawer {
    private PixelDrawer pd;

    public BresenheimArcDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, double startAngle, double arcAngle, Color color) {
        Arc arc = new Arc(x, y, width, height, startAngle, arcAngle);
        int[] position = arc.getProcessed();
        int lastX = 0;
        for (int i = position.length - 1; i >= 0; i--) {
            while (lastX <= position[i]) {
                drawArcPixel(arc, lastX, i, color);
                if (i > 0 && position[i - 1] == position[i]) {
                    break;
                }
                lastX++;
            }
        }
    }

    private void drawArcPixel(Arc arc, int x, int y, Color color) {
        colorPixel(arc, x, y, color);
        colorPixel(arc, -x, y, color);
        colorPixel(arc, -x, -y, color);
        colorPixel(arc, x, -y, color);
    }

    private void colorPixel(Arc arc, int x, int y, Color color) {
        if (arc.isPointInside(x, y)) {
            pd.setPixel(arc.getXcenter() + x, arc.getYcenter() - y, color);
        }
    }


}
