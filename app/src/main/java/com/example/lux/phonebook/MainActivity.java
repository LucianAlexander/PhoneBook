package com.example.lux.phonebook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button logIn,toReg;
    TextView hello;
    EditText user,pass;
    ProgressBar spinn;
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = findViewById(R.id.bt_log);
        toReg = findViewById(R.id.bt_reg);

        hello = findViewById(R.id.tv1);

        user = findViewById(R.id.etUser);
        pass = findViewById(R.id.textView2);

        spinn = findViewById(R.id.progressBar);
        spinn.setVisibility(View.GONE);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = user.getText().toString();
                final String password = pass.getText().toString();
                spinn.setVisibility(View.VISIBLE);
                if (username.length() == 0 || password.length() == 0) {
                    spinn.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                    builder.setMessage("I campi dati non devono essere vuoti")
                            .create()
                            .show();
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSON_STRING = jsonResponse.toString();
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    spinn.setVisibility(View.GONE);

                                    Intent intent = new Intent(MainActivity.this, listaPersone.class);
                                    intent.putExtra("json", JSON_STRING);
                                    MainActivity.this.startActivity(intent);
                                    finishAfterTransition();

                                } else {
                                    spinn.setVisibility(View.GONE);
                                    AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                                    builder.setMessage("Credenziali Sbagliati, Riprova o Registrati")
                                            .create()
                                            .show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    LogInRequest getLogInRequest = new LogInRequest(username,password,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(getLogInRequest);
                }
            }
            });

        toReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = user.getText().toString();
                final String password = pass.getText().toString();
                spinn.setVisibility(View.VISIBLE);
                if (username.length() == 0 || password.length() == 0) {
                    spinn.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                    builder.setMessage("I campi dati non devono essere vuoti")
                            .create()
                            .show();
                }else {
                    Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSON_STRING = jsonResponse.toString();
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                    Intent todata = new Intent(MainActivity.this, listaPersone.class);

                                    todata.putExtra("json", JSON_STRING);
                                    MainActivity.this.startActivity(todata);
                                    spinn.setVisibility(View.GONE);
                                    finishAfterTransition();
                                } else {
                                    spinn.setVisibility(View.GONE);
                                    AlertDialog.Builder builder = new AlertDialog.Builder((MainActivity.this));
                                    builder.setMessage("Errore: Utente esistente, Riprova con un'altro nome")
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    RegRequest registra = new RegRequest(username, password, responseListener2);
                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(registra);
                }
            }
        });
        }
}

