<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "loan_types".
 *
 * @property integer $id
 * @property string $name
 * @property double $rate
 * @property string $created
 */
class LoanTypes extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'loan_types';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['name', 'rate'], 'required'],
            [['rate'], 'number'],
            [['created'], 'safe'],
            [['name'], 'string', 'max' => 255],
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
            'rate' => 'Rate',
            'created' => 'Created',
        ];
    }
	public function getLoanIdByName($type){
return LoanTypes::find()->asArray()->where(['name' => $type])->one();	
}
	    public static function getLoanType($id){
        $model = LoanTypes::find()->where(["id" => $id])->one();
        if(!empty($model)){
            return $model->name;
        }

        return null;
    }



}
