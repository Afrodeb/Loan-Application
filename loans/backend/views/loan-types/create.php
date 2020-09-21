<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\LoanTypes */

$this->title = 'Create Loan Types';
$this->params['breadcrumbs'][] = ['label' => 'Loan Types', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="loan-types-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
