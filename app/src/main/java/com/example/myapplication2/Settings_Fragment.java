package com.example.myapplication2;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Settings_Fragment extends Fragment {
CardView logout_menu;
   AppCompatButton editprofilebutton;
   RelativeLayout about,logout,historybtn,complaint,security;
   TextView Settings_name;
   ImageView profileimg;
   TextView security_close,cancel_logout,confirm_logout;
   RelativeLayout security_drpdwn;
   SwitchCompat fingerprintswitch;
   ProgressBar load;
   boolean toogle;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_EMAIL = "email";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view =  inflater.inflate(R.layout.fragment_settings_, container, false);

        editprofilebutton = view.findViewById(R.id.editprofilebtn);
        Settings_name = view.findViewById(R.id.settings_name);
          security = view.findViewById(R.id.securitybtn);
        historybtn = view.findViewById(R.id.historybtn);
        complaint = view.findViewById(R.id.complain);
        logout_menu = view.findViewById(R.id.logout_menu);
        about = view.findViewById(R.id.about);
        load = view.findViewById(R.id.load);

        profileimg = view.findViewById(R.id.profile_img);
        fingerprintswitch = view.findViewById(R.id.fingerprintswitch);

profileimg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(),Edit_profile.class);
        startActivity(intent);
    }
});

        cancel_logout = view.findViewById(R.id.cancel_logoutbtn);
        confirm_logout = view.findViewById(R.id.confirmlogoutbtn);

        logout = view.findViewById(R.id.logout);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String fname = sharedPreferences.getString(KEY_FNAME,null);
        String lnamen = sharedPreferences.getString(KEY_LNAME,null);
        if(fname != null && lnamen != null){
            Settings_name.setText(fname + " " + lnamen);
        }

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Complaint.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AboutUs_Activity.class);
                startActivity(intent);
            }
        });



        editprofilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment settings = new Settings_Fragment();
                Intent intent = new Intent(getContext(),Edit_profile.class);
                startActivity(intent);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),security.class);
                startActivity(intent);
            }
        });




        historybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),history.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_menu.setVisibility(View.VISIBLE);
            }
        });

        confirm_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(),UserLogin.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        cancel_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout_menu.setVisibility(View.GONE);
            }
        });






        return view;
    }




}