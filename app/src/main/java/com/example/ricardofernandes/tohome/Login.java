package com.example.ricardofernandes.tohome;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mNomeLogin = (EditText) findViewById(R.id.etUsername);
        String user;
        user = "Cliente";
        validaLogin(user);
    }


    Button mbtnEntraLogin;
    EditText mNomeLogin;

    public void validaLogin(String user) {
        if(user.equals("Cliente")){
            LoginOnButtonClick();
        }
        else{
            showAlert();
        }
    }

    public void LoginOnButtonClick(){
        mbtnEntraLogin = (Button) findViewById(R.id.btnEntrar);
            mbtnEntraLogin.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent it = new Intent(Login.this, EscolhaProjeto.class);
                            startActivity(it);
                        }
                    }
            );
        }


    public void showAlert() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Usuário e/ou senha estão incorretos")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int i1){
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Login Inválido!")
                .create();
        myAlert.show();

    }

}
