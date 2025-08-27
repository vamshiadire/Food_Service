<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - Food App</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f7f7f7;
	text-align: center;
	margin: 0;
	padding: 0;
}

.register-container {
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

.error {
	color: red;
}

.login-link {
	display: block;
	margin-top: 15px;
}

<
link
	href ="https: //cdn.jsdelivr.net /npm/bootstrap@5.3.0
	/dist/css/bootstrap.min.css "
	rel ="stylesheet"> <style>.required::after {
	content: " *";
	color: red;
}
</style>
</head>
<body>
	<div class="register-container">
		<img src="/images/logo.png" class="logo" />

		<h2>Register</h2>

		<c:if test="${not empty message}">
			<div style="color: red; font-weight: bold;">${message}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/register"
			method="post">
			<input type="text" name="username" placeholder="Username" required />
			<input type="email" name="email" placeholder="Email" required /> <input
				type="text" name="mobileNumber" placeholder="Mobile Number" required />
			<input type="password" name="password" placeholder="Password"
				required /> <select name="role" required>
				<option value="">Select Role</option>
				<option value="ADMIN">ADMIN</option>
				<option value="WAITER">WAITER</option>
				<option value="CHEF">CHEF</option>
			</select> <br />

			<!-- Token Input Field -->
			<input type="text" name="registrationToken"
				placeholder="Enter Registration Token (if provided)" required />

			<button type="submit">Register</button>
			<a href="/"><button type="button">Login</button></a> <a
				href="/forgot-password" class="forgot-link">Forgot Password?</a>
		</form>
	</div>

	<div
		class="container d-flex align-items-center justify-content-center min-vh-100">
		<div class="card shadow-lg p-4 w-100 rounded-5"
			style="max-width: 500px;">
			<div class="text-center mb-3">
				<img src="/images/logo.png" alt="Food App Logo" class="img-fluid"
					style="max-width: 100px;">
			</div>
			<h3 class="text-center mb-5">Registration Form</h3>


			<c:if test="${not empty error}">
				<div class="alert alert-danger text-center">${error}</div>
			</c:if>

			<form action="/register" method="post">
				<div class="row mb-3 align-items-center">
					<label for="username" class="col-sm-3 col-form-label required">Username</label>
					<div class="col-sm-8">
						<input type="text" id="username" name="username"
							class="form-control" required>
					</div>
				</div>

				<div class="row mb-3 align-items-center">
					<label for="email" class="col-sm-3 col-form-label required">Email</label>
					<div class="col-sm-8">
						<input type="email" id="email" name="email" class="form-control"
							required>
					</div>
				</div>

				<div class="row mb-3 align-items-center">
					<label for="mobileNumber" class="col-sm-3 col-form-label required">Number</label>
					<div class="col-sm-8">
						<input type="text" id="mobileNumber" name="mobileNumber"
							class="form-control" required>
					</div>
				</div>


				<div class="row mb-3 align-items-center">
					<label for="password" class="col-sm-3 col-form-label required">Password</label>
					<div class="col-sm-8">
						<input type="password" id="password" name="password"
							class="form-control" required>
					</div>
				</div>

				<div class="row mb-4 align-items-center">
					<label for="role" class="col-sm-3 col-form-label required">Role</label>
					<div class="col-sm-8">
						<select id="role" name="role" class="form-select" required>
							<option value="">Select Role</option>
							<option value="WAITER">WAITER</option>
							<option value="CHEF">CHEF</option>
						</select>
					</div>
				</div>

				<div class="d-grid gap-2 d-md-flex justify-content-md-between">
					<button type="submit"
						class="btn btn-success col-12 col-md-5 m-auto">Register</button>

				</div>
				<div class="d d-md-flex justify-content-md-center mt-2">
					<p>
						Already have an account ? <a href="login"
							class=" col-12 col-md-5 text-decoration-none">Login </a>
					</p>
				</div>
			</form>

		</div>
	</div>

	<!-- Bootstrap Bundle JS (includes Popper) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	======= >>>>>>> ed1e66ab18b6fe9b8efacb933c474388676d3107
</body>
</html>
