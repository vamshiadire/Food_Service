<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body>

    <!-- Include Navbar -->
    <jsp:include page="navbar.jsp" />

    <!-- Include Sidebar -->
    <jsp:include page="sidebar.jsp" />

    <!-- Page Content -->
    <div class="container mt-4">
        <h1>Welcome, ${admin.username}!</h1>

        <!-- Display messages -->
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center">${message}</div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success text-center">${successMessage}</div>
        </c:if>

        <!-- Home Section -->
        <c:choose>
            <c:when test="${section == 'home'}">
                <hr>
                <h5>Generate Token for New User</h5>
                <form action="/admin/generate-token" method="post" class="row g-3 align-items-center mb-4">
                    <div class="col-auto">
                        <label for="role" class="form-label">Select Role:</label>
                    </div>
                    <div class="col-auto">
                        <select name="role" id="role" class="form-select" required>
                            <option value="WAITER">Waiter</option>
                            <option value="CHEF">Chef</option>
                            <option value="ADMIN">Admin</option>
                        </select>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">Generate Token</button>
                    </div>
                </form>

                <c:if test="${not empty generatedToken}">
                    <div class="alert alert-success text-center">
                        Generated Token for <strong>${role}</strong>: <code>${generatedToken}</code>
                    </div>
                </c:if>

                <hr>
                <h5>Add New Table</h5>
                <form action="/admin/addTable" method="post" class="row g-3 align-items-center mb-4">
                    <div class="col-auto">
                        <input type="number" name="tableNumber" class="form-control" placeholder="Enter Table Number" required>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-success">Add Table</button>
                    </div>
                </form>

                <h5 class="mt-4">Current Tables</h5>
                <div class="row">
                    <c:forEach var="table" items="${tables}">
                        <div class="col-md-3 mb-3">
                            <div class="card shadow-sm text-center p-3">
                                <h5>Table ${table.tableNumber}</h5>
                                <p>Status:
                                    <c:choose>
                                        <c:when test="${table.status}">Booked</c:when>
                                        <c:otherwise>Available</c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:when>
        </c:choose>

        <!-- Bills Section -->
        <c:choose>
            <c:when test="${section == 'bills'}">
                <jsp:include page="billAmount.jsp" />
            </c:when>
        </c:choose>

        <!-- Employee List Section -->
        <c:choose>
            <c:when test="${section == 'list'}">
                <jsp:include page="list_users.jsp" />
            </c:when>
        </c:choose>

        <!-- Add Menu Section -->
        <c:choose>
            <c:when test="${section == 'addMenu'}">
                <jsp:include page="Menu_Item.jsp" />
            </c:when>
        </c:choose>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
