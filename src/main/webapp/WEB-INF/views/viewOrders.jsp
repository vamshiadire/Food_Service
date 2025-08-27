<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Completed Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
	
    <div class="container mt-5">
        <h3 class="text-center mb-4">Orders</h3>

        <c:if test="${empty orders}">
            <div class="alert alert-info text-center">No orders found.</div>
        </c:if>

        <c:if test="${not empty orders}">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Order ID</th>
                        <th>Table</th>
                        <th>Date & Time</th>
                        <th>Items</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.restaurantTable.tableNumber}</td>
                            <td>${order.localDateTime}</td>
                            <td>
                                <ul>
                                    <c:forEach var="item" items="${order.orderItems}">
                                        <li>${item.itemName}</li>
                                    </c:forEach>
                                </ul>
                            </td>
                            <td>${order.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
