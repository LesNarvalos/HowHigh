package draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import java.util.logging.Logger;

import m2dl.com.howhigh.R;

/**
 * Created by etudiant on 16/03/18.
 */

public class RockDraw extends View {

    private static final Logger LOGGER = Logger.getLogger(RockDraw.class.getName());

    private Bitmap bitmap;

    private int horizontal;

    private int vertical;

    public RockDraw(Context context, int horizontal) {
        super(context);
        this.horizontal = horizontal;
        vertical= 100;
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

    public void move(Canvas canvas, int vert){
        canvas.drawBitmap(bitmap, horizontal, vertical, null);
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
}
