<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delight Food Hotel</title>
    <!-- Bootstrap CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
.nav-link.active {
    position: relative;
    font-weight: bold;
    color: #198754 !important; /* success green */
}

.nav-link.active::after {
    content: "";
    position: absolute;
    bottom: -5px;
    left: 0;
    width: 100%;
    height: 3px;
    background-color: #198754;
    border-radius: 2px;
}

</style>
    
</head>
<body>

<!-- Header/Navbar -->
<nav class="navbar shadow-sm navbar-expand-lg navbar-dark bg-white text-black fixed-top mb-5">
    <div class="container-fluid mx-4">
        <a class="navbar-brand d-flex align-items-center text-black" href="#" style="font-family: 'Playfair Display', cursive; font-size: 30px; color: #FFD700;">
            <img src="/images/hotel_logo.png" alt="Logo" width="40" height="40" class="me-2 shadow-sm" style="border-radius: 50%">
            Delight Food Hotel
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    <%
    String currentPage = "home"; // change this on each JSP page accordingly
%>
<div class="collapse text-black navbar-collapse justify-content-end" id="navbarNav">
    <ul class="navbar-nav text-black">
        <li class="nav-item">
            <a class="nav-link text-black <%= currentPage.equals("home") ? "active" : "" %>" href="home">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-black <%= currentPage.equals("services") ? "active" : "" %>" href="food_menu">Food_menu</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-black <%= currentPage.equals("about") ? "active" : "" %>" href="about">About Us</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-black <%= currentPage.equals("career") ? "active" : "" %>" href="career">Career</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-black <%= currentPage.equals("contact") ? "active" : "" %>" href="#">Contact Us</a>
        </li>
        <li class="nav-item">
            <a class="btn btn-success btn-sm mt-1" href="login">Login</a>
        </li>
    </ul>
</div>

    </div>
</nav>

<!-- Main Section: Table Booking Cards -->

<div class="container my-5 pt-5">
    <h4 class="text-center mb-4">Book Your Table</h4>
    <div class="row justify-content-center">
        <% for (int i = 1; i <= 9; i++) { %>
            <div class="col-md-4 mb-4 d-flex justify-content-center">
                <div class="card shadow-sm text-center p-3 d-flex flex-column align-items-center justify-content-center"
                     style="width: 200px; height: 200px; border-radius: 50%; overflow: hidden;
                            background-image: url('/images/hotel_table.jpg');
                            background-size: cover;
                            background-position: center;
                            color: white;">


                    <div class="card-body p-2">
                        <h5 class="card-title mt-5">Table <%= i %></h5>
                        <a href="food_menu?tabelId=<%=i %>" class="btn btn-light btn-sm mt-1">Book Now</a>
                    </div>
                </div>
            </div>
        <% } %>
    </div>
</div>



<!-- Services / Features Section -->
<div class="bg-light py-5" id="services">
    <div class="container text-center">
        <h2 class="mb-4">Our Services</h2>
        <div class="row">
          
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Online Food Order</h5>
                        <p class="card-text">Order your favorite dishes online and get them delivered at your doorstep.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mb-4">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title">Party Hall Booking</h5>
                        <p class="card-text">Spacious and elegant halls for birthdays, meetings, and special events.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-3 mt-4">
    &copy; 2025 Delight Food Hotel. All Rights Reserved.
</footer>

<!-- Bootstrap JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
