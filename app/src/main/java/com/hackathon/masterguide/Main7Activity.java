package com.hackathon.masterguide;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class Main7Activity extends AppCompatActivity {

    private CardEditor mCardEditor;
    private Button mPayButton;
    private int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            price = extras.getInt(ActivityConstants.PRICE);
        }

        TextView charged = (TextView) findViewById(R.id.chargePrice);
        charged.setText(price + "€ Charge");

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
                //Toast.makeText(Main7Activity.this, cardToken.getId(), Toast.LENGTH_SHORT).show();

                new HttpAsyncTask().execute(cardToken.getId());

                mPayButton.setEnabled(true);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                mPayButton.setEnabled(true);
            }
        });
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = "http://localhost:8080/service?token=" + urls[0] + "&amount=" + 10;
            return GET(url);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Received! " + String.valueOf(result), Toast.LENGTH_LONG).show();
            startActivity(new Intent(Main7Activity.this, Main8Activity.class));
        }
    }

}
