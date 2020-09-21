<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model app\models\SalaryGrades */

$this->title = 'Create Salary Grades';
$this->params['breadcrumbs'][] = ['label' => 'Salary Grades', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="salary-grades-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
