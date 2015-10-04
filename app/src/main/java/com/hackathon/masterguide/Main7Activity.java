package com.hackathon.masterguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

public class Main7Activity extends AppCompatActivity {

    private CardEditor mCardEditor;
    private Button mPayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Simplify.init("sbpb_ZTdmZTY0MjgtNzg5Mi00NzI2LTg0ZmQtMGRjMDAzZWE3YTQ0");


        mPayButton = (Button) findViewById(R.id.btnPay);
        mPayButton.setEnabled(false);
        mPayButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                requestCardToken();
            }
        });

        mCardEditor = (CardEditor) findViewById(R.id.card_editor);
        mCardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {

            @Override
            public void onStateChange(CardEditor cardEditor) {
                mPayButton.setEnabled(cardEditor.isValid());
            }
        });
    }

    private void requestCardToken() {
        mPayButton.setEnabled(false);

        Simplify.createCardToken(mCardEditor.getCard(), new CardToken.Callback() {

            @Override
            public void onSuccess(CardToken cardToken) {
                RequestQueue queue = Volley.newRequestQueue(Main7Activity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, "",
                        new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

                queue.add(stringRequest);

                mPayButton.setEnabled(true);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                mPayButton.setEnabled(true);
            }
        });
    }

}
