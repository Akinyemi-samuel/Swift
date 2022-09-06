package com.example.myapplication2;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class First_Fragment extends Fragment implements RecyclerAdapter.ItemClickListener {

    TextView firstname,welcome;;
    String fid;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ImageView notification;

   private ArrayList<Product> userList = new ArrayList<>();


    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_ID = "id";
    final static String load_items =  "https://iufmp.spotters.tech/android/Orders_request.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_first_, container, false);

        firstname = view.findViewById(R.id.firstname);
        welcome = view.findViewById(R.id.welcome);
        notification = view.findViewById(R.id.notification);


        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String Phonen = sharedPreferences.getString(KEY_PHONE,null);
        String fnamen = sharedPreferences.getString(KEY_FNAME,null);
        fid = sharedPreferences.getString(KEY_ID,null);
        if(Phonen != null && fnamen != null && fid != null){
            firstname.setText(fnamen);
            welcome.setText(getgreet());
        }




         recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setHasFixedSize(true);



        load_items();


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Notification.class);
                startActivity(intent);
            }
        });


        return view;


    }




    private void load_items() {

        String id = fid;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, load_items, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String order_id = object.getString("order_id").trim();
                            String rec_name = object.getString("rec_name").trim();
                            String rec_phone = object.getString("rec_phone").trim();
                            String rec_address = object.getString("rec_address").trim();
                            String order_details = object.getString("order_details").trim();

                            Product product = new Product(order_id,rec_name,rec_phone,rec_address,order_details);

                            userList.add(product);

                        }


                    adapter = new RecyclerAdapter(First_Fragment.this,userList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "error"+ e+toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), "error"+ error+toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id",id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    public static String getgreet(){
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay < 12){
            return "Good Morning,";
        }else if(timeOfDay < 16){
            return "Good Afternoon,";
        }else if(timeOfDay < 21){
            return "Good Evening,";
        }else{
            return "Good Night,";
        }
    }

    @Override
    public void onItemClick(Product product) {





       Intent intent = new Intent(getContext(),order_details_first_fragment.class);
       intent.putExtra("id",product.getOrder_id());
       intent.putExtra("name",product.getReceipient_name());
        intent.putExtra("phone",product.getReceipient_Phone());
        intent.putExtra("add",product.getReciepient_Address());
        intent.putExtra("details",product.getOrder_details());
        startActivity(intent);





    }

}