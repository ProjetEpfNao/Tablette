package com.example.coraliemiquel.myapplication.Vue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coraliemiquel.myapplication.Manager.ConnectionManager;
import com.example.coraliemiquel.myapplication.R;

/**
 * Created by Coralie MIQUEL on 16/11/2016.
 */

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        Button ajouterCompte = (Button)findViewById(R.id.ajouterButton);
        ajouterCompte.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AdminActivity.this.onClickCreatNouveauCompte(v);
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //changement de vue
        Intent intent = new Intent(AdminActivity.this, ConnectActivity.class);
        ConnectionManager.getInstance(this).logout();
        startActivity(intent);
        return  super.onOptionsItemSelected(item);
    }


    public void onClickCreatNouveauCompte(View myView){

        EditText indentifiantEditText  = (EditText) findViewById(R.id.identifiant);
        EditText motDePasseEditText  = (EditText) findViewById(R.id.motDePasse);
        EditText ipNaoEditText  = (EditText) findViewById(R.id.ipNao);

        String identifiant = indentifiantEditText.getText().toString();
        String motDePasse = motDePasseEditText.getText().toString();
        String ipNao =  ipNaoEditText.getText().toString();

        if(identifiant != null && motDePasse != null &&  ipNao!=null) {

            Toast myToast  = new Toast(getApplicationContext());
            myToast.setView(myView);
            myToast.setDuration(Toast.LENGTH_SHORT);

            if(identifiant.isEmpty()){
                myToast.setText("Veuillez rentrer un identifiant");
            }else if (motDePasse.isEmpty()){
                myToast.setText("Veuillez rentrer un mot de passe");
            }else if (ipNao.isEmpty()){
                myToast.setText("Veuillez rentrer l'up du robot Nao associé");
            }else {
                ConnectionManager connectionManager = ConnectionManager.getInstance(this);
                connectionManager.creatNewUser(identifiant,motDePasse,ipNao);
                myToast.setText("Compte créé");
            }
            myToast.show();
        }


    }
}
