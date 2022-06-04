package com.example.mohammed.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

class registration extends AppCompatActivity {
TextView tv ;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        tv= findViewById(R.id.link_login);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new  Intent(getApplicationContext(), login.class);

                startActivity(intent);
            }
        });


    }
}
