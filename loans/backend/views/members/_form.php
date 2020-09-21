<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use yii\helpers\ArrayHelper;
use app\models\Banks;
use app\models\SalaryGrades;
use app\models\Members;


/* @var $this yii\web\View */
/* @var $model app\models\Members */
/* @var $form yii\widgets\ActiveForm */
$banks=Banks::find()->all();	
$bankListData=ArrayHelper::map($banks,'id','name');

$salaryGrades=SalaryGrades::find()->all();	
$salaryGradesListData=ArrayHelper::map($salaryGrades,'id','name');
$members=new Members;
$member_id=$members->generateMemberNumber();
?>

<div class="members-form">

    <?php $form = ActiveForm::begin(); ?>

    <?= $form->field($model, 'name')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'lastname')->textInput(['maxlength' => true]) ?>

	<?= $form->field($model, 'bank_id')->dropDownList($bankListData,['prompt'=>'Select Bank']); ?>

    <?= $form->field($model, 'bank_account')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'savings_club_number')->textInput(['maxlength' => true,'value'=>$member_id,'readonly'=>'readonly']) ?>

    <?= $form->field($model, 'job_title')->textInput(['maxlength' => true]) ?>

	<?= $form->field($model, 'grade_id')->dropDownList($salaryGradesListData,['prompt'=>'Select Salary Grade']); ?>

    <?= $form->field($model, 'annual_salary')->textInput() ?>

    <?= $form->field($model, 'monthly_net_salary')->textInput() ?>

    <?= $form->field($model, 'mobile_number')->textInput(['maxlength' => true]) ?>

    <?= $form->field($model, 'address')->textarea(['rows' => 6]) ?>

    
    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
