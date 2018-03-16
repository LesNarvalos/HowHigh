package m2dl.com.howhigh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.TextView;

import draw.RockDraw;
import m2dl.com.howhigh.MoveAlpiniste;
import m2dl.com.howhigh.R;
import manager.GameManager;

public class GameActivity extends AppCompatActivity {

    private Handler handler;

    private RockDraw rockDraw;

    private GameManager gameManager;
    private MoveAlpiniste moveAlpiniste;
    TextView compteur;

    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        /*Intent intent = new Intent(this,MoveAlpiniste.class);
        startActivity(intent);*/

        compteur = findViewById(R.id.score);
        runnable = new Runnable() {
            @Override
            public void run() {
                compteur.setText(String.valueOf(gameManager.getTotal()));
                gameManager.update();
                handler.postDelayed(this, 100);
            }
        };

        Display display = getWindowManager().getDefaultDisplay();
        float xmax = (float)display.getWidth() - 120;
        float ymax = (float)display.getHeight()-430;

<<<<<<< 6d52567d4fcdcf8a5ef291d0139f4501e28369a7
        gameManager = new GameManager(getApplicationContext(), getWindowManager().getDefaultDisplay());
=======
        gameManager = new GameManager(getApplicationContext(),getWindowManager().getDefaultDisplay());
>>>>>>> Ajout du score
        moveAlpiniste = new MoveAlpiniste(this,gameManager,ymax,xmax);

        handler = new Handler();
        handler.postDelayed(runnable, 500);

        addContentView(gameManager, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
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
                 float sensorY = moveAlpiniste.getDrawalpiniste().getSensorY();
                sensorY -= 100;
                moveAlpiniste.getDrawalpiniste().setSensorY(sensorY);

        }
        return true;
        //return super.onTouchEvent(event);
    }
}
