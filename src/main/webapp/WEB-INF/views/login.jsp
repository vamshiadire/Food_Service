<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Food App</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f7f7f7;
	text-align: center;
	margin: 0;
	padding: 0;
}

.login-container {
	background-color: #fff;
	max-width: 400px;
	margin: 50px auto;
	padding: 30px;
	box-shadow: 0px 0px 10px #ccc;
	border-radius: 8px;
}

.logo {
	width: 120px;
	height: auto;
	margin-bottom: 20px;
}

input, select {
	width: 90%;
	padding: 10px;
	margin: 10px 0;
	border-radius: 5px;
	border: 1px solid #ccc;
}

button {
	width: 45%;
	padding: 10px;
	margin: 10px 5px;
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 5px;
}

.forgot-link {
	display: block;
	margin-top: 15px;
}

.error {
	color: red;
}
</style>
</head>
<body>

	<div class="login-container">
		<img src="/images/logo.png" class="logo" />

		<h2>Login</h2>

		<c:if test="${not empty message}">
			<div style="color: red; font-weight: bold;">${message}</div>
		</c:if>

		<form action="/login" method="post">
			<input type="email" name="email" placeholder="email" required /> <input
				type="password" name="password" placeholder="Password" required />
			<select name="role" required>
				<option value="">Select Role</option>
				<option value="ADMIN">ADMIN</option>
				<option value="WAITER">WAITER</option>
				<option value="CHEF">CHEF</option>
			</select> <br />

			<button type="submit">Login</button>
			<button type="button" onclick="window.location='/register'">Register</button>

			<a href="/forgot-password" class="forgot-link">Forgot Password?</a>
		</form>
	</div>

</body>
</html>
