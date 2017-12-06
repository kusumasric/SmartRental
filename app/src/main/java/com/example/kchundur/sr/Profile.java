package com.example.kchundur.sr;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kchundur on 12/3/2017.
 */

public class Profile extends Fragment {
    private static Object context;
    TextView tx_name,tx_email,tx_fname,tx_lname,tx_addr;
    EditText et_password;

    String name,password,email,addr,fname,lname;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Profile");
        String memname = getArguments().getString("username");
        tx_name=(TextView) view.findViewById(R.id.name);
        tx_email=(TextView) view.findViewById(R.id.email);
        tx_fname=(TextView) view.findViewById(R.id.fname);
        tx_lname=(TextView) view.findViewById(R.id.lname);
        tx_addr=(TextView)view.findViewById(R.id.address);
        et_password=(EditText)view.findViewById(R.id.edit_pass);

        getuserinfo(memname);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_profile,container,false);
        Button button = (Button) view.findViewById(R.id.Update);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something

                boolean res=false;
               // name=tx_name.getText().toString();

                try {
                    password = et_password.getText().toString();
                }catch (Exception e)
                {
                    Log.d("Error",e.toString());
                }
              //  email=tx_email.getText().toString();
              //  addr=tx_addr.getText().toString();
              //  fname=tx_fname.getText().toString();
               // lname=tx_lname.getText().toString();


                Map<String, String> jsonParams = new HashMap<String, String>();
                jsonParams.put("UserName",name );
                jsonParams.put("PWD",password);
                jsonParams.put("FirstName",fname);
                jsonParams.put("LastName",lname);
                jsonParams.put("Address",addr);
                jsonParams.put("Email",email);



                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                JsonObjectRequest myRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://ec2-13-57-3-28.us-west-1.compute.amazonaws.com:9999/api/members/update",
                        new JSONObject(jsonParams),

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.d("message","success");

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("resonse",error.toString());

                            }
                        }) {

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("User-agent", "My useragent");
                        return headers;
                    }
                };
                queue.add(myRequest);
            }
        });
        return view;
    }




    public void getuserinfo(String memname)
    {

        String url = "http://ec2-13-57-3-28.us-west-1.compute.amazonaws.com:9999/api/members/search?user=";
        url+=url+memname;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Request a string response from the provided URL.
     /*   StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("debug", "came here");
                        String name,email,fname,lname,addr;
                        String result=response;
                        try {
                            JSONObject mainObject = new JSONObject(result);
                            name=mainObject.getString("UserName");
                            Log.d("name",name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", "didnot work");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);*/


        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("debug", "came here");
                        String name="",email="",fname="",lname="",addr="";

                        try {

                            name=response.getString("UserName");
                            tx_name.setText(name);
                            email=response.getString("EmailID");
                            tx_email.setText(email);
                            fname=response.getString("FirstName");
                            tx_fname.setText(fname);
                            lname=response.getString("LastName");
                            tx_lname.setText(lname);
                            addr=response.getString("Address");
                            tx_addr.setText(addr);

                            Log.d("name",name);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.d("resonse",error.toString());

                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        queue.add(jsonObjectRequest);


    }
}
