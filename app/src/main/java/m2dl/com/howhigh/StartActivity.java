package m2dl.com.howhigh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private Button playButton;

    private Button quitButton;

    private TextView record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        playButton = (Button) findViewById(R.id.play);
        quitButton = (Button) findViewById(R.id.quit);
        record = (TextView) findViewById(R.id.record);

        record.setText("Pas de record");
    }

    public void launchGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void quit(View view) {
        this.finish();
    }
}
