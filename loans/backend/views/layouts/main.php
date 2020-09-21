<?php
/* @var $this \yii\web\View */
/* @var $content string */
use backend\assets\AppAsset;
use yii\helpers\Html;
use yii\helpers\Url;
use yii\bootstrap\Nav;
use yii\bootstrap\NavBar;
use yii\widgets\Breadcrumbs;
use common\widgets\Alert;

AppAsset::register($this);
?>
<!DOCTYPE HTML>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?= Html::csrfMetaTags() ?>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><?= Html::encode($this->title) ?></title>
<meta name="keywords" content="Ultra Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<!--skycons-icons-->
<script src="js/skycons.js"></script>
<!--//skycons-icons-->

 <!-- js-->
<script src="js/jquery-1.11.1.min.js"></script>
 <script src="js/bootstrap.js"></script>
<script src="js/modernizr.custom.js"></script>
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Comfortaa:400,700,300' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Muli:400,300,300italic,400italic' rel='stylesheet' type='text/css'>
<!--//webfonts-->  
<!-- Metis Menu -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
<!--<link rel="stylesheet" href="css/clndr.css" type="text/css" />-->
<script src="js/underscore-min.js" type="text/javascript"></script>
<script src= "js/moment-2.2.1.js" type="text/javascript"></script>
<!--<script src="js/clndr.js" type="text/javascript"></script>-->
<script src="js/site.js" type="text/javascript"></script>
<!--End Calender-->
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class="sidebar" role="navigation">
            <div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right dev-page-sidebar mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar" id="cbp-spmenu-s1">
					<div class="scrollbar scrollbar1">
						<ul class="nav" id="side-menu">
							<li>
								<a href="<?php echo Yii::$app->homeUrl; ?>" class="active"><i class="fa fa-home nav_icon"></i>Dashboard</a>
							</li>
							<li>
								<a href="#"><i class="fa fa-cogs nav_icon"></i>Loan Types <span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li>
										<a href=" <?php echo Url::to(['loan-types/create']); ?>">Add New</a>
									</li>
									<li>
										<a href="<?php echo Url::to(['loan-types/index']); ?>">View</a>
									</li>
								</ul>
								<!-- /nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-home nav_icon"></i>Banks <span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li>
										<a href="<?php echo Url::to(['banks/create']); ?>">Add Bank</a>
									</li>
									<li>
										<a href="<?php echo Url::to(['banks/index']); ?>">View Banks</a>
									</li>
								</ul>
								<!-- /nav-second-level -->
							</li>							
							<li>
								<a href="#"><i class="fa fa-users nav_icon"></i>Members<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li>
										<a href="<?php echo Url::to(['members/create']); ?>">Add New</a>
									</li>
									<li>
									<a href="<?php echo Url::to(['members/index']); ?>">View All</a>
									</li>
								</ul>
								<!-- //nav-second-level -->
							</li>
							<li>
								<a href="#"><i class="fa fa-dollar nav_icon"></i>Loans<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<!--<li>
										<a href="<?php echo Url::to(['loan/create']); ?>">Add New Loan</a>
									</li>-->
									<li>
										<a href="<?php echo Url::to(['loan/']); ?>">View Loans</a>
									</li>
								<li>
										<a href="<?php echo Url::to(['payment/create']); ?>">Payments</a>
									</li>
								
								</ul>
								<!-- //nav-second-level -->
							</li>
							
							<li>
								<a href="#"><i class="fa fa-list-ul nav_icon"></i>Salary Grades<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li>
										<a href="<?php echo Url::to(['salary-grades/create']); ?>">Add New</a>
									</li>
									<li>
										<a href="<?php echo Url::to(['salary-grades/index']); ?>">View All</a>
									</li>
								</ul>
								<!-- //nav-second-level -->
							</li>
							<li>
								<a href="#" class="chart11-nav"><i class="fa fa-users nav_icon"></i>Users<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li>
										<a href="<?php echo Url::to(['users/create']); ?>">Add New</a>
									</li>
									<li>
										<a href="<?php echo Url::to(['users/index']); ?>">View Users</a>
									</li>
																<li>
								<a href="<?php echo Url::to(['site/logout']); ?>">Logout</a>
							</li>

								</ul>
								<!-- //nav-second-level -->
							</li>
						</ul>
					</div>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--logo -->
				<div class="logo">
					<a href="<?php echo Yii::$app->homeUrl; ?>">
					<!--<h1><?php //echo Yii::$app->name; ?></h1>-->
				<img src="images/logo.png" height="60" width="60"></a>
				</div>
				<!--//logo-->
				<div class="user-right">
					<div class="profile_details_left"><!--notifications of menu start -->
						<div class="profile_details">		
							<ul>
								<li class="dropdown profile_details_drop">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
										<!--<div class="profile_img">	
											<span class="prfil-img"><img src="images/a.png" alt=""> </span>
											<div class="clearfix"></div>	
										</div>	-->
									</a>
									<ul class="dropdown-menu drp-mnu">
										<li> <a href="<?=Url::to(['/site/logout']);?>"><i class="fa fa-sign-out"></i> Logout</a> </li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="profile_medile"><!--notifications of menu start -->
				<!--<ul class="nofitications-dropdown">		
					<li class="dropdown head-dpdn">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">3</span></a>
						<ul class="dropdown-menu anti-dropdown-menu">
							<li>
								<div class="notification_header">
									<h3>You have 3 new notification</h3>
								</div>
							</li>
								<li><a href="#">
								<div class="user_img"><img src="images/2.png" alt=""></div>
									<div class="notification_desc">
										<p>Lorem ipsum dolor amet</p>
										<p><span>1 hour ago</span></p>
										</div>
									  <div class="clearfix"></div>	
								</a></li>
								<li class="odd"><a href="#">
									<div class="user_img"><img src="images/1.png" alt=""></div>
									 <div class="notification_desc">
										<p>Lorem ipsum dolor amet </p>
										<p><span>1 hour ago</span></p>
									</div>
									<div class="clearfix"></div>	
								</a></li>
								<li><a href="#">
									<div class="user_img"><img src="images/3.png" alt=""></div>
									 <div class="notification_desc">
										<p>Lorem ipsum dolor amet </p>
										<p><span>1 hour ago</span></p>
									</div>
									 <div class="clearfix"></div>	
								</a></li>
								<li>
									<div class="notification_bottom">
										<a href="#">See all notifications</a>
									</div> 
								</li>
						</ul>
					</li>	
				</ul>-->
			</div>
			<div class="header-right">
					<!--toggle button start-->
					<div class="search-box">
					<form class="input">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</form>
				</div>
				<button id="showLeftPush"><i class="fa fa-bars"></i></button>
				<div class="clearfix"> </div>
				<!--toggle button end-->
			</div>
			<div class="clearfix"> </div>	
		</div>
		<!-- //header-ends -->
		<div id="page-wrapper">
			<div class="main-page">
 <?= Alert::widget() ?>
        <?= $content ?>
			</div>
			</div>
			<div class="copy-section">
		<p>&copy; <?=date("Y")."-".Yii::$app->name; ?>. All rights reserved | Developed by <a href="http://afrodeb.com">AfroDeb</a></p>
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
				<link href="css/bootstrap.min.css" rel="stylesheet">

				<!--//scrolling js-->
				<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
				<div class="modal-dialog" role="document">
					<div class="modal-content modal-info">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
						</div>
						<div class="modal-body">
							<div class="more-grids">
									<h3>New Message </h3>
									<p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae.</p>
									  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button
								
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- //Register -->


</body>
</html>
