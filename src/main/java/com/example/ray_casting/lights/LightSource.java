package com.example.ray_casting.lights;

import com.example.ray_casting.Ray;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class LightSource {
    boolean isStationary;
    double originX;
    double originY;
    Color lightColor;
    final int strength;
    ArrayList<Ray> rays = new ArrayList<>();
    final int rayCount;
    protected Image lightDropOff;

    LightSource (int rayCount, double originX, double originY, Color c, boolean isStationary, int strength) {
        this.strength = strength;
        this.lightColor = c;
        this.isStationary = isStationary;
        this.rayCount = rayCount;
        this.originX = originX;
        this.originY = originY;
    }

    public Image getLightDropOff () {
        return lightDropOff;
    }

    public void setLightDropOff (Image ldo) {
        lightDropOff = ldo;
    }

    public ArrayList<Ray> getRays() {
        return rays;
    }

    public double[] getCoordinates () {
        return new double[]{originX, originY};
    }

    public void setCoordinates (double newX, double newY) {
        originX = newX;
        originY = newY;
    }

    public Color getLightColor () {
        return lightColor;
    }

    public int getStrength () {
        return strength;
    }

    public boolean stationaryStatus () {
        return isStationary;
    }
}
