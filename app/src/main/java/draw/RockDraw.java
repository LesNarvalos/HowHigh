package draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.logging.Logger;

import m2dl.com.howhigh.R;
import manager.GameItem;

/**
 * Created by etudiant on 16/03/18.
 */

public class RockDraw extends SurfaceView implements GameItem {

    private static final Logger LOGGER = Logger.getLogger(RockDraw.class.getName());

    private Bitmap bitmap;

    private int horizontal;

    private int vertical;

    private int verticalModifier = 30;

    private SurfaceHolder holder;

    public RockDraw(Context context, int horizontal) {
        super(context);
        holder = getHolder();
        this.horizontal = horizontal;
        vertical = 100;
        bitmap = (Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.rock);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, horizontal, vertical, null);
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public int getVertical() {
        return vertical;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public void display() {
        Canvas canvas = holder.lockCanvas();
        vertical = vertical + verticalModifier;
        canvas.drawBitmap(bitmap, horizontal, vertical, null);
        holder.unlockCanvasAndPost(canvas);
    }

    public void clean() {
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        holder.unlockCanvasAndPost(canvas);
    }
}
