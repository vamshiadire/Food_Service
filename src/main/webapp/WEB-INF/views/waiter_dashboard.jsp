<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Tasty Food - Waiter Dashboard</title>
<meta http-equiv="Cache-Control"
	content="no-store, no-cache, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Tasty Food</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item me-2"><a class="btn btn-info"
					href="${pageContext.request.contextPath}/waiter/viewOrders">View
						Orders</a></li>
				<li class="nav-item"><a class="btn btn-success"
					href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container my-5 pt-5">
		<h4 class="text-center mb-4">Tables Overview</h4>

		<c:if test="${not empty message}">
			<div class="alert alert-success text-center">${message}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger text-center">${error}</div>
		</c:if>

		<div class="row">
			<c:forEach var="table" items="${tables}">
				<div class="col-md-3 mb-4 d-flex justify-content-center">
					<div class="card shadow-sm text-center p-3"
						style="width: 180px; height: 180px; border-radius: 50%; background-image: url('/images/hotel_table.jpg'); background-size: cover; background-position: center; color: white;">
						<div class="card-body d-flex flex-column justify-content-center">
							<h5 class="card-title mb-3">Table ${table.tableNumber}</h5>

							<c:choose>
								<c:when test="${table.status}">
									<c:if
										test="${table.assignedWaiter != null && loggedInUser != null && table.assignedWaiter.id == loggedInUser.id}">
										<form
											action="${pageContext.request.contextPath}/waiter/continueOrder"
											method="post">
											<input type="hidden" name="tableId" value="${table.id}" />
											<button type="submit" class="btn btn-primary btn-sm">Continue
												Order</button>
										</form>
										<form
											action="${pageContext.request.contextPath}/waiter/releaseTable"
											method="post">
											<input type="hidden" name="tableId" value="${table.id}" />
											<button type="submit" class="btn btn-danger btn-sm">Release</button>
										</form>
									</c:if>
									<c:if
										test="${table.assignedWaiter != null && loggedInUser != null && table.assignedWaiter.id != loggedInUser.id}">
										<button class="btn btn-secondary btn-sm" disabled>Booked</button>
									</c:if>
								</c:when>
								<c:otherwise>
									<form
										action="${pageContext.request.contextPath}/waiter/selectTable"
										method="post">
										<input type="hidden" name="tableId" value="${table.id}" />
										<button type="submit" class="btn btn-light btn-sm">Book
											Now</button>
									</form>
								</c:otherwise>
							</c:choose>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>