package com.afrodeb.zamea.zamea;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Delon Savanhu on 2/24/2018.
 */

public class Controller {
    public Context context;
    public String rizo="";
    public  Controller(Context _context){
        this.context=_context;
    }
    public String login(String phone,String idnumber){
        Model model=new Model(context);
        return model.login2(phone,idnumber);
    }

    public String applyLoan(String id,double amount,String type){
        Model model=new Model(context);
        return model.applyLoan(id,amount,type);
    }
        public String getLoanTypes() {
            Model model=new Model(context);
            model.getLoanTypes(new com.afrodeb.zamea.zamea.VolleyCallback(){
                @Override
                public void onSuccess(String result){
                    rizo=result;
                    System.out.println(rizo);
                }
            });

            return rizo;
        }
        }
