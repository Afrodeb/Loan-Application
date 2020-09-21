package com.afrodeb.zamea.zamea;

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
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
public Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            helper=new Helper(this);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            String id = getIntent().getStringExtra("id");
            String amount_required = getIntent().getStringExtra("amount_required");
            String current_loan_amount = getIntent().getStringExtra("current_loan_amount");
            String current_monthly_payments = getIntent().getStringExtra("current_monthly_payments");
            String created = getIntent().getStringExtra("created");
            String loan_type = getIntent().getStringExtra("loan_type");
            String status = getIntent().getStringExtra("status");
            String member_id = getIntent().getStringExtra("member_id");
            setTitle("$" + amount_required + " - " + status);
            TextView tid = (TextView) findViewById(R.id.member_id);
            TextView tamount = (TextView) findViewById(R.id.amount_required);
            TextView tCurrentLoan = (TextView) findViewById(R.id.current_loan);
            TextView tCurrentPayment = (TextView) findViewById(R.id.current_monthly_payment);
            TextView tCreated = (TextView) findViewById(R.id.created);
            TextView tLoanType = (TextView) findViewById(R.id.loan_type);
            TextView tStatus = (TextView) findViewById(R.id.status);
            //SimpleDateFormat ft = new SimpleDateFormat ("MMM dd HH:mm:ss yyyy", Locale.US);
            //Date date = ft.parse(created);
            tid.setText(id);
            tamount.setText("$"+amount_required);
            tCurrentLoan.setText("$"+current_loan_amount);
            tCurrentPayment.setText("$"+current_monthly_payments);
            tCreated.setText(created);
            tLoanType.setText(loan_type);
            tStatus.setText(status);
            navigationView.setNavigationItemSelectedListener(this);
            //set user details
            View headerView = navigationView.getHeaderView(0);
            TextView name=(TextView)headerView.findViewById(R.id.name);
            name.setText(WordUtils.capitalize(helper.getName() +" "+helper.getLastName()));
            TextView phone=(TextView)headerView.findViewById(R.id.phone);
            phone.setText(helper.getZamea());

        }catch(Exception ex){
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
        getMenuInflater().inflate(R.menu.details, menu);
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

        helper.actions(DetailsActivity.this,id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
