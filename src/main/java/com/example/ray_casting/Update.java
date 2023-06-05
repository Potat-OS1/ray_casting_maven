package com.example.ray_casting;

import com.example.ray_casting.lights.BeamLight;
import com.example.ray_casting.lights.ConeLight;
import com.example.ray_casting.lights.LightSource;
import com.example.ray_casting.lights.RadialLight;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Update extends AnimationTimer {
    private long lastUpdate;
    ArrayList<Shape> lightShapeList = new ArrayList<>();

    @Override
    public void handle (long now) {
        if (now - lastUpdate >= 16_666_666) {

            lastUpdate = now;
        }
    }

    private void updateShadows () {
        for (LightSource light : App.lights) {
            if (light instanceof RadialLight) {
                updateRadial(light);
                continue;
            }
            if (light instanceof BeamLight) {
                updateBeam(light);
                continue;
            }
            if (light instanceof ConeLight) {
                updateCone(light);
            }
        }
    }

    private void updateRadial (LightSource light) {
        lightShapeList.add(Tools.arrayListToPolygon(updateEndPoints(light), Color.RED));
    }

    private void updateBeam (LightSource light) {

    }

    private void updateCone (LightSource light) {

    }

    private ArrayList<Point2D> updateEndPoints (LightSource light) {
        boolean stationary = light.stationaryStatus();
        ArrayList<Point2D> lightPoints = new ArrayList<>();

        double[] point = new double[2];
        for (Ray ray : light.getRays()) {
            if (!stationary) {
                ray.setOrigin(App.mouseX, App.mouseY);
            }

            for (Object obj : App.objects) {
                point[0] = 0;
                point[1] = 0;
                Point2D shortestPoint = Tools.getShortestIntersection(ray.l, obj.getObjectBounds());

                if (point != null) {
                    point[0] = shortestPoint.getX();
                    point[1] = shortestPoint.getY();
                    ray.setEndPoint(point[0], point[1]);
                }
                else {
                    point[0] = ray.l.x2;
                    point[1] = ray.l.y2;
                }
            }

            lightPoints.add(new Point2D(point[0], point[1]));
        }
        return lightPoints;
    }
}
