package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Order_process extends AppCompatActivity {
String name,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_process);

        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        Fragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putString("phone",phone);

        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.map_view,fragment).commit();


    }

    public void sam(View view) {
        Intent intent = new Intent(this,order_details_first_fragment.class);
        startActivity(intent);
    }
}