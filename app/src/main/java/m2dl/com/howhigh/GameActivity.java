package m2dl.com.howhigh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    DrawApliniste apliniste;
    TextView compteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*apliniste = new DrawApliniste(this);
        setContentView(apliniste);*/
        Intent intent = new Intent(this,MoveAlpiniste.class);
        startActivity(intent);
    }


}
