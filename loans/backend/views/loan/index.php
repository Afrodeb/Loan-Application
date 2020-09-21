<?php

use yii\helpers\Html;
use yii\grid\GridView;
use app\models\Members;
use app\models\LoanTypes;
/* @var $this yii\web\View */
/* @var $dataProvider yii\data\ActiveDataProvider */

$this->title = 'Loans';
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="loan-index">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?php
		//= Html::a('Create Loan', ['create'], ['class' => 'btn btn-success']) 
		?>
    </p>
    <?php /*= GridView::widget([
        'dataProvider' => $dataProvider,
        'columns' => [
            ['class' => 'yii\grid\SerialColumn'],

            'id',
            'member_id',
            'amount_required',
            'current_loan_amount',
            'current_monthly_payment',
            // 'created',
            // 'status',
            // 'loan_type',

            ['class' => 'yii\grid\ActionColumn'],
        ],
    ]); */
	?>
	<?=GridView::widget([
    'dataProvider' => $dataProvider,    
    'columns' => [
        ['class' => 'yii\grid\SerialColumn'],
		'id',
        [
            'label' => 'Member',
            'value' => function($data){
                return Members::getMember($data->member_id);
            },
        ],
		 'amount_required',
            'current_loan_amount',
            'current_monthly_payment',
			[
            'label' => 'Loan Type',
            'value' => function($data){
                return LoanTypes::getLoanType($data->loan_type);
            },
        ],
        
        ['class' => 'yii\grid\ActionColumn', 'template' => '{view} {update} {delete} '],
    ],
]);
?>
</div>
