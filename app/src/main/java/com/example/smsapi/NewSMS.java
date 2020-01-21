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

public class NewSMS extends AppCompatActivity {
    //This activity is used to generate OTP which you have to send it to the recipent.
String name,phn,email;
TextView nametxt,emailtxt,phnotxt;
Button btnsendsms;
Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sms);
            toolbar=findViewById(R.id.tool);
            toolbar.setTitle(R.string.app_name);

        Intent intent=getIntent();
        name= intent.getStringExtra("name");
        phn=intent.getStringExtra("phno");
        email=intent.getStringExtra("email");




        nametxt=findViewById(R.id.name);
        emailtxt=findViewById(R.id.email);
        phnotxt=findViewById(R.id.phno);
        btnsendsms=findViewById(R.id.btnsendsms);

        nametxt.append(name);
        emailtxt.append(email);
        phnotxt.append(phn);

        btnsendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(NewSMS.this,SMSSend.class);
                i.putExtra("name",name);
                i.putExtra("phno",phn);
                startActivity(i);

            }
        });

       // Toast.makeText(this, "data = "+name+phn+email, Toast.LENGTH_SHORT).show();


    }
}
