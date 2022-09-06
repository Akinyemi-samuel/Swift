package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

public class order_details_first_fragment extends AppCompatActivity {

    EditText Order_id,receipient_name,receipient_phone,Order_details,receipient_address;
    String name;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_first_fragment);
        Order_id = findViewById(R.id.order_id);
        receipient_name = findViewById(R.id.receipent_name);
        receipient_phone = findViewById(R.id.receipent_phone);
        receipient_address = findViewById(R.id.receipent_address);
        Order_details = findViewById(R.id.order_details);



        String id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("add");
        String details = getIntent().getStringExtra("details");
        Order_id .setText(id);
        receipient_name.setText(name);
        receipient_phone.setText(phone);
        receipient_address.setText(address);
        Order_details.setText(details);


    }

    public void proceedorderdetails(View view) {
        Intent intent = new Intent(this,Order_process.class);
        intent.putExtra("name", name);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }

    public void backbtn(View view) {
        Fragment first_fragment =new First_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,first_fragment);
        finish();
    }
}