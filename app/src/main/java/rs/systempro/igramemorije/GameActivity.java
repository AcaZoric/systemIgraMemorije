package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int h;
    int w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        h= myIntent.getIntExtra("height", 4);
        w = myIntent.getIntExtra("width",3);


    }

    @Override
    protected void onResume() {
        super.onResume();

        GridLayout glMreza = (GridLayout) findViewById(R.id.glMreza);

        if(glMreza.getChildCount()!=0){
            return;
        }

        glMreza.setColumnCount(w);

        for(int i=0;i<h*w;i++){
            Button b = new Button(this);

            b.setText("Dugme "+ i);
            glMreza.addView(b);
            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.setGravity(Gravity.FILL);
            b.setLayoutParams(param);
        }
    }
}
