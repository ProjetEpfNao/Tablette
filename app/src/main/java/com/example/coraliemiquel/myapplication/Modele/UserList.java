package com.example.coraliemiquel.myapplication.Modele;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Coralie MIQUEL on 03/11/2016.
 */

public class UserList extends Activity {
        private String array_spinner[];
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
         /*   setContentView(R.layout.admin_activity);
            // Here come all the options that you wish to show depending on the
            // size of the array.
            array_spinner=new String[5];
            array_spinner[0]="option 1";
            array_spinner[1]="option 2";
            array_spinner[2]="option 3";
            array_spinner[3]="option 4";
            array_spinner[4]="option 5";
            Spinner s = (Spinner) findViewById(R.id.Spinner01);
            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, array_spinner);
            s.setAdapter(adapter);*/
        }

}
