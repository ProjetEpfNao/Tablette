package com.example.coraliemiquel.myapplication.Vue.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coraliemiquel.myapplication.Manager.ConnectionManager;
import com.example.coraliemiquel.myapplication.R;

/**
 * Created by Coralie MIQUEL on 07/12/2016.
 */

public class SayItDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialogue_activity, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Activity myContext = SayItDialogFragment.this.getActivity();
                        View myView = view.findViewById(R.id.message);
                        String message  = ((EditText)myView).getText().toString();
                        ConnectionManager myConnectionManager = ConnectionManager.getInstance(view.getContext());
                        myConnectionManager.sayIt(message);

                        InputMethodManager inputManager = (InputMethodManager) myContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.hideSoftInputFromWindow(myView.getWindowToken(), 0);


                        Toast.makeText(myContext,"message envoyé à Nao",Toast.LENGTH_SHORT).show();
                        SayItDialogFragment.this.getDialog().cancel();
                    }
                })
                .setNegativeButton("annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Activity myContext = SayItDialogFragment.this.getActivity();
                        View myView = view.findViewById(R.id.message);

                        InputMethodManager inputManager = (InputMethodManager) myContext.getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(myView.getWindowToken(), 0);

                        SayItDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
