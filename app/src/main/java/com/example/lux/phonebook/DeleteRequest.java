package com.example.lux.phonebook;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static final String DEL_REQUEST_URL = "http://192.168.0.4/sons/delete.php";
    private Map<String,String> params;

    DeleteRequest(String tel, Response.Listener<String>listener){

        super(Request.Method.POST, DEL_REQUEST_URL,listener,null);
        params = new HashMap<>();

        params.put("tel",tel);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

