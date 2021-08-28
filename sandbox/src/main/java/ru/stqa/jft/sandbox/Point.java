package ru.stqa.jft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }
    public double distance(Point p) {
        double x = this.x - p.x;
        double y = this.y - p.y;
        return Math.sqrt(x * x + y * y);
    }
}
