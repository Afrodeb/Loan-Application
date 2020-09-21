<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "members".
 *
 * @property integer $id
 * @property string $name
 * @property string $lastname
 * @property integer $bank_id
 * @property string $bank_account
 * @property string $savings_club_number
 * @property string $job_title
 * @property integer $grade_id
 * @property double $annual_salary
 * @property double $monthly_net_salary
 * @property string $mobile_number
 * @property string $address
 * @property string $created
 */
class Members extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'members';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['name', 'lastname', 'bank_id', 'bank_account', 'savings_club_number', 'job_title', 'grade_id', 'annual_salary', 'monthly_net_salary', 'mobile_number', 'address'], 'required'],
            [['bank_id', 'grade_id'], 'integer'],
            [['annual_salary', 'monthly_net_salary'], 'number'],
            [['address'], 'string'],
            [['created'], 'safe'],
            [['name', 'lastname', 'bank_account', 'savings_club_number', 'job_title', 'mobile_number'], 'string', 'max' => 255],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'name' => 'Name',
            'lastname' => 'Last name',
            'bank_id' => 'Bank',
            'bank_account' => 'Bank Account',
            'savings_club_number' => 'Savings Club Number',
            'job_title' => 'Job Title',
            'grade_id' => 'Salary Grade',
            'annual_salary' => 'Annual Salary',
            'monthly_net_salary' => 'Monthly Net Salary',
            'mobile_number' => 'Mobile Number',
            'address' => 'Address',
            'created' => 'Created',
        ];
    }
	
public function loginMember($phone,$id){
return Members::find()->asArray()->where(['mobile_number' => $phone,'savings_club_number' => $id])->one();	
}


    public static function getMember($id){
        $model = Members::find()->where(["id" => $id])->one();
        if(!empty($model)){
            return $model->name." ".$model->lastname;
        }

        return null;
    }    

	public function generateMemberNumber(){
		return "ZNA-".substr(time(),0,12);
	}
	
	}
