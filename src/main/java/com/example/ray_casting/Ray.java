package com.example.ray_casting;

public class Ray {
    Line l;
    double strength;
    double theta = 0;

    public Ray (double x1, double x2, double y1, double y2) {
        l = new Line(x1, x2, y1, y2);
        strength = l.calculateStrength();
    }

    public Ray (double x1, double y1, double theta, int strength) {
        l = new Line(x1, x1 + (strength * Math.cos(Math.toRadians(theta))), y1, y1 + (strength * Math.sin(Math.toRadians(theta))));
        this.strength = strength;
        this.theta = theta;
    }

    public double getStrength () {
        return strength;
    }

    public void recalculateEndPoint (double theta, int strength) {
        this.strength = strength;
        l.setEnd(l.x1 + (strength * Math.cos(Math.toRadians(theta))), l.y1 + (strength * Math.sin(Math.toRadians(theta))));
    }

    public void setEndPoint (double x2, double y2) {
        l.setEnd(x2, y2);
    }

    public void setOrigin (double x1, double y1) {
        l.setOrigin(x1, y1);
    }
}
