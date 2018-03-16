package manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import draw.DrawApliniste;

/**
 * Created by etudiant on 16/03/18.
 */

public class GameManager extends SurfaceView {

    private List<GameItem> listGameItem = new ArrayList<>();

    private SurfaceHolder holder;

    public GameManager(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public GameManager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public GameManager(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void update() {
        holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);

        // collision
        /*for (GameItem item : listGameItem) {
            if (item.getClass() == DrawApliniste.class){
                for (GameItem item2 : listGameItem) {
                    if (item2.getClass() != DrawApliniste.class){
                        item.getx()+60
                    }
                }
                }
            }
            //item.display(canvas);
        }*/

        for (GameItem item : listGameItem) {
            item.display(canvas);
        }
        holder.unlockCanvasAndPost(canvas);
        //System.out.println("size liste manager"+listGameItem.size());
    }

    public List<GameItem> getListGameItem() {
        return listGameItem;
    }

    public void setListGameItem(List<GameItem> listGameItem) {
        listGameItem = listGameItem;
    }
}
