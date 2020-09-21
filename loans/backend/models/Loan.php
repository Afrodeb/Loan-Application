<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "loan".
 *
 * @property integer $id
 * @property integer $member_id
 * @property double $amount_required
 * @property double $current_loan_amount
 * @property double $current_monthly_payment
 * @property string $created
 * @property string $status
 * @property integer $loan_type
 */
class Loan extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'loan';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['member_id', 'amount_required', 'current_loan_amount', 'current_monthly_payment', 'status', 'loan_type'], 'required'],
            [['member_id', 'loan_type'], 'integer'],
            [['amount_required', 'current_loan_amount', 'current_monthly_payment'], 'number'],
            [['created'], 'safe'],
            [['status'], 'string'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'member_id' => 'Member',
            'amount_required' => 'Amount Required',
            'current_loan_amount' => 'Current Loan Amount',
            'current_monthly_payment' => 'Current Monthly Payment',
            'created' => 'Applied On',
            'status' => 'Status',
            'loan_type' => 'Loan Type',
        ];
    }

	public function getCurrentLoanAmount($member){
		$connection = Yii::$app->getDb();
        $command = $connection->createCommand("SELECT SUM(amount_required) AS p FROM loan WHERE member_id=:mid",['mid'=>$member]);
        return $command->queryAll();
	}
	public function getAll($id){
	$connection = Yii::$app->getDb();
        $command = $connection->createCommand("SELECT loan.*,DATE_FORMAT(loan.created, '%d %M %Y') AS lcreated,loan_types.name FROM loan,loan_types WHERE loan.loan_type=loan_types.id AND loan.member_id=:id",['id'=>$id]);
        return $command->queryAll();	
	}
	
	public function getTotalAccepted($id){
	$connection = Yii::$app->getDb();
    $command = $connection->createCommand("SELECT SUM(loan.amount_required) AS current_total FROM loan WHERE loan.member_id=:id AND status='Approved'",['id'=>$id]);
    return $command->queryAll();	
	}
	public function getUserAccepted($id){
	$connection = Yii::$app->getDb();
    $command = $connection->createCommand("SELECT COUNT(status) AS status FROM loan WHERE loan.member_id=:id AND status='Approved'",['id'=>$id]);
    return $command->queryAll();	
	}
	public function getAveragePayment($id){
	$connection = Yii::$app->getDb();
    $command = $connection->createCommand("SELECT AVG(amount) AS average,SUM(amount) AS total_payments FROM payment WHERE payment.member_id=:id",['id'=>$id]);
    return $command->queryAll();	
	}
	public function notify($id,$status,$mid){
		$message="";
		//return static::findOne(['phone' => $username,'password'=>$password]);
		$member=Members::findOne(['id'=>$mid]);		
		if($status == "Mid-Level"){
			$message="Hello,".$member->name ." ".$member->lastname.".Your ZAMEA Loan Application has been approved by Loans Committee and awaits decision from Treasurer.";
		}else if($status == "Approved"){
        	$message="Hello,".$member->name ." ".$member->lastname.".Your ZAMEA Loan Application has been approved by the Treasurer.Funds will be transferred to you shortly";			
		}else if($status == "Denied"){
			$message="Hello,".$member->name ." ".$member->lastname.".Your ZAMEA Loan Application has been denied.You can re-apply or contact the loan office.";
		}
$theMessage=urlencode($message);
$thePhone=urlencode($member->mobile_number);
$cSession = curl_init(); 
curl_setopt($cSession,CURLOPT_URL,"http://www.bluedotsms.com/api/mt/SendSMS?user=afrodeb&password=gabby%26kudaPrezha1&senderid=ZAMEA&channel=Normal&DCS=0&flashsms=0&number=".$thePhone."&text=".$theMessage);
curl_setopt($cSession,CURLOPT_RETURNTRANSFER,true);
curl_setopt($cSession,CURLOPT_HEADER, false); 
$result=curl_exec($cSession);
curl_close($cSession);
return $result;		
}
	
	}

