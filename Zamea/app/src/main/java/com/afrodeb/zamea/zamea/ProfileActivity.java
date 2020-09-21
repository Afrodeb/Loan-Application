package com.afrodeb.zamea.zamea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Helper helper;
TextView fname,lastname,pphone,address,bank_account;
Spinner bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper=new Helper(this);

        fname=(EditText)findViewById(R.id.fname);
        lastname=(EditText)findViewById(R.id.lname);
        pphone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        bank_account=(EditText)findViewById(R.id.bank);
        bank=(Spinner)findViewById(R.id.bank_id);
        final HashMap<Integer,String> bankTypes=new HashMap<Integer,String>();
        System.out.println(helper.getAddress());

        String url = helper.getBaseUrl() + "index.php?r=" + Uri.encode("banks/all-types");
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            ArrayList<String> temp = new ArrayList<String>();
                            JSONArray types = response.getJSONArray("result");
                            for(int i=0;i<types.length();i++) {
                                JSONObject type = types.getJSONObject(i);
                                temp.add(type.getString("name"));
                                bankTypes.put(Integer.parseInt(type.getString("id")),type.getString("name"));
                            }
                            String [] arr=temp.toArray(new String[temp.size()]);
                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(ProfileActivity.this,android.R.layout.simple_spinner_item,arr);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                            bank.setAdapter(adapter);
                            int spinnerPosition = adapter.getPosition(bankTypes.get(Integer.parseInt(helper.getBankID())));
                            bank.setSelection(spinnerPosition);
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

        fname.setText(helper.getName());
        lastname.setText(helper.getLastName());
        address.setText(helper.getAddress());
        pphone.setText(helper.getPhone());
        bank_account.setText(helper.getBankAccount());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        //set user details
        View headerView = navigationView.getHeaderView(0);
        TextView name=(TextView)headerView.findViewById(R.id.name);
        name.setText(WordUtils.capitalize(helper.getName() +" "+helper.getLastName()));
        TextView phone=(TextView)headerView.findViewById(R.id.phone);
        phone.setText(helper.getZamea());


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);


        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    final String fullname=fname.getText().toString();
                    final String last_name=lastname.getText().toString();
                    final String phoneNumber=pphone.getText().toString();
                    final String add=address.getText().toString();
                    final String bankAcc=bank_account.getText().toString();
                    final String bankId=bank.getSelectedItem().toString();
                    final String id=helper.getId();
                    final ProgressDialog pDialog = new ProgressDialog(ProfileActivity.this);
                    String url2=helper.getBaseUrl() + "index.php?r=" +Uri.encode("members/mupdate")+
                            "&fullname="+Uri.encode(fullname)+
                            "&lastname="+Uri.encode(last_name)+
                            "&phoneNumber="+Uri.encode(phoneNumber)+
                            "&bank_id="+Uri.encode(bankId)+
                            "&account="+Uri.encode(bankAcc)+
                            "&address="+Uri.encode(add)+
                            "&id="+Uri.encode(id);
                    pDialog.setMessage("Updating profile...");
                    pDialog.show();
                    StringRequest postRequest2 = new StringRequest(Request.Method.POST, url2,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        //System.out.println(response);
                                        //JSONObject jsonResponse = new JSONObject(response);
                                        helper.updateLoggin(fullname,last_name,helper.getKeyFromValue(bankTypes,bankId).toString(),bankAcc,add,phoneNumber);
                                        Toast.makeText(ProfileActivity.this,"Your account has been updated.",Toast.LENGTH_LONG).show();
                                        pDialog.dismiss();
                                    }catch (Exception ex){
                                        pDialog.dismiss();
                                        ex.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    pDialog.dismiss();
                                    //Log.d("Error.Response", error.getMessage());
                                    Toast.makeText(ProfileActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("fullname", fullname);
                            params.put("lastname", last_name);
                            params.put("phoneNumber", phoneNumber);
                            params.put("bank_id", bankId);
                            params.put("account", bankAcc);
                            params.put("address", add);
                            return params;
                        }
                    };
                    postRequest2.setRetryPolicy(new DefaultRetryPolicy(80000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    Volley.newRequestQueue(ProfileActivity.this).add(postRequest2);



                }catch (Exception ex){
                    ex.printStackTrace();
                }



            }
        });



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
        getMenuInflater().inflate(R.menu.profile, menu);
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

        helper.actions(ProfileActivity.this,id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
