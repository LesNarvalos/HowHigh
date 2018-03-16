package draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import m2dl.com.howhigh.R;
import manager.GameItem;

/**
 * Created by hichem on 16/03/2018.
 */

public class DrawApliniste implements GameItem {
    SurfaceHolder holder;
    Thread thread = null;
    boolean isRunning = true;
    Bitmap alpiniste;
    float sensorX,sensorY;
    public DrawApliniste(Context context) {
        //super(context);
        alpiniste = BitmapFactory.decodeResource(context.getResources(), R.drawable.apliniste);
    }

    public  void setSensorX(float sensorX){
        this.sensorX = sensorX;
    }

    public  float getSensorX(){
        return sensorX;
    }

    public  float getSensorY(){
        return sensorY;
    }

    public  void setSensorY(float sensorY){
        this.sensorY = sensorY;
    }


    public void display(Canvas canvas) {
        //vertical = vertical + verticalModifier;
       // System.out.println("je passe dans display draw Alpiniste ");
        //System.out.println("sensorX "+sensorX);
        canvas.drawBitmap(alpiniste, this.getSensorX(), sensorY, null);
    }

    @Override
    public int getx() {
        return (int)sensorX;
    }

    @Override
    public int gety() {
        return (int)sensorY;
    }

    @Override
<<<<<<< 6d52567d4fcdcf8a5ef291d0139f4501e28369a7
    public int getVertical() {
=======
    public int getHorizontal() {
>>>>>>> Ajout du score
        return 0;
    }

    @Override
<<<<<<< 6d52567d4fcdcf8a5ef291d0139f4501e28369a7
    public int getHorizontal() {
=======
    public int getVertical() {
>>>>>>> Ajout du score
        return 0;
    }
}
