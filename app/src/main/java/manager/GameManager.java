package manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.Html;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import draw.HoleDraw;
import draw.RockDraw;
import draw.YetiDraw;

import draw.DrawApliniste;
import m2dl.com.howhigh.activity.GameActivity;
import m2dl.com.howhigh.activity.StartActivity;

/**
 * Created by etudiant on 16/03/18.
 */

public class GameManager extends SurfaceView {

    private List<GameItem> listGameItem = new ArrayList<>();

    private SurfaceHolder holder;

    private DisplayMetrics metrics;

    private int compteur = 0;

    private int time = 1;

    private int total = 0;

    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private String record;

    // Constructors

    public GameManager(Context context) {
        super(context);
    }

    public GameManager(Context context, Display display) {
        super(context);
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                record = dataSnapshot.child("Record").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void update() {
        total = total + 30;
        addBlock();
        holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.WHITE);

        // collision
        for (GameItem item : listGameItem) {
            if (item.getClass() == DrawApliniste.class){
                for (GameItem item2 : listGameItem) {

                    if (item2.getClass() != DrawApliniste.class){
                        System.out.println("na me class"+item2.getClass().getName());
                        System.out.println("x item2 "+item2.getx());
                        if(item.getx()>item2.getx()-150 && item.getx()-60<item2.getx()+150 && item.gety()>item2.gety()-150 && item.gety()-118<item2.gety()+150) {

                            if (Integer.decode(record).intValue() < total) {
                                database.getReference().child("Record").setValue(String.valueOf(total));
                            }

                        onFinishInflate();
                        }
                    }
                }
            }
        }
            //item.display(canvas);


        for (GameItem item : listGameItem) {
            item.display(canvas);
        }

        holder.unlockCanvasAndPost(canvas);

        removeItem();
    }

    public void addBlock() {
        if (compteur % time == 0) {
            Random randomGenerator = new Random();
            int random = randomGenerator.nextInt();
            if (random < 0) {
                random = random * -1;
            }
            if ((random % 2) == 0) {
                listGameItem.add(new YetiDraw(getContext(), random % metrics.widthPixels));
            }
            else if ((random % 3) == 0) {
                listGameItem.add(new HoleDraw(getContext(), random % metrics.widthPixels));
            }
            else {
                listGameItem.add(new RockDraw(getContext(), random % metrics.widthPixels));
            }
            compteur = 0;
            time = random % 6;
            if (time < 3) {
                time = 3;
            }
            time = time * 10;
        }
        compteur++;
    }

    public void removeItem() {
        if (!listGameItem.isEmpty()) {
            if (listGameItem.get(0).getVertical() > metrics.heightPixels) {
                listGameItem.remove(0);
            }
        }
    }

    // Getters and Setters

    public List<GameItem> getListGameItem() {
        return listGameItem;
    }

    public void setListGameItem(List<GameItem> listGameItem) {
        listGameItem = listGameItem;
    }

    public void getRecord() {
        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                record = dataSnapshot.child("Record").getValue(Long.class).toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public int getTotal() {
        return total;
    }
}
