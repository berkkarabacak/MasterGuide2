package com.hackathon.masterguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.hackathon.masterguide.core.GuideService;
import com.hackathon.masterguide.core.GuideServiceFactory;
import com.hackathon.masterguide.domain.Guide;

public class Main5Activity extends AppCompatActivity {

    private final GuideService service = GuideServiceFactory.instance().getService();
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "umut", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main5Activity.this, Main7Activity.class));
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int id=extras.getInt("id");
        }

       /* Guide guide = service.getGuide(new Integer(id));
        ImageView IV = (ImageView) findViewById(R.id.imageView);
        int j = getResources().getIdentifier("profiletop" + id, "drawable", getPackageName());
        IV.setImageResource( j );
        ImageView IV2 = (ImageView) findViewById(R.id.imageView2);
        int j2 = getResources().getIdentifier("profilemid" + id, "drawable", getPackageName());
        IV2.setImageResource( j2 ); */
    }



}
