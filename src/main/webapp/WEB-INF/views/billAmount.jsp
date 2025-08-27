<!-- bills_section.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="container mt-4">
    <hr>
    <h5>Add New Bill</h5>
    <form action="/admin/bills/add" method="post" class="row g-3 align-items-center mb-4">
        <div class="col-md-3">
            <input type="text" name="waiterName" class="form-control" placeholder="Waiter Name" required>
        </div>
        <div class="col-md-4">
            <input type="text" name="item" class="form-control" placeholder="Item" required>
        </div>
        <div class="col-md-2">
            <input type="number" step="0.01" name="amount" class="form-control" placeholder="Amount" required>
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-success">Add Bill</button>
        </div>
    </form>

    <hr>
    <h5>Bill Amounts</h5>
    <c:if test="${filterApplied}">
        <p>Filtered by waiter: <strong>${waiterName}</strong></p>
    </c:if>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Waiter</th>
                <th>Item</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="bill" items="${bills}">
                <tr>
                    <td>${bill.waiterName}</td>
                    <td>${bill.item}</td>
                    <td>${bill.amount}</td>
                </tr>
            </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2"><strong>Total</strong></td>
                <td><strong>${totalAmount}</strong></td>
            </tr>
        </tfoot>
    </table>
</div>
