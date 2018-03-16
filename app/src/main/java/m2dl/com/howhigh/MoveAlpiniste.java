package m2dl.com.howhigh;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import draw.DrawApliniste;
import m2dl.com.howhigh.activity.GameActivity;
import manager.GameManager;

/**
 * Created by hichem on 16/03/2018.
 */

public class MoveAlpiniste implements SensorEventListener{
    //Bitmap alpiniste;
    SensorManager sm;
    DrawApliniste drawalpiniste;
    float x,y, sensorX, sensorY,xmax,ymax;
    Context mContext;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = (sensorX*2)+xmax/2;
        /*int temp =(int) sensorEvent.values[0]*150;*/
        sensorX = (int)(sensorEvent.values[0]);
        sensorX = (-sensorX*80)+xmax/2;
        if (sensorX > xmax) {
            sensorX = xmax;
        } else if (sensorX < 0) {
            sensorX = 0;
        }
        drawalpiniste.setSensorX(sensorX);


        //view.run();
    }

/*    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                sensorY -= 500;


        }
        return true;
        //return super.onTouchEvent(event);
    }*/

    public DrawApliniste getDrawalpiniste(){
        return drawalpiniste;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public MoveAlpiniste(Context context, GameManager gameManager, float ymax, float xmax) {

        this.xmax = xmax;
        this.ymax = ymax;
        this.mContext = context;
        sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_UI);

        }

        System.out.println("je passe dans Move alpiniste ");
        //alpiniste = BitmapFactory.decodeResource(getResources(),R.drawable.apliniste);
        x = y = sensorX = sensorY =0;
        drawalpiniste =new DrawApliniste(mContext);
        /*Display display = getWindowManager().getDefaultDisplay();
        xmax = (float)display.getWidth() - 120;
        ymax = (float)display.getHeight()-290;*/
        sensorY = ymax;
        drawalpiniste.setSensorY(sensorY);
        gameManager.getListGameItem().add(drawalpiniste);
        /*view = new DrawApliniste(this);
        view.resume();
        setContentView(view);*/


    }
}
