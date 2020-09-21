<?php

/* @var $this yii\web\View */
/* @var $form yii\bootstrap\ActiveForm */
/* @var $model \common\models\LoginForm */

use yii\helpers\Html;
use yii\bootstrap\ActiveForm;

$this->title = 'Login';
$this->params['breadcrumbs'][] = $this->title;
?>
<!DOCTYPE HTML>
<html>
<head>
<title><?= Html::encode($this->title) ?></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Ultra Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link rel="icon" href="favicon.ico" type="image/x-icon" >
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
 <!-- js-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/modernizr.custom.js"></script>
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Comfortaa:400,700,300' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Muli:400,300,300italic,400italic' rel='stylesheet' type='text/css'>
<!--//webfonts--> 
<!-- Metis Menu -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
<!--//Metis Menu -->

</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		<div class="sticky-header header-section ">
			<div class="header-left">
<center>
				<div class="logo">
					<a href="#"><h1><?=Yii::$app->name; ?></h1></a>
				</div>
</center>				
				<div class="clearfix"> </div>
			</div>
	
			
			<div class="clearfix"> </div>	
		</div>
		<!-- //header-ends -->
		<div id="page-wrapper">
			<div class="main-page">
				<div class="login-form">
					<center><h4>Login</h4></center>
            <?php $form = ActiveForm::begin(['id' => 'login-form']); ?>
						<?= $form->field($model, 'username')->textInput(['autofocus' => true,'placeholder'=>'Phone Number']) ?>
                        <?= $form->field($model, 'password')->passwordInput(['class'=>'pass','placeholder'=>'Password']) ?>
                        <span class="check-right"><a href="#">Forgot password?</a></span>
						<div class="clearfix"></div>
						<button class="btn btn-info btn-block" type="submit">Sign in</button>
						<?php ActiveForm::end(); ?>
				</div>
		</div>	
	</div>	
	<!--typo-ends-->
	
			<div class="copy-section">
		<p>&copy; 2016 Ultra Modern. All rights reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
		</div>
	</div>
			<!-- Classie -->
				<script src="js/classie.js"></script>
				<script>
					var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
						showLeftPush = document.getElementById( 'showLeftPush' ),
						body = document.body;
						
					showLeftPush.onclick = function() {
						classie.toggle( this, 'active' );
						classie.toggle( body, 'cbp-spmenu-push-toright' );
						classie.toggle( menuLeft, 'cbp-spmenu-open' );
						disableOther( 'showLeftPush' );
					};
					

					function disableOther( button ) {
						if( button !== 'showLeftPush' ) {
							classie.toggle( showLeftPush, 'disabled' );
						}
					}
				</script>
			<!-- Bootstrap Core JavaScript --> 
				
				<script type="text/javascript" src="js/bootstrap.min.js"></script>
				<!--scrolling js-->
				<script src="js/jquery.nicescroll.js"></script>
				<script src="js/scripts.js"></script>
				<!--//scrolling js-->
</body>
</html>