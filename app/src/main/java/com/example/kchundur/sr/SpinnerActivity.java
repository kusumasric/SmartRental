package com.example.kchundur.sr;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by kchundur on 12/3/2017.
 */

public class SpinnerActivity implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)


    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
