package ru.xxi_empire.rkhunter.quizdogorcatperson;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView    result;
    private ImageView   imageView;

    public ResultActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result      = (TextView)    findViewById(R.id.result_text);
        imageView   = (ImageView)   findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int catResult = extras.getInt("catCounter");
            int dogResult = extras.getInt("dogCounter");
            Log.d("CAT", Integer.toString(catResult));
            Log.d("DOG", Integer.toString(dogResult));

            if (catResult > dogResult) {
                result.setText("Cat person");
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_cat));
            } else if (dogResult > catResult){
                result.setText("Dog person");
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_dog));
            } else {
                result.setText("Neither");
            }
        }
    }
}
