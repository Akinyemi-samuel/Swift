package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AboutUs_Activity extends AppCompatActivity {
    TextView terms_of_services,privacy_policy,jch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        terms_of_services = findViewById(R.id.terms_of_service);
        privacy_policy = findViewById(R.id.privacy_policy);
        jch = findViewById(R.id.jch);

        terms_of_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUs_Activity.this,Terms_of_services.class);
                startActivity(intent);
            }
        });
        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutUs_Activity.this,privacy_policy.class);
                startActivity(intent);
            }
        });

        jch.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void backbtn(View view) {
        Fragment settingsfrag =new Settings_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsfrag);
        finish();
    }
}