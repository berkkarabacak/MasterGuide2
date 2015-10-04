package com.hackathon.masterguide;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class Main7Activity extends AppCompatActivity {

    private CardEditor mCardEditor;
    private Button mPayButton;

    private final AsyncTask<String, String, String> task = new AsyncTask<String, String, String>() {
        @Override
        protected String doInBackground(String... params) {
            String url = "http://localhost:8080/service?token=" + params[0] + "&amount=" + 10;
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(url));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }
    };

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
                Toast.makeText(Main7Activity.this, cardToken.getId(), Toast.LENGTH_SHORT).show();

                task.execute(cardToken.getId());
                /*
                RequestQueue queue = Volley.newRequestQueue(Main7Activity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://localhost:8080/service?token=" + cardToken.getId() + "&amount=" + 10,
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

                queue.start();
                */

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
