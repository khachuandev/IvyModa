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
<title>Product</title>
<!-- CSS -->
<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
<style type="text/css">
.cartegory-right-content {
	margin-top: 20px;
	gap: 16px;
}
</style>
</head>

<body>
	<!-- Begin: Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>
	<!-- End: Header -->

	<!-- Begin: Main -->
	<section class="category">
		<div class="container">
			<div class="category-top row">
				<p>Trang chủ</p>
				<span>&#8722;</span>
				<p>Áo</p>
			</div>
		</div>
		<div class="container margin-top-head-category">
			<div class="row">
				<div class="cartegory-left">
					<ul>
						<li class="cartegory-left-li cartegory-left-li-custom">
							<div class="controls-sidebar">
								Size <span class="icon-sidebar"></span>
							</div>
							<ul class="item-sidebar">
								<li>S</li>
								<li>M</li>
								<li>L</li>
								<li>XL</li>
								<li>XXL</li>
							</ul>
						</li>
						<li class="cartegory-left-li cartegory-left-li-custom-border">
							<div class="controls-sidebar">
								Màu sắc <span class="icon-sidebar"></span>
							</div>
							<ul class="item-sidebar">
								<li><i class="fa-regular fa-circle white"></i></li>
								<li><i class="fa-regular fa-circle black"></i></li>
								<li><i class="fa-regular fa-circle yellow"></i></li>
								<li><i class="fa-regular fa-circle light-blue"></i></li>
								<li><i class="fa-regular fa-circle pink"></i></li>
								<li><i class="fa-regular fa-circle red"></i></li>
							</ul>
						</li>
						<li class="cartegory-left-li cartegory-left-li-price">
							<div class="controls-sidebar">
								Mức giá <span class="icon-sidebar"></span>
							</div>
							<ul class="item-sidebar">
								<li><input type="checkbox" value="1" name="price"
									id="price1"> <label for="price1">0đ - 500.000đ</label></li>
								<li><input type="checkbox" value="1" name="price"
									id="price2"> <label for="price2"> 500.000đ -
										1.000.000đ</label></li>
								<li><input type="checkbox" value="1" name="price"
									id="price3"> <label for="price3">1.000.000đ -
										3.000.000đ</label></li>
								<li><input type="checkbox" value="1" name="price"
									id="price4"> <label for="price4">3.000.000đ -
										5.000.000đ</label></li>
								<li><input type="checkbox" value="1" name="price"
									id="price5"> <label for="price5">Trên
										5.000.000đ</label></li>
							</ul>
						</li>
					</ul>
					<div class="btn-center">
						<button class="btn-check" type="button">Lọc</button>
					</div>
				</div>
				<div class="cartegory-right row">
					<div class="cartegory-right-top-item">
						<p></p>
					</div>

					<div class="cartegory-right-top-item">
						<select name="" id="">
							<option value="">Sắp xếp theo</option>
							<option value="">Giá cao đến thấp</option>
							<option value="">Giá thấp đến cao</option>
						</select>
					</div>
					<div class="cartegory-right-content row">
						<c:forEach items="${products}" var="product">
							<div class="cartegory-right-content-item">
								<p class="p-discount-cartegory">
									-20 <span class="percent-cartegory">%</span>
								</p>
								<div class="img-scale">
									<a href="${classpath}/product-detail/${product.id}"> <img
										src="${classpath }/FileUploads/${product.avatar}" alt=""></a>
								</div>
								<h1>${product.name }</h1>
								<div class="price margin-price">
									<p class="new-price">
										<fmt:formatNumber value="${product.price}"
											minFractionDigits="0" />đ
										<span> <fmt:formatNumber value="${product.salePrice}"
												minFractionDigits="0" />đ
										</span>
									</p>
									<button class="btn-buy">
										<i class="fa-solid fa-bag-shopping"></i>
									</button>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- <nav class="pagination-center">
						<ul class="pagination pagination-custom">
							<li class="page-item"><a class="page-link" href="#">Trang
									đầu</a></li>
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
							<li class="page-item"><a class="page-link page-active"
								href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
							<li class="page-item"><a class="page-link" href="#">Trang
									cuối</a></li>
						</ul>
					</nav> -->
				</div>
			</div>
		</div>
	</section>
	<!-- End: Main -->

	<!-- Begin: Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
	<!-- End: Footer -->
</body>
<!-- JS -->
<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
<script>
    // Click Size
    var selectedSizes = [];  // Mảng để lưu trữ các phần tử đã được chọn
    $('.cartegory-left-li-custom ul li').on('click', function () {
        var sizeValue = $(this).text();

        // Kiểm tra xem phần tử đã được chọn hay chưa
        if (selectedSizes.includes(sizeValue)) {
            // Nếu đã được chọn, loại bỏ khỏi mảng
            selectedSizes = selectedSizes.filter(size => size !== sizeValue);
        } else {
            // Nếu chưa được chọn, thêm vào mảng
            selectedSizes.push(sizeValue);
        }

        // Cập nhật trạng thái 'border_active'
        $(this).toggleClass('border_active', selectedSizes.includes(sizeValue));
    });

    // Click Color
    var selectedColors = [];  // Mảng để lưu trữ các màu đã được chọn
    $('.cartegory-left-li-custom-border ul li').on('click', function () {
        var colorValue = $(this).find('i').attr('class');

        // Kiểm tra xem màu đã được chọn hay chưa
        if (selectedColors.includes(colorValue)) {
            // Nếu đã được chọn, loại bỏ khỏi mảng
            selectedColors = selectedColors.filter(color => color !== colorValue);
        } else {
            // Nếu chưa được chọn, thêm vào mảng
            selectedColors.push(colorValue);
        }

        // Cập nhật trạng thái 'color_active'
        $(this).toggleClass('color_active', selectedColors.includes(colorValue));
    });

    // Hiển thị và ẩn item-sidebar khi click vào controls-sidebar
    $('.cartegory-left-li').each(function () {
        var controlsSidebar = $(this).find('.controls-sidebar');
        var itemSidebar = $(this).find('.item-sidebar');

        controlsSidebar.on('click', function () {
            // Tìm controls-sidebar và item-sidebar tương ứng
            var currentControlsSidebar = $(this);
            var currentItemSidebar = currentControlsSidebar.siblings('.item-sidebar');

            // Kiểm tra xem item-sidebar đang hiển thị hay không
            if (currentItemSidebar.is(':visible')) {
                currentItemSidebar.hide(300);
                currentControlsSidebar.removeClass('active');
            } else {
                currentItemSidebar.show(300);
                currentControlsSidebar.addClass('active');
            }
        });
    });
</script>

</html>