package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer {
    private Graphics gr;

    GraphicsPixelDrawer(Graphics gr) {
        this.gr = gr;
    }


    @Override
    public void setPixel(int x, int y, Color c) {
        gr.setColor(c);
        gr.fillRect(x, y, 1, 1);
    }
}
