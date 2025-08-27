<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food Menu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.card {
	margin-bottom: 20px;
}

.card-img-top {
	height: 200px;
	object-fit: cover;
}

.btn-add-cart {
	background-color: #198754;
	color: white;
}

.cart-summary {
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Tasty Food</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="btn btn-success"
					href="${pageContext.request.contextPath}/logout">Logout</a></li>
			</ul>
		</div>
	</nav>

	<div class="container mt-5">

		<h2 class="text-center mb-4">Food Menu for Table
			${table.tableNumber}</h2>

		<div class="row">
			<!-- Food Items -->
			<div class="col-md-8">
				<div class="row">
					<c:forEach var="item" items="${menuItems}">
						<div class="col-md-4">
							<div class="card">
								<img src="${item.itemUrl}" class="card-img-top"
									alt="${item.itemName}">
								<div class="card-body">
									<h5 class="card-title">${item.itemName}</h5>
									<p class="card-text">${item.description}</p>
									<p class="card-text fw-bold">$${item.price}</p>
									<a
										href="${pageContext.request.contextPath}/waiter/addToCart?id=${item.id}&tableId=${table.tableNumber}"
										class="btn btn-add-cart w-100">Add to Cart</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<!-- Cart Summary -->
			<div class="col-md-4">
				<div class="cart-summary">
					<h5>Cart</h5>
					<c:if test="${not empty cart}">
						<ul class="list-group mb-3">
							<c:set var="total" value="0" />
							<c:forEach var="item" items="${cart}">
								<li
									class="list-group-item d-flex justify-content-between align-items-center">
									<div>${item.itemName} x ${item.quantity}</div>
									<div>
										$
										<c:out value="${item.price * item.quantity}" />
									</div>
								</li>
								<c:set var="total"
									value="${total + (item.price * item.quantity)}" />
							</c:forEach>
							<li class="list-group-item d-flex justify-content-between">
								<strong>Total:</strong> <strong>$<c:out
										value="${total}" /></strong>
							</li>
						</ul>

						<!-- Place Order form -->
						<form
							action="${pageContext.request.contextPath}/waiter/placeOrder"
							method="post">
							<input type="hidden" name="tableId" value="${table.tableNumber}" />

							<!-- Hidden input for cart items -->
							<c:forEach var="item" items="${cart}">
								<input type="hidden"
									name="cartItems[${item.menuItemId}].menuItemId"
									value="${item.menuItemId}" />
								<input type="hidden"
									name="cartItems[${item.menuItemId}].itemName"
									value="${item.itemName}" />
								<input type="hidden" name="cartItems[${item.menuItemId}].price"
									value="${item.price}" />
								<input type="hidden"
									name="cartItems[${item.menuItemId}].quantity"
									value="${item.quantity}" />
							</c:forEach>


							<button type="submit" class="btn btn-success w-100">Place
								Order</button>
						</form>
					</c:if>
					<c:if test="${empty cart}">
						<p>Your cart is empty.</p>
					</c:if>

					<!-- Button to book another table -->
					<form action="${pageContext.request.contextPath}/waiter"
						method="get">
						<input type="hidden" name="tableId" value="${table.id}" />
						<button type="submit" class="btn btn-light btn-sm">Book
							Another Table</button>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
