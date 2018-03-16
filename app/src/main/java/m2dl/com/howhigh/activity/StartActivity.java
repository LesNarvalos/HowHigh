package m2dl.com.howhigh.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Logger;

import draw.RockDraw;
import m2dl.com.howhigh.R;
import manager.GameManager;

public class StartActivity extends AppCompatActivity {

    private Button playButton;

    private Button quitButton;

    private TextView record;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final Logger LOGGER = Logger.getLogger(StartActivity.class.getName());

    private Handler handler;

    private Display display;

    private RockDraw rockDraw;

    private GameManager gameManager = new GameManager();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GameManager.update();
            handler.postDelayed(this, 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        display = getWindowManager().getDefaultDisplay();

        handler = new Handler();
        handler.postDelayed(runnable, 500);

        rockDraw = new RockDraw(this, 50);
        GameManager.getListGameItem().add(rockDraw);
        addContentView(rockDraw, new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        playButton = (Button) findViewById(R.id.play);
        quitButton = (Button) findViewById(R.id.quit);
        record = (TextView) findViewById(R.id.record);

        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String recordPlayer = dataSnapshot.child("Record").getValue(Long.class).toString();
                record.setText(Html.fromHtml("<b>Record : " + recordPlayer + "</b>"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void launchGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void quit(View view) {
        this.finish();
    }

    public void onPause() {
        super.onPause();

        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
