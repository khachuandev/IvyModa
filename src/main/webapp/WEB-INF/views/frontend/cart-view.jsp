<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cart</title>
<!-- CSS -->
<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
<style>
.cart-content-button {
	display: flex;
	justify-content: center;
	align-items: center;
}

.cart-content-button a {
	display: block;
	font-size: 18px;
	padding: 5px 10px;
}
</style>
</head>
<body>
	<!-- Begin: Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Begin: Cart -->
	<section class="cart">
		<div class="container">
			<div class="cart-content">
				<c:choose>
					<c:when test="${totalCartProducts <= 0}">
						<p class="cart-content-quantity">Không có sản phẩm nào trong
							giỏ hàng của bạn.</p>
						<div class="cart-content-button">
							<button class="btn-back">
								<a href="${classpath }/index">Back to Shop</a>
							</button>
						</div>
					</c:when>
					<c:otherwise>
						<p class="cart-content-quantity">Giỏ hàng của bạn: ${message }</p>
						<div class="cart-content-table">
							<div class="cart-content-table-left">
								<table>
									<thead>
										<tr>
											<th>No</th>
											<th>Image</th>
											<th>Product</th>
											<th>Quantity</th>
											<th>Price</th>
											<th>Total</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${cart.productCarts }" var="item"
											varStatus="loop">
											<tr>
												<td>${loop.index + 1}</td>
												<td>
													<div class="cart-product-img">
														<img src="${classpath}/FileUploads/${item.avatar}" alt="">
													</div>
												</td>
												<td>
													<p class="cart-product-name">${item.productName}</p>
												</td>
												<td>
													<div class="cart-product-quantity-input">
														<div
															onclick="updateProductQuantity(${item.productId }, -1)"
															class="cart-minus" value="-">
															<i class="fa-solid fa-minus"></i>
														</div>
														<strong> <span
															id="productQuantity_${item.productId }">${item.quantity }</span>
														</strong>
														<div
															onclick="updateProductQuantity(${item.productId }, 1)"
															class="cart-plus" value="+">
															<i class="fa-solid fa-plus"></i>
														</div>
													</div>
												</td>
												<td>
													<div class="cart-product-price">
														<fmt:formatNumber value="${item.price }" type="currency"
															currencyCode="VND" />
													</div>
												</td>
												<td>
													<div id="totalPrice"
														class="cart-product-total cart-product-total-td">
														<fmt:formatNumber value="${item.price * item.quantity }"
															type="currency" currencyCode="VND" />
													</div>
												</td>
												<td><a
													href="${classpath }/product-cart-delete/${item.productId }">
														<i
														class="cart-product-delete-icon fa-regular fa-trash-can"></i>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="cart-content-table-right">
								<div class="cart-total-product cart-total-products">
									Tổng tiền giỏ hàng: <span><fmt:formatNumber
											value="${totalCartPrice }" type="currency" currencyCode="VND" /></span>
								</div>
								<p class="info-user-title">Thông tin khách hàng</p>
								<form action="${classpath }/cart-view" method="get">
									<div class="info-user">
										<div class="form-user">
											<div class="form-user-info">
												<label for="name">Customer name (*)</label> <input
													type="text" id="txtName" name="txtName"
													placeholder="Your name" value="${loginedUser.name }">
											</div>
											<div class="form-user-info">
												<label for="mobile">Customer mobile (*)</label> <input
													type="text" id="txtMobile" name="txtMobile"
													placeholder="Your phone number"
													value="${loginedUser.mobile }">
											</div>
											<div class="form-user-info">
												<label for="email">Customer email</label> <input
													type="email" id="txtEmail" name="txtEmail"
													placeholder="Your email" value="${loginedUser.email }">
											</div>
											<div class="form-user-info">
												<label for="name">Customer address</label> <input
													type="text" id="txtAddress" name="txtAddress"
													placeholder="Your address" value="${loginedUser.address }">
											</div>
											<div class="form-user-info-button">
												<button class="btn-back">
													<a href="${classpath }/index">Back to shop</a>
												</button>
												<button class="btn-pay" onclick="_placeOrder()">Place
													orders</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
	<!-- End: Cart -->

	<!-- Begin: Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
	<!-- End: Footer -->

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>

	<script type="text/javascript">
		updateProductQuantity = function(_productId, _quantity) {
			let data = {
				productId : _productId, //lay theo id
				quantity : _quantity
			};

			//$ === jQuery
			jQuery.ajax({
				url : "/update-product-quantity",
				type : "POST",
				contentType : "application/json",
				data : JSON.stringify(data),
				dataType : "json", //Kieu du lieu tra ve tu controller la json

				success : function(jsonResult) {
					let totalProducts = jsonResult.totalCartProducts; 
					//Viet lai so luong sau khi bam nut -, +
					$("#productQuantity_" + jsonResult.productId).html(jsonResult.newQuantity); 
				},

				error : function(jqXhr, textStatus, errorMessage) {
					alert("An error occur");
				}
			});
		}
	</script>

	<script type="text/javascript">
		function _placeOrder() {
			//javascript object
			let data = {				
				txtName : jQuery("#txtName").val(),
				txtEmail : jQuery("#txtEmail").val(), //Get by Id
				txtMobile : jQuery("#txtMobile").val(),
				txtAddress : jQuery("#txtAddress").val(),
			};
			
			//$ === jQuery
			jQuery.ajax({
				url : "/place-order",
				type : "POST",
				contentType: "application/json",
				data : JSON.stringify(data),
				dataType : "json", //Kieu du lieu tra ve tu controller la json
				
				success : function(jsonResult) {
					alert(jsonResult.code + ": " + jsonResult.message);
					//$("#placeOrderSuccess").html(jsonResult.message);
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert("An error occur");
				}
			});
		}
	</script>
</body>
</html>