<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Ivy-Project</title>
<!-- CSS -->
<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
	<!-- Begin: Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Begin: Slider -->
	<div class="container">
		<div class="slider">
			<c:forEach var="slider" items="${sliders }">
				<img class="slider-image" src="${classpath }/FileUploads/${slider.slider}" alt="">
			</c:forEach>
		</div>
	</div>
	<!-- End: Slider -->

	<!-- Begin: Main -->
	<main class="container margin-top-head">
		<div>
			<h1 class="title">New Arrival</h1>
			<div class="controls">
				<li class="active"><a href="#IVYmoda">IVY moda</a></li>
				<li><a href="#IVYmen">IVY men</a></li>
				<li><a href="#IVYkids">IVY kids</a></li>
			</div>
		</div>

		<div class="tab-container">
			<div>
				<section id="IVYmoda" class="p-slider">
					<button aria-label="Previous" class="glider-prev">
						<i class="fa-solid fa-arrow-left-long"></i>
					</button>
					<div class="glider-contain">
						<div class="glider">
							<c:forEach items="${products }" var="product">
								<c:if test="${product.gender.toLowerCase() eq 'female'}">
									<div class="product-box">
										<p class="p-discount">
											-20 <span>%</span>
										</p>
										<div class="p-img-container">
											<div class="p-img">
												<a href="${classpath }/product-detail/${product.id}"> <img
													src="${classpath }/FileUploads/${product.avatar}"
													class="p-img-front" alt="Front">
												</a>
											</div>
										</div>
										<div class="p-box-text">
											<div class="product-category">
												<a href="${classpath }/product-detail/${product.id}"
													class="product-title">${product.name}</a>
											</div>
											<div class="price">
												<p class="new-price">
													<fmt:formatNumber value="${product.price}"
														minFractionDigits="0" />
													đ <span> <fmt:formatNumber
															value="${product.salePrice}" minFractionDigits="0" />đ
													</span>
												</p>
												<button
													onclick="addToCart(${product.id}, 1, '${product.name }')"
													class="btn-buy">
													<i class="fa-solid fa-bag-shopping"></i>
												</button>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<button aria-label="Next" class="glider-next">
						<i class="fa-solid fa-arrow-right-long"></i>
					</button>
				</section>
				<section id="IVYmen" class="p-slider">
					<button aria-label="Previous" class="glider-prev1">
						<i class="fa-solid fa-arrow-left-long"></i>
					</button>
					<div class="glider-contain">
						<div class="glider1">
							<c:forEach items="${products }" var="product">
								<c:if test="${product.gender.toLowerCase() eq 'male'}">
									<div class="product-box">
										<p class="p-discount">
											-20 <span>%</span>
										</p>
										<div class="p-img-container">
											<div class="p-img">
												<a href="${classpath }/product-detail/${product.id}"> <img
													src="${classpath }/FileUploads/${product.avatar}"
													class="p-img-front" alt="Front">
												</a>
											</div>
										</div>
										<div class="p-box-text">
											<div class="product-category">
												<a href="${classpath }/product-detail/${product.id}"
													class="product-title">${product.name}</a>
											</div>
											<div class="price">
												<p class="new-price">
													<fmt:formatNumber value="${product.price}"
														minFractionDigits="0" />
													đ <span class="sale-price"> <fmt:formatNumber
															value="${product.salePrice}" minFractionDigits="0" />đ
													</span>
												</p>
												<button
													onclick="addToCart(${product.id}, 1, '${product.name }')"
													class="btn-buy">
													<i class="fa-solid fa-bag-shopping"></i>
												</button>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<button aria-label="Next" class="glider-next1">
						<i class="fa-solid fa-arrow-right-long"></i>
					</button>
				</section>
				<section id="IVYkids" class="p-slider">
					<button aria-label="Previous" class="glider-prev2">
						<i class="fa-solid fa-arrow-left-long"></i>
					</button>
					<div class="glider-contain">
						<div class="glider2">
							<c:forEach items="${products }" var="product">
								<c:if test="${product.gender.toLowerCase() eq 'kid'}">
									<div class="product-box">
										<p class="p-discount">
											-20 <span>%</span>
										</p>
										<div class="p-img-container">
											<div class="p-img">
												<a href="${classpath }/product-detail/${product.id}"> <img
													src="${classpath }/FileUploads/${product.avatar}"
													class="p-img-front" alt="Front">
												</a>
											</div>
										</div>
										<div class="p-box-text">
											<div class="product-category">
												<a href="${classpath }/product-detail/${product.id}"
													class="product-title">${product.name}</a>
											</div>
											<div class="price">
												<p class="new-price">
													<fmt:formatNumber value="${product.price}"
														minFractionDigits="0" />
													đ <span class="sale-price"> <fmt:formatNumber
															value="${product.salePrice}" minFractionDigits="0" />đ
													</span>
												</p>
												<button type="button"
													onclick="addToCart(${product.id}, 1, '${product.name }')"
													class="btn-buy">
													<i class="fa-solid fa-bag-shopping"></i>
												</button>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<button aria-label="Next" class="glider-next2">
						<i class="fa-solid fa-arrow-right-long"></i>
					</button>
				</section>
			</div>
			<div class="showAll">
				<a href="${classpath }/frontend/product.html">Xem tất cả</a>
			</div>
		</div>

		<div class="tab-container">
			<h1 class="title-center">YEAR END SALE - UP TO 30% TOÀN BỘ SẢN
				PHẨM</h1>
			<div class="list-item">
				<c:forEach items="${products }" var="product">
					<div class="product-box">
						<p class="p-discount">
							-30 <span>%</span>
						</p>
						<div class="p-img-container">
							<div class="p-img">
								<a href="${classpath }/product-detail/${product.id}"> <img
									src="${classpath}/FileUploads/${product.avatar}"
									class="p-img-front" alt="Front">
								</a>
							</div>
						</div>
						<div class="p-box-text">
							<div class="product-category">
								<a href="${classpath }/product-detail/${product.id}"
									class="product-title">${product.name }</a>
							</div>
							<div class="price">
								<p class="new-price">
									<fmt:formatNumber value="${product.price}"
										minFractionDigits="0" />
									đ <span class="sale-price"> <fmt:formatNumber
											value="${product.salePrice}" minFractionDigits="0" />đ
									</span>
								</p>
								<button type="button"
									onclick="addToCart(${product.id}, 1, '${product.name }')"
									class="btn-buy">
									<i class="fa-solid fa-bag-shopping"></i>
								</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="showAll">
				<a href="${classpath }/product">Xem tất cả</a>
			</div>
		</div>

		<div>
			<h1 class="title-bottom">Gallery</h1>
		</div>
		<div class="container margin-top-head">
			<div class="list-bottom">
				<div class="item-bottom">
					<div class="product">
						<a href="${classpath }/product"><img width="100%"
							src="https://pubcdn.ivymoda.com/files/news/2024/01/08/e57b5aa0028c430c7356b7e00462dc9f.jpg"
							alt=""></a>
					</div>
				</div>
				<div class="item-bottom">
					<div class="product">
						<a href="${classpath }/product"><img width="100%"
							src="https://pubcdn.ivymoda.com/files/news/2024/01/08/594ee4a0dd77d1587cd827a3deee1342.jpg"
							alt=""></a>
					</div>
				</div>
				<div class="item-bottom">
					<div class="product">
						<a href="${classpath }/product"><img width="100%"
							src="https://pubcdn.ivymoda.com/files/news/2024/01/08/c2d3d051ef9a3d05dcf7975aeee3ecdf.jpg"
							alt=""></a>
					</div>
				</div>
				<div class="item-bottom">
					<div class="product">
						<a href="${classpath }/product"><img width="100%"
							src="https://pubcdn.ivymoda.com/files/news/2024/01/08/6c293db29aa48a4cfb70876b0d35c72b.jpg"
							alt=""></a>
					</div>
				</div>
				<div class="item-bottom">
					<div class="product">
						<a href="${classpath }/product"><img width="100%"
							src="https://pubcdn.ivymoda.com/files/news/2024/01/08/e8d2186e48a2d931de961d5bf132fb4d.jpg"
							alt=""></a>
					</div>
				</div>
			</div>
		</div>
	</main>
	<!-- End: Main -->

	<!-- Begin: Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
</body>
<!-- JS -->
<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
<!-- Add to cart -->
<script type="text/javascript">
		addToCart = function(_productId, _quantity, _productName) {		
			//alert("Thêm "  + _quantity + " sản phẩm '" + _productName + "' vào giỏ hàng ");
			let data = {
				productId: _productId, //lay theo id
				quantity: _quantity,
				productName: _productName,
			};
				
			//$ === jQuery
			jQuery.ajax({
				url : "/add-to-cart",
				type : "POST",
				contentType: "application/json",
				data : JSON.stringify(data),
				dataType : "json", //Kieu du lieu tra ve tu controller la json
				
				success : function(jsonResult) {
					alert(jsonResult.code + ": " + jsonResult.message);
					let totalProducts = jsonResult.totalCartProducts;
					$("#totalCartProductsId").html(totalProducts);
				},
				
				error : function(jqXhr, textStatus, errorMessage) {
					alert(jsonResult.code + ': Đã có lỗi xay ra...!')
				},
			});
		}
	</script>
</html>