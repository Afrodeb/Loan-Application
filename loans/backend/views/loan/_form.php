<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use app\models\Members;
use app\models\LoanTypes;
use yii\helpers\ArrayHelper;

/* @var $this yii\web\View */
/* @var $model app\models\Loan */
/* @var $form yii\widgets\ActiveForm */
$members=Members::find()->all();
$listData=ArrayHelper::map($members,'id',function($model) {
        return $model['name'].' '.$model['lastname'];
    });
$loanTypes=LoanTypes::find()->all();	
$loanListData=ArrayHelper::map($loanTypes,'id','name');

?>

<div class="loan-form">

    <?php $form = ActiveForm::begin(); ?>

    <?php 
	//= $form->field($model, 'member_id')->textInput() 
	?>
	<?= $form->field($model, 'member_id')->dropDownList($listData,['prompt'=>'Select Member']); ?>

    <?= $form->field($model, 'amount_required')->textInput(['readonly'=>'readonly']) ?>

    <?= $form->field($model, 'current_loan_amount')->textInput(['readonly'=>'readonly']) ?>

    <?= $form->field($model, 'current_monthly_payment')->textInput(['readonly'=>'readonly']) ?>

    <?= $form->field($model, 'created')->textInput(['readonly'=>'readonly']) ?>

	<?php
	if(Yii::$app->user->identity->level == "Mid-Level"){
	?>	
    <?= $form->field($model, 'status')->dropDownList([ 'Mid-Level' => 'Approved','Denied' => 'Denied', ], ['prompt' => 'Select Status']) ?>
<?php
	}else{
?>
   <?php
   //= $form->field($model, 'status')->dropDownList([ 'Applied' => 'Applied', 'Mid-Level' => 'Mid-Level', 'Senior-Level' => 'Senior-Level', 'Approved' => 'Approved', 'Denied' => 'Denied', ], ['prompt' => ''])
   ?>
   <?= $form->field($model, 'status')->dropDownList(['Approved' => 'Approved', 'Denied' => 'Denied', ], ['prompt' => 'Select Status']) ?>
<?php
	}
?>
   
	<?= $form->field($model, 'loan_type')->dropDownList($loanListData,['prompt'=>'Select Loan Type','readonly'=>'readonly']); ?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
