<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<style>
		.bg{
			margin-top: 80px;
			height: 500px;
			width: 100%;
			background: linear-gradient(to bottom right,#6fb2b7 0,#6dd8b9 100%);
			overflow: hidden;
		}


		.z1,.z2,.z3,.z4,.z5,.z6,.z7,.z8{
			margin: 500px 50px 0px;
			float: left;
			background-color: #eee;
			opacity: 0.2;

		}

		.z1{
			height: 30px;
			width: 30px;
			animation: paly 8s linear infinite;
		}

		.z2{
			height: 50px;
			width: 50px;
			animation: paly 9s linear 5s infinite;
		}
		.z3{
			height: 70px;
			width: 70px;
			animation: paly 6s linear 3s infinite;
		}
		.z4{
			height: 90px;
			width: 90px;
			animation: paly 7s linear infinite;
		}
		.z5{
			height: 150px;
			width: 150px;
			animation: paly 15s linear 2s infinite;
		}
		.z6{
			height: 80px;
			width: 80px;
			animation: paly 10s linear infinite;
		}
		.z7{
			height: 30px;
			width: 30px;
			animation: paly 5s linear 9s infinite;
		}
		.z8{
			height: 60px;
			width: 60px;
			animation: paly 8s linear 1s infinite;
		}

		@Keyframes paly{
			from{
				transform: translate(0px, 0px) rotate(0deg)
			}
			to{
				transform: translate(0px, -1500px) rotate(-360deg)
			}
		}

		form{
			width: 250px;
			margin: 120px auto;
			text-align: center;
		}
		form h1{
			color: white;
		}


		.tex{
			width: 98%;
			height: 45px;
			margin-top: 10px;
			text-align: center;
			font-size: 15px;
			font-weight: bold;
			color: white;
			background-color: rgba(255, 255, 255, 0.1);
			border-style: none;
			border: 1px solid #eee;
			border-radius: 5px;
			outline: none;
		}
		.tex:focus{
			background-color: rgba(255, 255, 255, 0.3);
		}

		.but{
			text-align: center;
			font-size: 15px;
			font-weight: bold;
			color:#8addc8;
			margin: 10px auto;
			width: 100%;
			height: 45px;
			border-style: none;
			border-radius: 5px;
			outline: none;
			background-color: rgba(255, 255, 255, 0.9);
		}


	</style>
</head>
<body>
	<div class="bg">
		<form action="">
			<h1>WelCome</h1>
			<input class ="tex" type="text" placeholder="UserName">
			<input class ="tex" type="text" placeholder="PassWord">
			<input class ="but" type="button" value="Login">
		</form>
			

	
		<div class="z1"></div>
		<div class="z2"></div>
		<div class="z3"></div>
		<div class="z4"></div>
		<div class="z5"></div>
		<div class="z6"></div>
		<div class="z7"></div>
		<div class="z8"></div>
	</div>
	
</body>
</html>