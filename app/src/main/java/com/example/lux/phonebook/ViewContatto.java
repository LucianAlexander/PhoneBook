package com.example.lux.phonebook;

import android.content.Intent;
import android.net.Uri;
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

public class ViewContatto extends AppCompatActivity {
    EditText tnome,tcognome,tindirizzo,ttelefono,teta;
    Button mod,call,del;
    String JSON_STRING;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contatto);

        pb = findViewById(R.id.progBar);
        pb.setVisibility(View.GONE);

        tnome = findViewById(R.id.etName);
        tcognome = findViewById(R.id.etCognome);
        tindirizzo = findViewById(R.id.etIndir);
        ttelefono =findViewById(R.id.etTel);
        teta = findViewById(R.id.et_Eta);

        mod = findViewById(R.id.aggiorna);
        call = findViewById(R.id.chiama);
        del = findViewById(R.id.delete);

        Intent data = getIntent();

        final String oldtel = data.getStringExtra("tel");

        tnome.setText(data.getStringExtra("nome"));
        tcognome.setText(data.getStringExtra("cognome"));
        tindirizzo.setText(data.getStringExtra("indirizzo"));
        teta.setText(data.getStringExtra("eta"));
        ttelefono.setText(data.getStringExtra("tel"));

        call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                dialContactPhone(ttelefono.getText().toString());
                pb.setVisibility(View.GONE);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                final String tel2 = ttelefono.getText().toString();
                if(tel2.length()==0){
                    pb.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder((ViewContatto.this));
                    builder.setMessage("Inserisci il numero di telefono!!")
                            .create()
                            .show();
                }else{
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSON_STRING = jsonResponse.toString();
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    pb.setVisibility(View.GONE);
                                    Intent intent = new Intent(ViewContatto.this, listaPersone.class);
                                    intent.putExtra("json", JSON_STRING);
                                    ViewContatto.this.startActivity(intent);
                                    finishAfterTransition();


                                } else {
                                    pb.setVisibility(View.GONE);
                                    AlertDialog.Builder builder = new AlertDialog.Builder((ViewContatto.this));
                                    builder.setMessage("Credenziali Sbagliati, Riprova o Registrati")
                                            .create()
                                            .show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    DeleteRequest delreq = new DeleteRequest(tel2,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ViewContatto.this);
                    queue.add(delreq);
                }

            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);

               final String nome = tnome.getText().toString();
               final String cognome = tcognome.getText().toString();
               final String indirizzo = tindirizzo.getText().toString();
               final String telef = ttelefono.getText().toString();
               final String eta = teta.getText().toString();

                if(nome.length()==0||cognome.length()==0||indirizzo.length()==0||telef.length()==0||eta.length()==0){
                    pb.setVisibility(View.GONE);
                    AlertDialog.Builder builder = new AlertDialog.Builder((ViewContatto.this));
                    builder.setMessage("I campi dati non devono essere vuoti")
                            .create()
                            .show();

                }else{
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSON_STRING = jsonResponse.toString();
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    pb.setVisibility(View.GONE);
                                    Intent intent = new Intent(ViewContatto.this, listaPersone.class);
                                    intent.putExtra("json", JSON_STRING);
                                    ViewContatto.this.startActivity(intent);
                                    finishAfterTransition();

                                } else {
                                    pb.setVisibility(View.GONE);
                                    AlertDialog.Builder builder = new AlertDialog.Builder((ViewContatto.this));
                                    builder.setMessage("Attenzione, Il numero di telefono deve essere unico!")
                                            .create()
                                            .show();


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    ModificaRequest modreq = new ModificaRequest(nome,cognome,indirizzo,telef,oldtel,eta,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(ViewContatto.this);
                    queue.add(modreq);
                }

            }
        });
    }
    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
