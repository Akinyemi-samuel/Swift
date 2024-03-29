package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class security extends AppCompatActivity {

    EditText old_password,new_password,re_type_password;
    AppCompatButton pass_btn;
    String fid;
    final static String url_updatepassword = "https://iufmp.spotters.tech/android/edit_password_data_sam.php";

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        old_password = findViewById(R.id.old_password);
        new_password =  findViewById(R.id.new_password);
        re_type_password = findViewById(R.id.Retype_new_password);
        pass_btn =  findViewById(R.id.updatepassbtn);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String Phonen = sharedPreferences.getString(KEY_PHONE,null);
        String fnamen = sharedPreferences.getString(KEY_FNAME,null);
        String lnamen = sharedPreferences.getString(KEY_LNAME,null);
        String lpassword = sharedPreferences.getString(KEY_PASSWORD,null);
        fid = sharedPreferences.getString(KEY_ID,null);

        pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass = old_password.getText().toString().trim();
                String newpass = new_password.getText().toString().trim();
                String re_type_pass = re_type_password.getText().toString().trim();
                
                if(!(oldpass.isEmpty() || newpass.isEmpty() || re_type_pass.isEmpty())){
                    if(lpassword.equals(oldpass)){
                        if(newpass.equals(re_type_pass)){
                            updatepassword();
                        }else{
                            re_type_password.setError("passwords do not match");
                        }
                    }else{
                        old_password.setError("Old password is in-correct");
                    }
                }else{
                    Toast.makeText(security.this, "passwords cannot be empty", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void updatepassword() {
        Edit_profile edit_profile = new Edit_profile();
        String newpassword = this.new_password.getText().toString().trim();
        String id = fid;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_updatepassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")){
                        Toast.makeText(security.this, "Passwords updated successfully", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(security.this, "error" + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(security.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("password",newpassword);
                params.put("id",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void backbtn(View view) {
        Fragment settingsfrag =new Settings_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,settingsfrag);
        finish();
    }
}