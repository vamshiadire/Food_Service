<!-- add_menu.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container mt-4">
	<hr>
	<h5>Add Item</h5>
	<form action="/admin/addMenu" method="post">
		<div class="mb-3">
			<label for="itemName" class="form-label">Item Name</label> <input
				type="text" id="itemName" name="itemName" class="form-control"
				required />
		</div>
		<div class="mb-3">
			<label for="description" class="form-label">Description</label> <input
				type="text" id="description" name="description" class="form-control"
				required />
		</div>
		<div class="mb-3">
			<label for="price" class="form-label">Price</label> <input
				type="number" id="price" name="price" class="form-control" required />
		</div>
		<div class="mb-3">
			<label for="itemUrl" class="form-label">Item URL</label> <input
				type="url" id="itemUrl" name="itemUrl" class="form-control" required />
		</div>
		<div class="mb-3">
			<input type="checkbox" id="available" name="available" class="form-check-input" value="true" checked />
		</div>
		
		<button type="submit" class="btn btn-success">Add Item</button>
	</form>
</div>
