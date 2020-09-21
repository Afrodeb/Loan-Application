<?php

namespace app\models;

use Yii;

/**
 * This is the model class for table "salary_grades".
 *
 * @property integer $id
 * @property string $name
 * @property string $created
 */
class SalaryGrades extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'salary_grades';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['name'], 'required'],
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
            'created' => 'Created',
        ];
    }
}
