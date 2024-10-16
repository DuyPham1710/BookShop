<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
 <script src="script.js"></script>
<style>
	body {
        background-color: #f8f9fa;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
	.container {
	 	width: 400px;
	 	padding: 10px;
        background-color: white;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
        border-radius: 10px;
	}
</style>
</head>
<body>
	<form action="/BookShop/loginController" method="post" onsubmit="return check_Login()">
		<div class="container shadow p-4">
		
			<h1><center>Login</center></h1>
			
			<div class="input-group input-group-sm mb-3">
			  <span class="input-group-text" id="inputGroup-sizing-sm">Username</span>
			  <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="username">
			</div>
			
			<div class="input-group input-group-sm mb-3">
			  <span class="input-group-text" id="inputGroup-sizing-sm">Password</span>
			  <input type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="password">
			</div>
			
			<div class="d-grid">
				<input type="hidden" name="method" value="get">
			  <button type="submit" class="btn btn-primary btn-block">Login</button>
			</div>	
		</div>
	</form>
	
</body>
</html>