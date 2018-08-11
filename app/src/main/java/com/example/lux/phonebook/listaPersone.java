package com.example.lux.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listaPersone extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    PersonaAdapter personaAdapter;
    ListView listView;
    ArrayList <Persona>listuza;
    Button toInsert;
    ProgressBar spin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_persone);

        spin2 = findViewById(R.id.pb2);
        spin2.setVisibility(View.GONE);

        toInsert = findViewById(R.id.bt_add);

        listView = findViewById(R.id.listview);
        listView.setClickable(true);
        listuza = new ArrayList<>();

        personaAdapter = new PersonaAdapter(this,R.layout.raw_person);

        listView.setAdapter(personaAdapter);

        Intent fromMain = getIntent();

        json_string = fromMain.getStringExtra("json");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String nome,cognome,telefono,indirizzo,eta;

            while(count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                nome = JO.getString("nome");
                cognome = JO.getString("cognome");
                indirizzo = JO.getString("indirizzo");
                telefono = JO.getString("telefono");
                eta = JO.getString("eta");

                Persona persona = new Persona(nome,cognome,indirizzo,telefono,eta);
                personaAdapter.add(persona);
                listuza.add(persona);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        toInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin2.setVisibility(View.VISIBLE);
                Intent toRege = new Intent(listaPersone.this,Insert.class);
                listaPersone.this.startActivity(toRege);

                spin2.setVisibility(View.GONE);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                spin2.setVisibility(View.VISIBLE);
                Intent guarda = new Intent(listaPersone.this, ViewContatto.class);

                guarda.putExtra("nome",listuza.get(pos).getNome());
                guarda.putExtra("cognome",listuza.get(pos).getCognome());
                guarda.putExtra("indirizzo",listuza.get(pos).getIndirizzo());
                guarda.putExtra("tel",listuza.get(pos).getTelefono());
                guarda.putExtra("eta",listuza.get(pos).getEta());
                listaPersone.this.startActivity(guarda);
                spin2.setVisibility(View.GONE);

            }
        });

    }

}
