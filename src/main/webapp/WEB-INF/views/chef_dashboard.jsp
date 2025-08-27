<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chef Dashboard</title>
    <!-- Using CDN for Bootstrap 5.2.3 for simplicity and modern styling -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"/>
    <!-- Link to your custom CSS file -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/list-users.css"/>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">Food App</a>
    </div>
</nav>
<div class="container">
    <h1>Chef Orders Incomplete</h1>
    <p>Complete the orders </p>
    
    <!-- Right-aligned link -->
    <div class="right-align">
        <!-- Button to add a new user -->
        <a href="${pageContext.request.contextPath}/admin/showFormForAdd" class="btn btn-primary mb-3">Send Message</a>
        <a href="${pageContext.request.contextPath}/admin/bills" class="btn btn-primary mb-3" style="margin-left: 15px;">Help/</a>
    </div>
    
    <!-- User list table -->
    <table class="table table-bordered table-striped table-dark">
        <thead class="table-dark">
            <tr>
                <th>Bill No</th>
                <th>Item</th>
                <th>Waiter Name</th>
				<th>Actions</th>
			
            </tr>
        </thead>
        <tbody>
            <!-- Iterate over the 'chef' list of items by the controller -->
            <c:forEach var="ch" items="${chefs}">
                <tr>
                    <tr>
                    <td>${ch.billNo}</td>
                    <td>${ch.iteam}</td>
                    <td>${ch.waiterName}</td>
                
	                <td>
					    <form action="${pageContext.request.contextPath}/chef/completeOrder" method="post" class="d-inline">
					        <input type="hidden" name="billNo" value="${ch.billNo}">
					        <button type="submit" class="btn btn-info btn-sm">Completed</button>
					    </form>
					    <a href="${pageContext.request.contextPath}/admin/delete?userId=${ch.billNo}" class="btn btn-danger btn-sm" 
					       onclick="return confirm('Are you sure you want to delete this user?');">
					        N/A
					    </a>
					</td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tasty Food - Chef Dashboard</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
	margin-top: 20px;
}

th, td {
	border: 1px solid #aaa;
	padding: 8px;
	text-align: left;
}

th {
	background: #ddd;
}

.btn {
	padding: 6px 12px;
	margin: 2px;
	cursor: pointer;
	border: none;
	border-radius: 3px;
}

.btn-claim {
	background-color: orange !important;
	color: white !important;
}



.btn-update {
	background-color: #2196F3;
	color: white;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
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
	<h1>Chef Dashboard</h1>

	<c:if test="${not empty orders}">
		<table>
			<thead>
				<tr>
					<th>Order ID</th>
					<th>Table Number</th>
					<th>Items</th>
					<th>Status</th>
					<th>Assigned Chef</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="order" items="${orders}">
					<tr>
						<td>${order.id}</td>
						<td>${order.restaurantTable.tableNumber}</td>
						<td>
							<ul>
								<c:forEach var="item" items="${order.orderItems}">
									<li>${item.itemName}(Qty:${item.quantity})</li>
								</c:forEach>
							</ul>
						</td>
						<td>${order.status}</td>
						<td><c:choose>
								<c:when test="${order.assignedChef != null}">
                                    ${order.assignedChef.username}
                                </c:when>
								<c:otherwise>
                                    Not assigned
                                </c:otherwise>
							</c:choose></td>
						<td><c:if test="${order.assignedChef == null}">
								<form method="post"
									action="${pageContext.request.contextPath}/chef/claimOrder">
									<input type="hidden" name="orderId" value="${order.id}" />
									<button type="submit" class="btn btn-claim">Claim
										Order</button>
								</form>
							</c:if> <c:if test="${order.assignedChef != null}">
								<form method="post"
									action="${pageContext.request.contextPath}/chef/updateStatus"
									style="display: inline;">
									<input type="hidden" name="orderId" value="${order.id}" /> <select
										name="status">
										<option value="Pending"
											${order.status == 'Pending' ? 'selected' : ''}>Pending</option>
										<option value="In Progress"
											${order.status == 'In Progress' ? 'selected' : ''}>In
											Progress</option>
										<option value="Completed"
											${order.status == 'Completed' ? 'selected' : ''}>Completed</option>
									</select>
									<button type="submit" class="btn btn-update">Update</button>
								</form>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

	<c:if test="${empty orders}">
		<p>No orders available currently.</p>
	</c:if>

</body>
</html>
