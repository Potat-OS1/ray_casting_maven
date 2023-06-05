package com.example.ray_casting;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Tools {
    public static Image imageGradientToBlack (Color c, int width, int height, int steps, double deadPercent) {
        int endSteps = (int) (steps / 4.0);
        int deadSteps = (int) (steps * deadPercent);
        int normalSteps = steps - endSteps - deadSteps;

        double deltaR = (c.getRed()) / (normalSteps);
        double deltaG = (c.getGreen()) / (normalSteps);
        double deltaB = (c.getBlue()) / (normalSteps);
        double centerX = Math.ceil(width/2.0);
        double centerY = Math.ceil(height/2.0);

        double currentLength;

        double stepSize = Math.sqrt(Math.pow(centerX - width, 2) + Math.pow(centerY - height, 2)) / (steps * 1.1);
        int currentStep;

        WritableImage wi = new WritableImage(width, height);
        PixelWriter pw = wi.getPixelWriter();

        double red;
        double blue;
        double green;
        double alpha;

        for (int x = 0; x < wi.getWidth(); x++) {
            for (int y = 0; y < wi.getHeight(); y++) {
                currentLength = Math.sqrt(Math.pow(centerX - (x+1), 2) + Math.pow(centerY - (y+1), 2));
                currentStep = (int) Math.floor(currentLength / stepSize);

                if (currentStep > normalSteps + deadSteps) {
                    //pw.setColor(x, y, Color.BLACK);
                    continue;
                }
                if (currentStep < deadSteps) {
                    pw.setColor(x, y, Color.TRANSPARENT);
                }
                else {
                    currentStep = currentStep - deadSteps;

                    red = Math.max((c.getRed() - (deltaR * currentStep)), 0);
                    blue = Math.max((c.getBlue() - (deltaB * currentStep)), 0);
                    green = Math.max((c.getGreen() - (deltaG * currentStep)), 0);
                    alpha = 0.15 + ((0.85/normalSteps) * currentStep);

                    if (red < 0) {
                        red = 0;
                    }
                    if (blue < 0) {
                        blue = 0;
                    }
                    if (green < 0) {
                        green = 0;
                    }

                    pw.setColor(x, y, new Color(red, green, blue, alpha));
                }

            }
        }
        return wi;
    }

    public static Shape arrayToPolygon (Point2D[] points, Color c) {
        Polygon polygon = new Polygon();
        polygon.setFill(c);
        ObservableList<Double> list = polygon.getPoints();
        for (Point2D point : points) {
            list.add(point.getX());
            list.add(point.getY());
        }
        return polygon;
    }

    public static Shape arrayListToPolygon (ArrayList<Point2D> points, Color c) {
        return arrayToPolygon(points.toArray(new Point2D[0]), c);
    }

    public static Point2D getShortestIntersection(com.example.ray_casting.Line line, ArrayList<com.example.ray_casting.Line> objectBounds) {
        Point2D shortestIntersection = null;
        double shortestDistance = Double.POSITIVE_INFINITY;

        for (com.example.ray_casting.Line bound : objectBounds) {
            if (line != bound) {
                Point2D intersection = getIntersection(line, bound);
                if (intersection != null) {
                    double distance = line.x1 - intersection.getX();
                    if (distance < 0) {
                        distance = line.x2 - intersection.getX();
                    }
                    if (distance >= 0 && distance < shortestDistance) {
                        shortestIntersection = intersection;
                        shortestDistance = distance;
                    }
                }
            }
        }

        return shortestIntersection;
    }

    private static Point2D getIntersection(com.example.ray_casting.Line line1, Line line2) {
        double x1 = line1.x1;
        double y1 = line1.y1;
        double x2 = line1.x2;
        double y2 = line1.x2;
        double x3 = line2.x1;
        double y3 = line2.y1;
        double x4 = line2.x2;
        double y4 = line2.y2;

        double denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denom == 0) {
            // Lines are parallel
            return null;
        }

        double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
        if (ua < 0 || ua > 1 || ub < 0 || ub > 1) {
            // Intersection is not on line segments
            return null;
        }

        double x = x1 + ua * (x2 - x1);
        double y = y1 + ua * (y2 - y1);

        return new Point2D(x, y);
    }
}
