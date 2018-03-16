package manager;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etudiant on 16/03/18.
 */

public class GameManager {

    private static List<GameItem> listGameItem = new ArrayList<>();

    public static void update() {
        for (GameItem item : listGameItem) {
            item.clean();
        }

        for (GameItem item : listGameItem) {
            item.display();
        }
    }

    public static List<GameItem> getListGameItem() {
        return listGameItem;
    }

    public static void setListGameItem(List<GameItem> listGameItem) {
        GameManager.listGameItem = listGameItem;
    }
}
