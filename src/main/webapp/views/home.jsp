<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
	body {
        background-color: #f8f9fa;
        height: 100vh;
        display: flex;
        justify-content: center;
    }
    .container {
    	width: 1000px;
    }
</style>
</head>
<body>
	<div class="container">
		
		  <nav class="navbar navbar-expand-lg navbar-light bg-light">
	        <div class="container">
	            <a class="navbar-brand" href="#">Cửa Hàng Sách</a>
	            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse" id="navbarNav">
	                <ul class="navbar-nav me-auto">
	                    <li class="nav-item">
	                        <a class="nav-link active" aria-current="page" href="#">Trang Chủ</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="#">Sách Mới</a>
	                    </li>
	                    <li class="nav-item">
	                        <a class="nav-link" href="#">Giảm Giá</a>
	                    </li>
	                </ul>
	                <a class="btn btn-outline-primary" href="cartController">
	               		Giỏ Hàng <span class="badge bg-secondary">${totalItems}</span>
	                </a>
	            </div>
	        </div>
	    </nav>
	    <table class="table">
	        <thead class="table-light text-center">
	            <tr>
	                <th>Code</th>
	                <th>Mô tả</th>
	                <th>Giá</th>
	                <th>Chức năng</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:forEach var="book" items="${allBooks}">
	        		<tr class="text-center">
		        		<form action="productController" method="post">
		                	<td>${book.code}</td>
			                <td>${book.description}</td>
			                <td>${book.cost}</td>
			                
			                <input type="hidden" name="code" value="${book.code}">
			              	<input type="hidden" name="method" value="post">
			                <td><button class="btn btn-primary">Add to cart</button></td>
			             </form>
		            </tr>
		           
	        	</c:forEach> 
	        </tbody>
	    </table>
	</div>
	
</body>
</html>