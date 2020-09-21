package com.afrodeb.zamea.zamea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApplyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
public Spinner spinner;
public Button submit;
public EditText amount;
public Controller controller;//=new Controller();
    public String [] loanTypeArray;
    public String result;
public  Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner=(Spinner)findViewById(R.id.types);
        final EditText amount=(EditText)findViewById(R.id.amount);
        helper=new Helper(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        controller=new Controller(ApplyActivity.this);
        String url = helper.getBaseUrl() + "index.php?r=" + Uri.encode("loan-types/all-types");
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            HashMap<Integer,String> loanTypes=new HashMap<Integer,String>();
                            ArrayList<String> temp = new ArrayList<String>();
                            //jsonResponse = new JSONObject(json);
                            JSONArray types = response.getJSONArray("result");
                            for(int i=0;i<types.length();i++) {
                                JSONObject type = types.getJSONObject(i);
                                //JSONArray characters = type.getJSONArray("characters");
                                //for(int j=0;j<characters.length();j++){
                                temp.add(type.getString("name"));
                               // Toast.makeText(ApplyActivity.this,type.getString("name"),Toast.LENGTH_LONG).show();
                                //}
                            }
                            String [] arr=temp.toArray(new String[temp.size()]);
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(ApplyActivity.this,android.R.layout.simple_spinner_item,arr);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                            spinner.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            //result= "failed";
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //result= "failed";
                    }
                });
        Volley.newRequestQueue(this).add(jsonRequest);




        submit=(Button) findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    double amo=Double.parseDouble(amount.getText().toString());
                    String type=spinner.getSelectedItem().toString();
//                    String rizo=controller.applyLoan("1",amo,type);
                    String url = helper.getBaseUrl()+"index.php?r="+Uri.encode("loan/apply")+"&id="+Uri.encode(helper.getId())+"&amount="+Uri.encode(Double.toString(amo))+"&type="+Uri.encode(type);
                    JsonObjectRequest jsonRequest2 = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // the response is already constructed as a JSONObject!
                                    try {
                                        result=response.toString();
                                        response=response.getJSONObject("result");
                                        System.out.println(result);
               Toast.makeText(ApplyActivity.this,response.getString("message"),Toast.LENGTH_LONG).show();
               if(response.getString("message").equals("Loan application successful")){
               Intent i=new Intent(ApplyActivity.this,HistoryActivity.class);
               }
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
                    Volley.newRequestQueue(ApplyActivity.this).add(jsonRequest2);


                    // Toast.makeText(ApplyActivity.this,rizo,Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MyTask myTask = new MyTask();
        navigationView.setNavigationItemSelectedListener(this);
        //set user details
        View headerView = navigationView.getHeaderView(0);
        TextView name=(TextView)headerView.findViewById(R.id.name);
        name.setText(WordUtils.capitalize(helper.getName() +" "+helper.getLastName()));
        TextView phone=(TextView)headerView.findViewById(R.id.phone);
        phone.setText(helper.getZamea());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apply, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        helper.actions(ApplyActivity.this,id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class MyTask extends AsyncTask<String, String, String> {
        private String resp;
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                Log.d("pppppppp","jjjjjjjjjjjjjjjj");
                controller=new Controller(ApplyActivity.this);
                resp=controller.getLoanTypes();
            }catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {

            // execution of result of Long time consuming operation
            Toast.makeText(ApplyActivity.this,result,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            //finalResult.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ApplyActivity.this,
                    "ProgressDialog",
                    "Wait for seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            //finalResult.setText(text[0]);

        }
    }

}
