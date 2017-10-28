package rs.systempro.igramemorije;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int time = getIntent().getIntExtra("time", 60);

        ((TextView)findViewById(R.id.tvTime)).setText("Cestitamo vase vreme je: "+time + "s");
    }
}
