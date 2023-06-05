package com.example.ray_casting.lights;

import com.example.ray_casting.Ray;
import com.example.ray_casting.Tools;
import javafx.scene.paint.Color;

public class RadialLight extends LightSource{
    RadialLight(int rayCount, double originX, double originY, Color c, boolean isStationary, int strength) {
        super(rayCount, originX, originY, c, isStationary, strength);

        for (int a = 0; a < rayCount; a++) {
            rays.add(new Ray(originX, originY, (360 / a) * a, strength));
        }

        this.lightDropOff = Tools.imageGradientToBlack(lightColor, strength * 2, strength * 2, 120, 0);
    }
}
