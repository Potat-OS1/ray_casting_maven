package com.example.ray_casting;

import com.aparapi.Kernel;

public class ImageProcessingKernel extends Kernel {
    float[] sum;
    float[] a;
    float[] b;

    ImageProcessingKernel (int size) {
        sum = new float[size];
        a = new float[size];
        b = new float[size];
        for (int x = 0; x < size; x++) {
            a[x] = (float) (Math.random() * 100);
            b[x] = (float) (Math.random() * 100);
        }
    }

    @Override
    public void run() {
        int gid = getGlobalId();
        sum[gid] = a[gid] + b[gid];
    }

    public float[] getSum () {
        return sum;
    }
}
