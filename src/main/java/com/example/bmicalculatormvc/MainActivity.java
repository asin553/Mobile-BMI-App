package com.example.bmicalculatormvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ModelService WebIF;
    private ModelRESTfulAPI WebREST;
    private WebPageResult WebPAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebIF = ModelBMI.getService("bmi");
        WebREST = WebIF.createRESTAPI();
    }

    Callback<WebPageResult> button1Callback = new Callback<WebPageResult>() {
        @Override
        public void onResponse(Call<WebPageResult> call, Response<WebPageResult> response) {
            Log.d("inquiryCallback", "Code: " + response.code() + " Message: " + response.message());
            if (response.isSuccessful()) {
                WebPAGE = response.body();
                System.out.println("bmi: "+ WebPAGE.getBodyMassIndex());
                System.out.println("more: "+ WebPAGE.getWebLinks());
                System.out.println("risk: "+ WebPAGE.getRiskStatus());
                setUserInterface(WebPAGE);
            } else {
                Log.d("inquiryCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<WebPageResult> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<WebPageResult> button2Callback = new Callback<WebPageResult>() {
        @Override
        public void onResponse(Call<WebPageResult> call, Response<WebPageResult> response) {
            Log.d("inquiryCallback", "Code: " + response.code() + " Message: " + response.message());
            if (response.isSuccessful()) {
                WebPAGE = response.body();


                String url = WebPAGE.getWebLinks().get(0);
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(webpageIntent);

            } else {
                Log.d("inquiryCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<WebPageResult> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void callBMIAPI(View view) {
        double height = heightEntry();
        double weight = weightEntry();
        WebREST.getBodyMassIndex(height, weight).enqueue(button1Callback);
    }

    public void educateMe(View view) {
        double height = heightEntry();
        double weight = weightEntry();

        if(WebPAGE == null){
            Log.d("", "Error: Null Response");
            WebREST.getBodyMassIndex(height, weight).enqueue(button2Callback);
        } else {
            String url = WebPAGE.getWebLinks().get(0);
            Intent webpageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(webpageIntent);

        }
    }

    private double heightEntry(){
        EditText id = (EditText) findViewById(R.id.height_value);
        String entryHeight = id.getText().toString();
        double height = entryHeight != null && !"".equals(entryHeight) ? Double.parseDouble(entryHeight) : 1;
        return height;
    }

    private double weightEntry(){
        EditText id = (EditText) findViewById(R.id.weight_value);
        String entryWeight = id.getText().toString();
        double weight = entryWeight != null && !"".equals(entryWeight) ? Double.parseDouble(entryWeight) : 1;
        return weight;
    }

    private void setUserInterface(WebPageResult WebPAGE) {
        TextView bmi = (TextView) findViewById(R.id.bmi_value);
        TextView msg = (TextView) findViewById(R.id.msg_label);
        bmi.setText(WebPAGE.getBodyMassIndex()+ "");
        if(WebPAGE.getBodyMassIndex() < 18){
            msg.setTextColor(Color.parseColor("#FF0D26C8"));
            msg.setText("You are underweight.");
        } else if (WebPAGE.getBodyMassIndex() >= 18 && WebPAGE.getBodyMassIndex() < 25){
            msg.setTextColor(Color.parseColor("#FF1C8C08"));
            msg.setText("You are normal.");
        } else if (WebPAGE.getBodyMassIndex() >= 25 && WebPAGE.getBodyMassIndex() <= 30){
            msg.setTextColor(Color.parseColor("#FF7010C5"));
            msg.setText("You are pre-obese.");
        } else if (WebPAGE.getBodyMassIndex() > 30){
            msg.setTextColor(Color.parseColor("#FFEF0A24"));
            msg.setText("You are obese.");
        }

    }

}