package m2dl.com.howhigh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Logger;

public class StartActivity extends AppCompatActivity {

    private Button playButton;

    private Button quitButton;

    private TextView record;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private static final Logger LOGGER = Logger.getLogger(StartActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        playButton = (Button) findViewById(R.id.play);
        quitButton = (Button) findViewById(R.id.quit);
        record = (TextView) findViewById(R.id.record);

        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LOGGER.info("On entre dedans");
                String recordPlayer = dataSnapshot.child("Record").getValue(Long.class).toString();
                LOGGER.info("Contenu du record : " + recordPlayer);
                record.setText("Record : " + recordPlayer);
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
}
