package ru.vsu.cs;

import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.ArcDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.LineDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PieDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.PixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.PrimitivesFactoryWithDefaultGraphicsImplementation;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;
import ru.vsu.cs.kg2020.nuzhnykh_a_v.task2.graphics_impl.pie.PieDrawerFactoryByPixelDrawer;
import ru.vsu.cs.pixel_lines.BresenheimLineDrawer;

public class MyFactoryImplementation extends PrimitivesFactoryWithDefaultGraphicsImplementation {
    @Override
    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return pd -> {
            LineDrawer ld = new BresenheimLineDrawer(pd);
            ArcDrawer arcDrawer = new BresenheimArcDrawer(pd);
            return arcDrawer;
        };
    }

    @Override
    protected PieDrawerFactoryByPixelDrawer getCustomPieDrawerFactory() {
        return pd -> {
            LineDrawer ld = new BresenheimLineDrawer(pd);
            PieDrawer pieDrawer = new BresenheimPieDrawer(pd, ld);
            return pieDrawer;
        };
    }
}
