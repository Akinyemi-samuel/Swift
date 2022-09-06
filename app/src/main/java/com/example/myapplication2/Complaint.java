package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Complaint extends AppCompatActivity {

    EditText complain;
    Button complain_btn;
    final static String submit_url = "https://iufmp.spotters.tech/android/complaint.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        complain = findViewById(R.id.Complaint);
        complain_btn = findViewById(R.id.complaint_btn);




        complain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String edit_complain = complain.getText().toString().trim();

                if(edit_complain.isEmpty()){
                    Toast.makeText(Complaint.this, "Empty fields", Toast.LENGTH_SHORT).show();
                }
                else{
                  submit_complaint();
                }
            }

        });







    }

    private void submit_complaint() {
        final String complaint = this.complain.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")){
                        Toast.makeText(Complaint.this, "Your complaint has been sent successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Complaint.this, "Oops! we could,nt send your complaint try again", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Complaint.this, "Oops! we could,nt send your complaint try again", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> Params = new HashMap<>();
                Params.put("c_message",complaint);
                return Params;
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