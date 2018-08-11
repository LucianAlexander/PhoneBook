package com.example.lux.phonebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public PersonaAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(Persona object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        PersonaHolder personaHolder;
        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.raw_person,parent,false);

            personaHolder = new PersonaHolder();
            personaHolder.tx_nome = row.findViewById(R.id.nume);
            personaHolder.tx_cognome = row.findViewById(R.id.prenume);
            personaHolder.tx_tel = row.findViewById(R.id.telefon);

            row.setTag(personaHolder);
        }else{

            personaHolder = (PersonaHolder) row.getTag();
        }

        Persona persone = (Persona) this.getItem(position);

        personaHolder.tx_nome.setText(persone.getNome());
        personaHolder.tx_cognome.setText(persone.getCognome());
        personaHolder.tx_tel.setText(persone.getTelefono());

        return row;
    }

    static class PersonaHolder{
        TextView tx_nome,tx_cognome,tx_tel;

    }
}
