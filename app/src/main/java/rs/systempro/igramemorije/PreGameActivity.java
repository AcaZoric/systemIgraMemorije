package rs.systempro.igramemorije;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PreGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
    }

    public void letTheGameBegin(View view) {
        EditText etHeight =(EditText)findViewById(R.id.etHeight);
        EditText etWidth = (EditText)findViewById(R.id.etWidth);
        if(etHeight.getText().length()!=0 && etWidth.getText().length()!=0) {
            int h = Integer.parseInt(etHeight.getText().toString());
            int w = Integer.parseInt(etWidth.getText().toString());

            Intent myIntent = new Intent(this, GameActivity.class);
            myIntent.putExtra("height", h);
            myIntent.putExtra("width", w);
            startActivity(myIntent);
        }else{
            Toast.makeText(this, "Unesite velicinu mreze!", Toast.LENGTH_SHORT).show();
        }

    }
}
