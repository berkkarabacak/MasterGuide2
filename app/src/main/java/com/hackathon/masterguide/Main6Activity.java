package com.hackathon.masterguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hackathon.masterguide.core.SessionManager;
import com.hackathon.masterguide.core.SessionManagerFactory;

public class Main6Activity extends AppCompatActivity {

    private final SessionManager manager = SessionManagerFactory.instance().getManager();
    private String nextPage;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nextPage = extras.getString(ActivityConstants.NEXT_PAGE);
            price = extras.getInt(ActivityConstants.PRICE);
        }

        Button btnSearch = (Button) findViewById(R.id.login);
        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                if (manager.login(email, password)) {
                    if ("Main7Activity".equalsIgnoreCase(nextPage)) {
                        Intent i = new Intent(Main6Activity.this, Main7Activity.class);
                        i.putExtra(ActivityConstants.PRICE, price);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
