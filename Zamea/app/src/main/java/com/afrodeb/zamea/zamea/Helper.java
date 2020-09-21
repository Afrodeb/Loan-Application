package com.afrodeb.zamea.zamea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Delon Savanhu on 2/24/2018.
 */

public class Helper {
    Context context;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Zamea = "zameaKey";
    public static final String LastName = "lastnameKey";
    public static final String BankID = "bankIdKey";
    public static final String BankAccount = "bankAccountKey";
    public static final String Address = "addressIdKey";//
    public static final String MembershipNumberAccount = "membershipNumberKey";
    public static final String ID = "idKey";
    SharedPreferences sharedpreferences;

    public Helper(Context _context){
        this.context=_context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    public boolean initialise(){
        return sharedpreferences.getString(Name,"").toString().equals("");
    /*if (sharedpreferences.getString(Name,"").toString().equals("")){
        return false;
    }else{
        return true;
    }*/
    }
    public boolean setLoggin(String name,String lastname,String bankID,String accountNumber,String address,String membershipNumberAccount,String phone,String zamea,String id){
        try{
            SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, name);
            editor.putString(LastName, lastname);
            editor.putString(BankAccount, accountNumber);
            editor.putString(BankID, bankID);
            editor.putString(Address, address);
            editor.putString(MembershipNumberAccount, membershipNumberAccount);
        editor.putString(Phone, phone);
        editor.putString(Zamea, zamea);
        editor.putString(ID, id);
        editor.commit();
        return true;
        }catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
    }
    public boolean updateLoggin(String name,String lastname,String bankID,String accountNumber,String address,String phone){
        try{
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Name, name);
            editor.putString(LastName, lastname);
            editor.putString(BankAccount, accountNumber);
            editor.putString(BankID, bankID);
            editor.putString(Address, address);
            editor.putString(Phone, phone);
            editor.commit();
            return true;
        }catch(Exception exc){
            exc.printStackTrace();
            return false;
        }
    }

//    public String getBaseUrl(){
    //    return "http://172.16.35.44:8080/loans/backend/web/";
    //}
    public String getBaseUrl(){
        return "http://loans.delon.co.zw/";
    }

    public String getId(){
        return sharedpreferences.getString(ID,"");
    }
    public String getName(){
        return sharedpreferences.getString(Name,"");
    }
    public String getLastName(){
        return sharedpreferences.getString(LastName,"");
    }
    public String getBankID(){
        return sharedpreferences.getString(BankID,"");
    }
    public String getBankAccount(){
        return sharedpreferences.getString(BankAccount,"");
    }
    public String getAddress(){
        return sharedpreferences.getString(Address,"");
    }
    public String getPhone(){
        return sharedpreferences.getString(Phone,"");
    }
    public String getMembershipNumberAccount(){
        return sharedpreferences.getString(MembershipNumberAccount,"");
    }
    public String getZamea(){
        return sharedpreferences.getString(Zamea,"");
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }
    public void actions(Context context, int target) {
        Intent i;
        if (target == R.id.nav_apply) {
            i = new Intent(context, ApplyActivity.class);
            context.startActivity(i);
        } else if (target == R.id.nav_history) {
            i = new Intent(context, HistoryActivity.class);
            context.startActivity(i);
        }else if(target == R.id.nav_logout){
            this.logout();
            i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
        }else if (target == R.id.nav_profile) {
            i = new Intent(context, ProfileActivity.class);
            context.startActivity(i);
        }
    }
    public Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

}
