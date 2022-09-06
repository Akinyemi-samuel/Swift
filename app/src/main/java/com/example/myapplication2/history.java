package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class history extends AppCompatActivity {
    RecyclerView recyclerView;
    history_RecyclerAdapter adapter;
    ImageView backbtn;
    private ArrayList<history_Product> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        backbtn = findViewById(R.id.backbtn);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        adapter = new history_RecyclerAdapter(userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

//        build();






                backbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment settingsfrag =new Settings_Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsfrag);
                        finish();
                    }
                });
    }

    private void build() {
        userList.add(new history_Product("samuel","injection","9y2084"));
    }


}