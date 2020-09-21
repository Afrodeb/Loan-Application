<?php

use yii\helpers\Html;
use yii\grid\GridView;

/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Members';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="members-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Create Members', ['create'], ['class' => 'btn btn-success']) ?>
    </p>
    <?= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'name',
            'lastname',
            'bank_id',
			'mobile_number',
            'bank_account',
            'savings_club_number',
            // 'job_title',
            // 'grade_id',
            // 'annual_salary',
            // 'monthly_net_salary',
             'mobile_number',
            // 'address:ntext',
            // 'created',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); ?>
</div>
