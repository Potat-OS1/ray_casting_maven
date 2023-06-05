package com.example.ray_casting;

public class Line {
    double x1, x2, y1, y2;

    Line (double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void setOrigin (double x1, double y1) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public void setEnd (double x2, double y2) {
        this.y1 = y1;
        this.y2 = y2;
    }

    public double calculateStrength () {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }
}
