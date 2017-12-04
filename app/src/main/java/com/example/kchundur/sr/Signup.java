package com.example.kchundur.sr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by kchundur on 12/3/2017.
 */

public class Signup extends Activity  {
    private EditText et_Name,et_Pass, et_Address,et_fname,et_lname,et_email;
   String wservice;
   int memberid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_signup);
        wservice= getIntent().getStringExtra("Service").toString();

        et_Name = (EditText)findViewById(R.id.name);
        et_Pass = (EditText)findViewById(R.id.password);
        et_Address=(EditText)findViewById(R.id.Address);
        et_fname=(EditText)findViewById(R.id.fname);
        et_lname=(EditText)findViewById(R.id.Lname);
        et_email=(EditText)findViewById(R.id.email);

        Spinner spinner = (Spinner) findViewById(R.id.Spinner_service);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Service_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    public void onClickSignUp(View view)
    {

        Signupuser();

    }

    public void onClickcancel(View view)
    {
        moveTaskToBack(true);
        finish();


    }


    private void LoginErrorMessage()
    {
        android.app.AlertDialog.Builder dlgAlert = new android.app.AlertDialog.Builder(this);
        dlgAlert.setMessage("User already exist");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    public void Signupuser()
    {
        String name,password,email,addr,fname,lname;
        boolean res=false;

        name=et_Name.getText().toString();
        password=et_Pass.getText().toString();
        email=et_email.getText().toString();
        addr=et_Address.getText().toString();
        fname=et_fname.getText().toString();
        lname=et_lname.getText().toString();


        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("UserName",name );
        jsonParams.put("PWD",password);
        jsonParams.put("FirstName",fname);
        jsonParams.put("LastName",lname);
        jsonParams.put("Address",addr);
        jsonParams.put("Email",email);
        /*jsonParams.put("UserName",getSaltString() );
        jsonParams.put("PWD","abc");
        jsonParams.put("FirstName","xyz");
        jsonParams.put("LastName","abc");
        jsonParams.put("Address","def");
        jsonParams.put("Email","ghi"); */



        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest myRequest = new JsonObjectRequest(
                Request.Method.POST,
                "http://ec2-13-57-3-28.us-west-1.compute.amazonaws.com:9999/api/members/create",
                new JSONObject(jsonParams),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("resonse","success");
                        Intent intent = new Intent(getApplicationContext(), HomeActivity1.class);
                        intent.putExtra("servcie",wservice);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("resonse",error.toString());
                        LoginErrorMessage();
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

}

