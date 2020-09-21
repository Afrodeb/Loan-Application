package com.afrodeb.zamea.zamea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.ListView;
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

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<LoanModel> loanModels;
    ListView listView;
    private static CustomAdapter adapter;
    ArrayList<LoanModel> productModels;
    Helper helper;
    public String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        helper=new Helper(HistoryActivity.this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        listView=(ListView)findViewById(R.id.list);
        loanModels = new ArrayList<>();
        try{
            final ProgressDialog pDialog = new ProgressDialog(HistoryActivity.this);
            pDialog.setMessage("Collecting information...");
            pDialog.show();
            String url = helper.getBaseUrl()+"index.php?r="+ Uri.encode("loan/all")+"&id="+Uri.encode(helper.getId());
            JsonObjectRequest jsonRequest2 = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                result=response.toString();
                                JSONArray rizo=response.getJSONArray("result");
                                int count=rizo.length();
                                for(int x=0;x < count; x++) {
                                    JSONObject obj=rizo.getJSONObject(x);
                                    String id=obj.getString("id");
                                    String member_id=obj.getString("member_id");
                                    String amount_required=obj.getString("amount_required");
                                    String current_loan_amount=obj.getString("current_loan_amount");
                                    String current_monthly_payments=obj.getString("current_monthly_payment");
                                    String created=obj.getString("lcreated");
                                    String loan_type=obj.getString("loan_type");
                                    String status=obj.getString("status");
                                    String name=obj.getString("name");
                                    loanModels.add(new LoanModel(member_id,amount_required,current_loan_amount,current_monthly_payments,created,name,status,id));
                                }
                                adapter= new CustomAdapter(loanModels,getApplicationContext());
                                listView.setAdapter(adapter);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        LoanModel loanModel = loanModels.get(position);
                                        Intent i=new Intent(HistoryActivity.this,DetailsActivity.class);
                                        i.putExtra("id",loanModel.getId());
                                        i.putExtra("member_id",loanModel.getMemberid());
                                        i.putExtra("amount_required",loanModel.getAmountRequired());
                                        i.putExtra("current_loan_amount",loanModel.getCurrentLoanAmount());
                                        i.putExtra("current_monthly_payments",loanModel.getCurrentMonthlyPayments());
                                        i.putExtra("created",loanModel.getCreated());
                                        i.putExtra("loan_type",loanModel.getLoanType());
                                        i.putExtra("status",loanModel.getStatus());
                                        startActivity(i);
                                        //Snackbar.make(view, productModel.getName()+"\n"+productModel.getType()+"  "+productModel.getVersion_number(), Snackbar.LENGTH_LONG)
                                        //      .setAction("No action", null).show();
                                    }
                                });
                                pDialog.dismiss();
                            } catch (Exception e) {
                                pDialog.dismiss();
                                Toast.makeText(HistoryActivity.this,"We have issues collecting data",Toast.LENGTH_LONG).show();
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
            jsonRequest2.setRetryPolicy(new DefaultRetryPolicy(80000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Volley.newRequestQueue(HistoryActivity.this).add(jsonRequest2);

            navigationView.setNavigationItemSelectedListener(this);
            //set user details
            View headerView = navigationView.getHeaderView(0);
            TextView name=(TextView)headerView.findViewById(R.id.name);
            name.setText(WordUtils.capitalize(helper.getName() +" "+helper.getLastName()));
            TextView phone=(TextView)headerView.findViewById(R.id.phone);
            phone.setText(WordUtils.capitalize(helper.getZamea()));

            // Toast.makeText(ApplyActivity.this,rizo,Toast.LENGTH_LONG).show();
        }catch (Exception ex){
            ex.printStackTrace();
        }



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
        getMenuInflater().inflate(R.menu.history, menu);
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

        helper.actions(HistoryActivity.this,id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
