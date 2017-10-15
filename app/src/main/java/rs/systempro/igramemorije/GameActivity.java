package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    int h;
    int w;
    ArrayList<Drawable> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        h = myIntent.getIntExtra("height", 4);
        w = myIntent.getIntExtra("width", 3);

        images= new ArrayList<>();
        for(int i=0;i<(h*w)/2;i++){
            int  resId = getResources().getIdentifier("slicica"+(i+1),"drawable",getPackageName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                images.add(getDrawable(resId));
                images.add(getDrawable(resId));
            }else{
                images.add(getResources().getDrawable(resId));
                images.add(getResources().getDrawable(resId));
            }
        }
        Collections.shuffle(images);

        final RelativeLayout rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        final GridLayout glMreza = (GridLayout) findViewById(R.id.glMreza);
        glMreza.setColumnCount(w);

        rlRoot.post(new Runnable() {
            @Override
            public void run() {
                int rootH = rlRoot.getHeight();
                int rootW = rlRoot.getWidth();

                Toast.makeText(getApplicationContext(), rootH + " - " + rootW, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < h * w; i++) {
                    ImageButton b = new ImageButton(getApplicationContext());

                    b.setBackgroundColor(Color.RED);
                    b.setImageDrawable(images.get(i));
                    glMreza.addView(b);
                    GridLayout.LayoutParams param = new GridLayout.LayoutParams();

                    if(rootH/h<rootW/w) {
                        param.height = rootH / h - 20;
                        param.width = rootH / h - 20;
                    }else {
                        param.width = rootW / w - 20;
                        param.height = rootW / w - 20;
                    }
                    param.setMargins(10,10,10,10);
                    b.setLayoutParams(param);
                }
            }
        });

    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        GridLayout glMreza = (GridLayout) findViewById(R.id.glMreza);
//
//        if(glMreza.getChildCount()!=0){
//            return;
//        }
//
//        glMreza.setColumnCount(w);
//
//
//        int rootH = findViewById(R.id.rlRoot).getHeight();
//        int rootW = findViewById(R.id.rlRoot).getWidth();
//
//        Toast.makeText(this, rootH + " - "+rootW, Toast.LENGTH_SHORT).show();
//
//        for(int i=0;i<h*w;i++){
//            Button b = new Button(this);
//
//            b.setText("Dugme "+ i);
//            glMreza.addView(b);
//            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
//            param.height = rootH/h;
//            param.width = rootW/w;
//            b.setLayoutParams(param);
//        }
//    }
}
