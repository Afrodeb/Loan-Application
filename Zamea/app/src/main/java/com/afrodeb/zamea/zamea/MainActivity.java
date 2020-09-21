package com.afrodeb.zamea.zamea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Controller controller;
Helper helper;
    ProgressBar myprogressBar;
    TextView current,accepted,average,payments;
    Handler progressHandler = new Handler();
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        helper=new Helper(this);
        if (helper.initialise()){//user is not logged in,do nothing
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        }else{
        }
        accepted=(TextView)findViewById(R.id.accepted);
        average=(TextView)findViewById(R.id.average);
        payments=(TextView)findViewById(R.id.payments);
        current=(TextView)findViewById(R.id.current);

        try{
            final ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Collecting information...");
            pDialog.show();
            String url = helper.getBaseUrl()+"index.php?r="+ Uri.encode("loan/dashboard")+"&id="+Uri.encode(helper.getId());
            JsonObjectRequest jsonRequest2 = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                response=response.getJSONObject("result");
                                    String ac=response.getString("status");
                                    String total=response.getString("total_payments");
                                    String averageP=response.getString("average");
                                    String current_total=response.getString("current_total");
                                    if(total.equals("null")){
                                        total="0";
                                    }
                                    if(averageP.equals("null")){
                                        averageP="0";
                                    }
                                    if(current_total.equals("null")){
                                      current_total="0";
                                    }
                                accepted.setText(ac);
                                average.setText("$"+averageP);
                                payments.setText("$"+total);
                                current.setText("$"+current_total);
                                pDialog.dismiss();
                            } catch (Exception e) {
                                pDialog.dismiss();
                                Toast.makeText(MainActivity.this,"We have issues collecting data",Toast.LENGTH_LONG).show();
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
            jsonRequest2.setRetryPolicy(new DefaultRetryPolicy(80000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(MainActivity.this).add(jsonRequest2);


            // Toast.makeText(ApplyActivity.this,rizo,Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(this);
        //set user details
        View headerView = navigationView.getHeaderView(0);
        TextView name=(TextView)headerView.findViewById(R.id.name);
        name.setText(WordUtils.capitalize(helper.getName() +" "+helper.getLastName()));
        TextView phone=(TextView)headerView.findViewById(R.id.phone);
        phone.setText(WordUtils.capitalize(helper.getZamea()));
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        helper.actions(MainActivity.this,id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
