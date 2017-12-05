package com.example.kchundur.sr;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * Created by kchundur on 12/3/2017.
 */

public class Profile extends Fragment {
    private static Object context;
    TextView tx_name,tx_email,tx_fname,tx_lname,tx_addr;
    EditText et_password;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Profile");
        String memname = getArguments().getString("username");
        getuserinfo(memname);

        tx_name=(TextView) view.findViewById(R.id.name);
        tx_email=(TextView) view.findViewById(R.id.email);
        tx_fname=(TextView) view.findViewById(R.id.fname);
        tx_lname=(TextView) view.findViewById(R.id.lname);
        tx_addr=(TextView)view.findViewById(R.id.address);
        et_password=(EditText)view.findViewById(R.id.password);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_profile,container,false);
    }

    public void getuserinfo(String memname)
    {

        String url = "http://ec2-13-57-3-28.us-west-1.compute.amazonaws.com:9999/api/members/search?user=";
        url+=url+memname;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("debug",response);
                        String name,email,fname,lname,addr;


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("debug", "didnot work");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}
