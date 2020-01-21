package com.example.smsapi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SMSSend extends AppCompatActivity {
String name,phn,otpsms,currentDateandTime;
TextView namtxt,phnotxt,otptxt;
Button sendotpbtn;
    int randomNumber;
Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssend);
        Random random= new Random();
        randomNumber=random.nextInt(999999);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
         currentDateandTime = sdf.format(new Date());

        Intent intent=getIntent();
        name= intent.getStringExtra("name");
        phn=intent.getStringExtra("phno");

      Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("phno", phn);
        bundle.putInt("otp",randomNumber);
        bundle.putString("date", currentDateandTime);


        SMSFragment fragobj = new SMSFragment();
        fragobj.setArguments(bundle);

        namtxt=findViewById(R.id.name);
        phnotxt=findViewById(R.id.phno);
        otptxt=findViewById(R.id.otptxt);
        sendotpbtn=findViewById(R.id.otpbtn);
        otpsms="Hi, Your OTP for login is: "+randomNumber;

        toolbar=findViewById(R.id.tool);
        toolbar.setTitle(R.string.app_name);

        otptxt.setText(otpsms);

        namtxt.append(name);
        phnotxt.append(phn);


        sendotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });



    }
    public String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + "bWxEN8U9opU-1O7xLZcVsALyQKA6BRk9jAeuoptPt8";
            String message = "&message=" + otpsms;
            String sender = "&sender=" + "TXTLCL";
            String numbers = "&numbers=" + phnotxt.getText().toString();

            // Send data
            HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
            String data = apiKey + numbers + message + sender;
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            final StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
            rd.close();

            Toast.makeText(this, "Otp Send Succesfully", Toast.LENGTH_SHORT).show();

            return stringBuffer.toString();
        } catch (Exception e) {
            System.out.println("Error SMS "+e);
            Toast.makeText(this, "error = "+e, Toast.LENGTH_SHORT).show();
            return "Error "+e;
        }
    }

}
