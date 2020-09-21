package com.afrodeb.zamea.zamea;

/**
 * Created by Delon Savanhu on 3/8/2018.
 */

public class LoanModel {

    String id;
    String member_id;
    String amount_required;
    String current_loan_amount;
    String current_monthly_payments;
    String created;
    String loan_type;
    String status;


    public LoanModel(String member_id, String amount_required, String current_loan_amount, String current_monthly_payments, String created, String loan_type, String status, String id ) {
        this.member_id=member_id;
        this.amount_required=amount_required;
        this.current_loan_amount=current_loan_amount;
        this.current_monthly_payments=current_monthly_payments;
        this.created=created;
        this.loan_type=loan_type;
        this.status=status;
        this.id=id;
    }

    public String getId(){return  id;}

    public String getMemberid(){ return member_id; }

    public String getCurrentLoanAmount() {
        return current_loan_amount;
    }

    public String getCurrentMonthlyPayments() {
        return current_monthly_payments;
    }

    public String getCreated() {
        return created;
    }

    public String getLoanType(){return loan_type; }

    public String getStatus(){ return status; }

    public String getAmountRequired(){return amount_required; }
}
