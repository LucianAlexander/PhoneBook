package com.example.lux.phonebook;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertRequest extends StringRequest {

    private static final String INSERT_REQUEST_URL = "http://192.168.0.4/sons/insert.php";
    private Map<String,String> params;

    InsertRequest(String nome, String cognome, String indirizzo, String telefono, String eta , Response.Listener<String>listener){

        super(Request.Method.POST, INSERT_REQUEST_URL,listener,null);
        params = new HashMap<>();

        params.put("nome",nome);

        params.put("cognome",cognome);

        params.put("indir",indirizzo);

        params.put("tel",telefono);

        params.put ("eta",eta);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
