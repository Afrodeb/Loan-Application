<?php

namespace backend\controllers;

use Yii;
use app\models\Loan;
use app\models\LoanTypes;
use app\models\Payment;
use yii\data\ActiveDataProvider;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;

/**
 * LoanController implements the CRUD actions for Loan model.
 */
class LoanController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],
        ];
    }

    /**
     * Lists all Loan models.
     * @return mixed
     */
    public function actionIndex()
    {
		$loans=null;
		if(Yii::$app->user->identity->level == "Mid-Level"){
		    $loans=Loan::find()->where(['status'=>'Applied']);
		}else if(Yii::$app->user->identity->level == "Senior-Level"){
			$loans=Loan::find()->where(['status'=>'Mid-Level']);
		}else{ //if(Yii::$app->user->identity->level == "Supervisor"){
			$loans=Loan::find()->where(['status'=>'Approved']);
		}

        $dataProvider = new ActiveDataProvider([
            'query' => $loans,
        ]);

        return $this->render('index', [
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Loan model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new Loan model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Loan();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Loan model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
		$loans=new Loan;
        $model = $this->findModel($id);
        if ($model->load(Yii::$app->request->post()) && $model->save()) {            			
			$loans->notify($model->id,$model->status,$model->member_id);
			return $this->redirect(['index']);			
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Loan model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Loan model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return Loan the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Loan::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
	
public function actionApply(){
//    [['member_id', 'amount_required', 'current_loan_amount', 'current_monthly_payment', 'status', 'loan_type'], 'required'],        	
$loanTypes=new LoanTypes;	
$loan=new Loan;
$payments=new Payment;
$id=$_REQUEST['id'];
$amount=$_REQUEST['amount'];
$type=$_REQUEST['type'];
$loanSums=$loan->getCurrentLoanAmount($id);
$loanSum=$loanSums[0]["p"];
if($loanSum == ""){
	$loanSum=0;
}
$ltypes=$loanTypes->getLoanIdByName($type);
$lid=$ltypes["id"];
$mypayments=$payments->getCurrentPayment($id);
$averagePayments=$payments->getAveragePayment($id);
$currentPayments=$mypayments[0]["p"];
$averagePayment=$averagePayments[0]["p"];
if($currentPayments == ""){
	$currentPayments=0;
}
if($averagePayment == ""){
	$averagePayment=0;
}
$current=$loanSum-$currentPayments;
$loan->member_id=$id;
$loan->amount_required=$amount;
$loan->current_loan_amount=$current;
$loan->current_monthly_payment=$averagePayment;
$loan->status="Applied";
$loan->loan_type=$lid;
if($loan->save(false)){
$arr["result"]["message"]="Loan application successful";
print_r(json_encode($arr));	
}else{
$arr["result"]["message"]="Loan application failed,please contact the administrator if this continues.";
print_r(json_encode($arr));
}
}
	public function actionAll(){
		$loan=new Loan;
		$id=$_REQUEST["id"];
		$result["result"]=$loan->getAll($id);//Loan::find()->asArray()->where(['member_id' => $id])->all();
		print_r(json_encode($result));
	}

	public function actionDashboard(){
		$loan=new Loan;
		$id=$_REQUEST["id"];
		$getTotalAccepted=$loan->getTotalAccepted($id);
		$getUserAccepted=$loan->getUserAccepted($id);
		$getAveragePayment=$loan->getAveragePayment($id);
		$result["result"]["current_total"]=$getTotalAccepted[0]["current_total"];
		$result["result"]["status"]=$getUserAccepted[0]["status"];
		$result["result"]["total_payments"]=$getAveragePayment[0]["total_payments"];
		$result["result"]["average"]=$getAveragePayment[0]["average"];
	print_r(json_encode($result));
}
}