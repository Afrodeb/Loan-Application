<?php

use yii\helpers\Html;
use yii\widgets\ActiveForm;
use app\models\Members;
use yii\helpers\ArrayHelper;

/* @var $this yii\web\View */
/* @var $model app\models\Payment */
/* @var $form yii\widgets\ActiveForm */
$members=Members::find()->all();
$listData=ArrayHelper::map($members,'id',function($model) {
        return $model['name'].' '.$model['lastname'];
    });

?>

<div class="payment-form">

    <?php $form = ActiveForm::begin(); ?>

    <?php
//	= $form->field($model, 'id')->textInput() 
?>
	<?= $form->field($model, 'member_id')->dropDownList($listData,['prompt'=>'Select Member']); ?>


    <?= $form->field($model, 'amount')->textInput() ?>

    <?php
	//= $form->field($model, 'created')->textInput() 
	?>

    <div class="form-group">
        <?= Html::submitButton($model->isNewRecord ? 'Create' : 'Update', ['class' => $model->isNewRecord ? 'btn btn-success' : 'btn btn-primary']) ?>
    </div>

    <?php ActiveForm::end(); ?>

</div>
