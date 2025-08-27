<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Order Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            margin: 30px auto;
            width: 80%;
        }
        h2 {
            margin-bottom: 20px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            padding: 10px;
            border: 1px solid #aaa;
            text-align: left;
        }
        th {
            background-color: #eee;
        }
        .btn {
            padding: 6px 12px;
            margin-top: 10px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #1976D2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Order Details - ID: ${order.id}</h2>

        <p><strong>Table Number:</strong> ${order.restaurantTable.tableNumber}</p>
        <p><strong>Status:</strong> ${order.status}</p>
        <p><strong>Assigned Chef:</strong>
            <c:choose>
                <c:when test="${order.assignedChef != null}">
                    ${order.assignedChef.username}
                </c:when>
                <c:otherwise>
                    Not assigned
                </c:otherwise>
            </c:choose>
        </p>

        <h3>Ordered Items</h3>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${order.orderItems}">
                    <tr>
                        <td>${item.itemName}</td>
                        <td>${item.quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h3>Update Status</h3>
        <form method="post" action="${pageContext.request.contextPath}/chef/updateStatus">
            <input type="hidden" name="orderId" value="${order.id}" />
            <select name="status">
                <option value="PENDING" ${order.status == 'PENDING' ? 'selected' : ''}>Pending</option>
                <option value="IN_PROGRESS" ${order.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>
                <option value="COMPLETED" ${order.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                <option value="CANCELLED" ${order.status == 'CANCELLED' ? 'selected' : ''}>Cancelled</option>
            </select>
            <button type="submit" class="btn">Update</button>
        </form>

        <br>
        <a href="${pageContext.request.contextPath}/chef" style="text-decoration:none; color:#2196F3;">&#8592; Back to Dashboard</a>
    </div>
</body>
</html>
