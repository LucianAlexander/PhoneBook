package com.example.lux.phonebook;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Insert extends AppCompatActivity {
    EditText nome,cognome,indirizzo,telefono,eta;
    Button inserisci;
    ProgressBar spin3;
    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        nome = findViewById(R.id.etName);
        cognome = findViewById(R.id.etCognome);
        indirizzo = findViewById(R.id.etIndir);
        telefono = findViewById(R.id.etTel);
        eta = findViewById(R.id.et_Eta);

        spin3 = findViewById(R.id.pb4);
        spin3.setVisibility(View.GONE);

        inserisci = findViewById(R.id.but4);

        inserisci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = nome.getText().toString();
                final String cog = cognome.getText().toString();
                final String indir = indirizzo.getText().toString();
                final String tel = telefono.getText().toString();
                final String age = eta.getText().toString();

                spin3.setVisibility(View.VISIBLE);

                if(name.length()==0||cog.length()==0||indir.length()==0||tel.length()==0||age.length()==0){
                    spin3.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder((Insert.this));
                    builder.setMessage("I campi dati non devono essere vuoti")
                            .create()
                            .show();

                }else{

                    Response.Listener<String> responseListener3 = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSON_STRING = jsonResponse.toString();
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    spin3.setVisibility(View.GONE);
                                    Intent todata = new Intent(Insert.this, listaPersone.class);

                                    todata.putExtra("json", JSON_STRING);
                                    Insert.this.startActivity(todata);
                                    finishAfterTransition();

                                } else {
                                    spin3.setVisibility(View.GONE);
                                    AlertDialog.Builder builder = new AlertDialog.Builder((Insert.this));
                                    builder.setMessage("Errore: Contatto Esistente controlla i dati")
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    InsertRequest insertion = new InsertRequest(name, cog, indir,tel,age,responseListener3);
                    RequestQueue queue = Volley.newRequestQueue(Insert.this);
                    queue.add(insertion);

                }

            }
        });
    }
}
