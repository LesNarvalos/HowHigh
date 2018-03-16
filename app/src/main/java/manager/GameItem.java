package manager;

import android.graphics.Canvas;

/**
 * Created by etudiant on 16/03/18.
 */

public interface GameItem {

    public void display(Canvas canvas);

    public int getHorizontal();

    public int getVertical();
}
