<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script>
	function updateTotal(cost, code) {
	    var quantity = document.getElementById('quantity-' + code).value;
	    var newTotal = cost * quantity;
	    document.getElementById('total-' + code).innerText = newTotal;
	} 
	
	function confirmUpdate() {
	    alert("Cập nhật thành công!");
	    return true;
	}
</script>
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
		<h1><center>Giỏ hàng</center></h1>
	    <table class="table">
	        <thead class="table-light text-center">
	            <tr>
	                <th>Code</th>
	                <th>Mô tả</th>
	                <th>Số lượng</th>
	                <th>Đơn giá</th>
	                <th>Tổng tiền</th>
	                <th>Chức năng</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:forEach var="cart" items="${carts}">
	        		<tr class="text-center">      			
					   	<td>${cart.sp.code}</td>
		                <td>${cart.sp.description}</td>
		                
			             <form action="cartController" method="post" onsubmit="return confirmUpdate()">
			                <td>
								 <input type="hidden" name="method" value="put">
								
								 <div class="input-group">
							        <input type="number" class="form-control text-center" name="quantity" id="quantity-${cart.sp.code}" value="${cart.count}" min="1" onchange="updateTotal(${cart.sp.cost}, ${cart.sp.code})">
							     	 <input type="hidden" name="code" value="${cart.sp.code}">
							     	<button class="btn btn-primary">Update</button>
							     </div>
							</td>
					  	</form>
					  	
		                <td>${cart.sp.cost}</td>
		                <td id="total-${cart.sp.code}">${cart.count * cart.sp.cost}</td>
		              
			            <form action="cartController" method="post">
			            	<input type="hidden" name="method" value="post">
			                <input type="hidden" name="code" value="${cart.sp.code}">
			                <td><button class="btn btn-primary">Remove item</button></td>
						</form>
						
	                </tr>
	               
	             
	        	</c:forEach> 
	        	 <tr>
                    <td></td>
					<td></td>
					<td>Tổng tiền thanh toán:</td>
					<td></td>
					<td>${total}đ</td>
					<td></td>
                </tr>	
	        </tbody>
	    </table>
	    <a href="/BookShop/productController" class="btn btn-primary">Continue Shopping</a>
	</div>
	
</body>
</html>