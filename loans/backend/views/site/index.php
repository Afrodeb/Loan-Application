<?php
/* @var $this yii\web\View */
use yii\helpers\Url;
$this->title = 'Dashboard';
?>
				<div class="four-grids">
					<div class="col-md-3 four-grid">
						<div class="four-grid1">
							<div class="icon">
								<i class="glyphicon glyphicon-user" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>System Users</h3>
								<h4> <?=$users;?> </h4>								
							</div>
							<a href="<?=Url::to(['/users/']);?>">More Info</a>
						</div>
					</div>
					<div class="col-md-3 four-grid">
						<div class="four-grid2">
							<div class="icon">
								<i class="glyphicon glyphicon-align-justify " aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>Loans Taken</h3>
								<h4><?=$loans; ?></h4>
							</div>
							<a href="<?=Url::to(['/loan/']);?>">More Info</a>
						</div>
					</div>
					<div class="col-md-3 four-grid">
						<div class="four-grid3">
							<div class="icon">
								<i class="glyphicon glyphicon-user" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>Scheme Members</h3>
								<h4><?=$members;?></h4>								
							</div>
							<a href="<?=Url::to(['/members/']);?>">More Info</a>
						</div>
					</div>
					<div class="col-md-3 four-grid">
						<div class="four-grid4">
							<div class="icon">
								<i class="glyphicon glyphicon-usd" aria-hidden="true"></i>
							</div>
							<div class="four-text">
								<h3>Supported Banks</h3>
								<h4><?=$banks;?></h4>								
							</div>
							<a href="<?=Url::to(['/banks/']);?>">More Info</a>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>