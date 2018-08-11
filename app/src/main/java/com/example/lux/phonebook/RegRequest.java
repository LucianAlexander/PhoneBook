package com.example.lux.phonebook;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegRequest extends StringRequest {
    private static final String REG_REQUEST_URL = "http://192.168.0.4/sons/reg.php";
    private Map<String,String> params;

    RegRequest(String user, String password, Response.Listener<String>listener){

        super(Request.Method.POST, REG_REQUEST_URL,listener,null);
        params = new HashMap<>();

        params.put("user",user);

        params.put("password",password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}

