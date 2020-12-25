package ru.vsu.cs;

public class Arc {
    private int x, y, width, height, xcenter, ycenter, a, b, ACosMinAngle, ACosMaxAngle, BSinMinAngle, BSinMaxAngle;
    private double minAngle, maxAngle,
            sinMinAngle, sinMaxAngle, cosMinAngle, cosMaxAngle, arcAngle;
    private final double TOO_SMALL = .001;
    private int[] processed;


    Arc(int x, int y, int width, int height, double startAngle, double arcAngle) {
//        startAngle -= 0.5 * Math.PI;
        this.x = x;
        this.y = y;
        this.arcAngle = arcAngle;
        this.width = width;
        this.height = height;
        a = width / 2;
        b = height / 2;
        xcenter = x + a;
        ycenter = y + b;
        double endAngle = startAngle + arcAngle;
        minAngle = Math.min(startAngle, endAngle);
        maxAngle = Math.max(startAngle, endAngle);
        sinMinAngle = Math.sin(minAngle);
        sinMaxAngle = Math.sin(maxAngle);
        cosMinAngle = Math.cos(minAngle);
        cosMaxAngle = Math.cos(maxAngle);
        ACosMaxAngle = (int) (a * cosMaxAngle);
        ACosMinAngle = (int) (a * cosMinAngle);
        BSinMaxAngle = (int) (b * sinMaxAngle);
        BSinMinAngle = (int) (b * sinMinAngle);
        processed = process();
    }

    private int[] process() {
        int x = 0;
        int y = b;
        int[] result = new int[b + 1];
        int err = 4 * b * b * ((x + 1) * (x + 1)) + a * a * ((2 * y - 1) * (2 * y - 1)) - 4 * a * a * b * b; // Функция координат точки (x+1, y-1/2)
        while (a * a * (2 * y - 1) > 2 * b * b * (x + 1)) { // Первая часть дуги
            result[y] = x++;
            if (err < 0) { // Переход по горизонтали
                err += 4 * b * b * (2 * x + 3);
            } else { // Переход по диагонали
                err = err - 8 * a * a * (y - 1) + 4 * b * b * (2 * x + 3);
                y--;
            }
        }
        err = b * b * ((2 * x + 1) * (2 * x + 1)) + 4 * a * a * ((y + 1) * (y + 1)) - 4 * a * a * b * b; // Функция координат точки (x+1/2, y-1)
        while (y + 1 != 0) { // Вторая часть дуги, если не выполняется условие первого цикла, значит выполняется a^2(2y - 1) <= 2b^2(x + 1)
            result[y--] = x;
            if (err < 0) { // Переход по вертикали
                err += 4 * a * a * (2 * y + 3);
            } else {  // Переход по диагонали
                err = err - 8 * b * b * (x + 1) + 4 * a * a * (2 * y + 3);
                x++;
            }
        }
        return result;
    }

    public boolean isPointInside(int x, int y) {
        if (arcAngle < TOO_SMALL)
            return false;
        if (Math.abs(arcAngle - 2 * Math.PI) < TOO_SMALL)
            return true;

        double angle = (Math.atan2(y / (double) b, x / (double) a) + Math.PI * 2) % (Math.PI * 2);
        return minAngle <= angle && angle <= maxAngle
                || minAngle <= angle + Math.PI * 2 && angle + Math.PI * 2 <= maxAngle;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    int getXcenter() {
        return xcenter;
    }

    int getYcenter() {
        return ycenter;
    }

    int getA() {
        return a;
    }

    int getB() {
        return b;
    }

    double getMinAngle() {
        return minAngle;
    }

    double getMaxAngle() {
        return maxAngle;
    }

    double getSinMinAngle() {
        return sinMinAngle;
    }

    double getSinMaxAngle() {
        return sinMaxAngle;
    }

    double getCosMinAngle() {
        return cosMinAngle;
    }

    double getCosMaxAngle() {
        return cosMaxAngle;
    }

    int getACosMinAngle() {
        return ACosMinAngle;
    }

    int getACosMaxAngle() {
        return ACosMaxAngle;
    }

    int getBSinMinAngle() {
        return BSinMinAngle;
    }

    int getBSinMaxAngle() {
        return BSinMaxAngle;
    }

    public int[] getProcessed() {
        return processed;
    }
}
