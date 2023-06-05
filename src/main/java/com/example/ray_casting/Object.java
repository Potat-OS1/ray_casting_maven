package com.example.ray_casting;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Object {
    private final ArrayList<Line> bounds = new ArrayList<>();
    private final Shape object;

    Object (Point2D[] points, Color c) {
        int a = 0;
        while (true) {
            try {
                bounds.add(new Line(points[a].getX(), points[a].getY(), points[a+1].getX(), points[a+1].getY()));
                a++;
            }
            catch (Exception ignored) {
                bounds.add(new Line(points[a].getX(), points[a].getY(), points[0].getX(), points[0].getY()));
                break;
            }
        }
        object = Tools.arrayToPolygon(points, c);
    }

    public Shape getObject () {
        return object;
    }

    public ArrayList<Line> getObjectBounds () {
        return bounds;
    }
}
