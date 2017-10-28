package rs.systempro.igramemorije;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    int solved;
    ArrayList<Drawable> images;
    ImageButton firstOpened;
    ImageButton secondOpened;
    int numOfOpened;
    int time;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent myIntent = getIntent();
        final int h = myIntent.getIntExtra("height", 4);
        final int w = myIntent.getIntExtra("width", 3);

        solved=0;
        numOfOpened=0;

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

        time=0;
        timer = new Timer();
        final TextView tvTime = (TextView)findViewById(R.id.tvTime);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTime.setText(time+"s");
                    }
                });
            }
        },1000,1000);
        rlRoot.post(new Runnable() {
            @Override
            public void run() {
                int rootH = rlRoot.getHeight();
                int rootW = rlRoot.getWidth();

                Toast.makeText(getApplicationContext(), rootH + " - " + rootW, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < h * w; i++) {
                    final ImageButton b = new ImageButton(getApplicationContext());

                    final Drawable image = images.get(i);
                    b.setBackgroundColor(Color.RED);
//                    b.setImageDrawable(images.get(i));
                    b.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    b.setPadding(0,0,0,0);

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

                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            b.setImageDrawable(image);


                            if(numOfOpened==2){
                                numOfOpened=1;
                                firstOpened.setImageDrawable(null);
                                secondOpened.setImageDrawable(null);
                                firstOpened = b;
                                b.setImageDrawable(image);
                            }else if(numOfOpened==1){
                                b.setImageDrawable(image);
                                numOfOpened++;
                                secondOpened=b;
                                if(firstOpened.getDrawable().getConstantState().equals(secondOpened.getDrawable().getConstantState())) {
                                    numOfOpened=0;
                                    firstOpened.setClickable(false);
                                    secondOpened.setClickable(false);
                                    if(++solved == h*w/2){
                                        timer.cancel();
                                        finish();
                                        Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                                        intent.putExtra("time", time);
                                        startActivity(intent);
                                    }
                                }
                            }else{
                                b.setImageDrawable(image);
                                numOfOpened=1;
                                firstOpened = b;
                            }
                        }
                    });
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
