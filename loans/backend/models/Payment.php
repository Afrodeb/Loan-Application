<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "payment".
 *
 * @property integer $id
 * @property integer $member_id
 * @property double $amount
 * @property string $created
 */
class Payment extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'payment';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['member_id', 'amount'], 'required'],
            [['id', 'member_id'], 'integer'],
            [['amount'], 'number'],
            [['created'], 'safe'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'member_id' => 'Member ID',
            'amount' => 'Amount',
            'created' => 'Created',
        ];
    }
	
	public function getCurrentPayment($member){
		$connection = Yii::$app->getDb();
        $command = $connection->createCommand("SELECT SUM(amount) AS p FROM payment WHERE member_id=:mid",['mid'=>$member]);
        return $command->queryAll();
	}	
	public function getAveragePayment($member){
		$connection = Yii::$app->getDb();
        $command = $connection->createCommand("SELECT AVG(amount) AS p FROM payment WHERE member_id=:mid",['mid'=>$member]);
        return $command->queryAll();
	}
}
