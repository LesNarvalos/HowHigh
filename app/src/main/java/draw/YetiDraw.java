package draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.logging.Logger;

import m2dl.com.howhigh.R;
import manager.GameItem;

/**
 * Created by etudiant on 16/03/18.
 */

public class YetiDraw implements GameItem {

    private static final Logger LOGGER = Logger.getLogger(YetiDraw.class.getName());

    private Bitmap bitmap;

    private int horizontal;

    private int vertical = -500;

    private int verticalModifier = 30;

    private SurfaceHolder holder;

    public YetiDraw(Context context, int horizontal) {
        this.horizontal = horizontal;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.yeti);
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public void display(Canvas canvas) {
        vertical = vertical + verticalModifier;
        canvas.drawBitmap(bitmap, horizontal, vertical, null);
    }

    @Override
    public int getx() {
<<<<<<< 6d52567d4fcdcf8a5ef291d0139f4501e28369a7
        return horizontal;
=======
        return getHorizontal();
>>>>>>> Ajout du score
    }

    @Override
    public int gety() {
<<<<<<< 6d52567d4fcdcf8a5ef291d0139f4501e28369a7
        return vertical;
=======
        return getVertical();
>>>>>>> Ajout du score
    }
}
