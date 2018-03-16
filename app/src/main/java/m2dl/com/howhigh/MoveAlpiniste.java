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

/**
 * Created by hichem on 16/03/2018.
 */

public class MoveAlpiniste extends Activity implements SensorEventListener{
    Bitmap alpiniste;
    SensorManager sm;
    DrawApliniste view;
    float x,y, sensorX, sensorY,xmax,ymax;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int temp =(int) sensorEvent.values[0]*150;
        sensorX = sensorEvent.values[0]*150;
        //sensorX = (int) Math.pow(sensorEvent.values[0], 2);

        if (sensorX > xmax) {
            sensorX = xmax;
        } else if (sensorX < 0) {
            sensorX = 0;
        }
        //view.run();
    }

    @Override
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
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class DrawApliniste extends SurfaceView implements Runnable{
        SurfaceHolder holder;
        Thread thread = null;
        boolean isRunning = true;
        public DrawApliniste(Context context) {
            super(context);

            holder = getHolder();
        }

        public void pause (){
            isRunning = false;
            while(true){
                try {
                    thread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            thread = null;
        }

        public void resume(){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            while (isRunning){
                if(!holder.getSurface().isValid())
                    continue;
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(alpiniste,sensorX,sensorY,null);
                sensorY = ymax;
                holder.unlockCanvasAndPost(canvas);
            }

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_UI);

        }

        alpiniste = BitmapFactory.decodeResource(getResources(),R.drawable.apliniste);
        //x = y = sensorX = sensorY =0;
        Display display = getWindowManager().getDefaultDisplay();
        xmax = (float)display.getWidth() - 120;
        ymax = (float)display.getHeight()-290;
        sensorY = ymax;
        view = new DrawApliniste(this);
        view.resume();
        setContentView(view);


    }
}
