package com.example.kchundur.sr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by kchundur on 12/3/2017.
 */

public class Signup extends Activity {
    private EditText et_Name,et_Pass, et_ConPass , et_email, et_phonenumber;
   String wservice;
   int memberid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_signup);
        wservice= getIntent().getStringExtra("Service");

        et_Name = (EditText)findViewById(R.id.name);
        et_Pass = (EditText)findViewById(R.id.password);
        et_ConPass = (EditText)findViewById(R.id.ConfirmPassword);
        et_email=(EditText)findViewById(R.id.email);
        et_phonenumber=(EditText)findViewById(R.id.Phonenumber);

        Spinner spinner = (Spinner) findViewById(R.id.Spinner_service);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Service_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void onClickSignUp(View view)
    {
        String password = et_Pass.getText().toString();
        String confPass = et_ConPass.getText().toString();
        String email=et_email.getText().toString();
        String phonenumber=et_phonenumber.getText().toString();


    }

    public void onClickcancel(View view)
    {
        moveTaskToBack(true);
        finish();


    }


    private void LoginErrorMessage()
    {
        android.app.AlertDialog.Builder dlgAlert = new android.app.AlertDialog.Builder(this);
        dlgAlert.setMessage("wrong password or username");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.show();
    }
}

