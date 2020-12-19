package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer;

import java.awt.*;

public class BresenheimPieDrawer implements PieDrawer {

    private ru.vsu.cs.PixelDrawer pd;
    private LineDrawer ld;

    public BresenheimPieDrawer(PixelDrawer pd, LineDrawer ld) {
        this.pd = pd;
        this.ld = ld;
    }

    @Override
    public void drawPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color color) {
        Pie pie = new Pie(x, y, width, height, startAngle, arcAngle);
        int[] position = pie.getProcessed();
        int lastX = 0;
        for (int i = position.length - 1; i >= 0; i--) {
            while (lastX <= position[i]) {
                drawPiePixel(pie, lastX, i, color);
                if (i > 0 && position[i - 1] == position[i]) {
                    break;
                }
                lastX++;
            }
        }
        ld.drawLine(pie.getXcenter(), pie.getYcenter(), pie.getACosMaxAngle() + pie.getXcenter(), pie.getBSinMaxAngle() + pie.getYcenter());
        if (arcAngle < 2* Math.PI) {
            ld.drawLine(pie.getXcenter(), pie.getYcenter(), pie.getACosMinAngle() + pie.getXcenter(), pie.getBSinMinAngle() + pie.getYcenter());
        }
    }

    private void drawPiePixel(Pie pie, int x, int y, Color color) {
        colorPixel(pie, x, y, color);
        colorPixel(pie, -x, y, color);
        colorPixel(pie, -x, -y, color);
        colorPixel(pie, x, -y, color);
    }

    private void colorPixel(Pie pie, int x, int y, Color color) {
        if (!pie.isPointInside(x, y)) {
            pd.colorPixel(x + pie.getXcenter(), y + pie.getYcenter(), color);
        }
    }
}
