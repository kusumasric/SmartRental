package com.example.kchundur.sr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by siddhiparekh11 on 12/2/17.
 */

public class MainAct extends Activity {

    TextView t;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_question);
         t=(TextView)findViewById(R.id.txt1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent b=getIntent();
        String s1="";
             s1=(b.getStringExtra("answer1").toString()) + " " ;
             s1= s1  + (getIntent().getStringExtra("answer2").toString()) + " " ;
             s1=s1+ (getIntent().getStringExtra("answer3").toString())+ " ";
             s1=s1+ (getIntent().getStringExtra("answer4").toString());
        t.setText(s1);
        Log.d("myTag", (String) t.getText());


    }

    public void onclickNext(View view)
    {

         Intent intent = new Intent(getApplicationContext(), MainActivity.class);
         startActivity(intent);

    }

}
