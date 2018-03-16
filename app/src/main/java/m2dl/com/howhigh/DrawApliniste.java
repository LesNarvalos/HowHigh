package m2dl.com.howhigh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by hichem on 16/03/2018.
 */

public class DrawApliniste extends SurfaceView implements Runnable {
    SurfaceHolder holder;
    Thread thread = null;
    boolean isRunning = true;
    Bitmap alpiniste;
    float sensorX,sensorY,ymax;
    public DrawApliniste(Context context,Bitmap alpiniste,float sensorX,float sensorY,float ymax) {
        super(context);

        this.alpiniste = alpiniste;
        this.sensorX = sensorX;
        this.sensorY = sensorY;
        this.ymax = ymax;
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
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensorY = ymax;
            holder.unlockCanvasAndPost(canvas);
        }

    }


}
