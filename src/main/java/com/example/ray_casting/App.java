package com.example.ray_casting;

import com.example.ray_casting.lights.LightSource;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    public static ArrayList<LightSource> lights = new ArrayList<>();
    public static double mouseX, mouseY;
    public static ArrayList<Object> objects = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        Pane p = new Pane();
        Scene scene = new Scene(p, 320, 240);
        stage.setScene(scene);
        stage.show();

//        final int size = 100000;
//
//        ImageProcessingKernel ipk = new ImageProcessingKernel(size);
//        ipk.execute(Range.create(size));
//        float[] test = ipk.getSum();
//        ipk.dispose();
//        for (int i = 0; i < size; i++) {
//            System.out.println(test[i]);
//        }
    }

    public static void main(String[] args) {
        launch(args);

//
//        Kernel kernel = new Kernel(){
//            @Override public void run() {
//                int gid = getGlobalId();
//                sum[gid] = a[gid] + b[gid];
//            }
//        };
//
//        kernel.execute(Range.create(size));
//
//        for (int i = 0; i < size; i++) {
//            System.out.printf("%6.2f + %6.2f = %8.2f\n", a[i], b[i], sum[i]);
//        }
//
//        kernel.dispose();
    }
}