package com.afrodeb.zamea.zamea;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Delon Savanhu on 2/24/2018.
 */

public class Model {
Context context;
public String result="";
Helper helper;
ArrayList<String> rizo=new ArrayList<String>();
public Model(Context _context){
    this.context=_context;
    helper=new Helper(context);
}
    public String login(final String phone) {
/*    String url = helper.getBaseUrl()+"index.php?r=members/login";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response).getJSONObject("");
                            //String site = jsonResponse.getString("site"),
                              //      network = jsonResponse.getString("network");
                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("phone", phone);
                return params;
            }

        };
        Volley.newRequestQueue(context).add(postRequest);*/
return "";
    }
    public String login2(String phone,String idnumber){
        String url = helper.getBaseUrl()+"index.php?r="+Uri.encode("members/login")+"&phone="+Uri.encode(phone)+"&id="+Uri.encode(idnumber);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            response = response.getJSONObject("result");
                            String name = response.getString("name");
                            String lastname = response.getString("lastname");
                            String phone = response.getString("mobile_number");
                            String savingsClubNumber = response.getString("savings_club_number");
                            String id = response.getString("id");
                            //helper.setLoggin(name+" "+lastname,phone,savingsClubNumber,id);
                            System.out.println(name);
                            result= "success";
                        } catch (JSONException e) {
                            e.printStackTrace();
                            result= "failed";
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        result= "failed";
                    }
                });
        Volley.newRequestQueue(context).add(jsonRequest);

        return result;
    }

    public String applyLoan(String id,double amount,String type){

      String url = helper.getBaseUrl()+"index.php?r="+Uri.encode("loan/apply")+"&id="+Uri.encode(id)+"&amount="+Uri.encode(Double.toString(amount))+"&type="+Uri.encode(type);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            //response = response.getJSONObject("result");
                            result=response.toString();
                           System.out.println(result);
                           // return result;
                        } catch (Exception e) {
                            e.printStackTrace();
                            result= "failed";
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        result= "failed";
                    }
                });
        Volley.newRequestQueue(context).add(jsonRequest);

        return result;
    }

    public String getLoanTypes(final com.afrodeb.zamea.zamea.VolleyCallback callback){
        String url = helper.getBaseUrl() + "index.php?r=" + Uri.encode("loan-types/all-types");
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //result=response.toString();
                            result="delon";
                            callback.onSuccess(response.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            result= "failed";
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        result= "failed";
                    }
                });
        Volley.newRequestQueue(context).add(jsonRequest);
        return result;
    }

    public String getLoanTypes2 (){
/*        try {
            String url = helper.getBaseUrl() + "index.php?r=" + Uri.encode("loan-types/all-types");
            System.out.print(url);
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        //urlBuilder.addQueryParameter("v", "1.0");
        //urlBuilder.addQueryParameter("user", "vogella");
        String urlFinal = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(urlFinal)
                .build();
            OkHttpClient client = new OkHttpClient();
            //client.setConnectTimeout(5, TimeUnit.MINUTES);
            //client.setReadTimeout(5, TimeUnit.MINUTES);
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            Log.d("mmmmm",response.body().string());
            result=response.body().string();
         //   return response.body().string();
        }catch(IOException ex){
            ex.printStackTrace();
        }*/
        return result;
            //return rizo.toArray(new String[0]);
    }

}
