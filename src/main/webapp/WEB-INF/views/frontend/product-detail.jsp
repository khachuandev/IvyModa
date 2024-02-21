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
<title>Product-Detail</title>
<!-- CSS -->
<jsp:include page="/WEB-INF/views/frontend/layout/css.jsp"></jsp:include>
</head>

<body>
	<!-- Begin: Header -->
	<jsp:include page="/WEB-INF/views/frontend/layout/header.jsp"></jsp:include>
	<!-- End: Header -->

	<section class="product-detail">
		<div class="container">
			<div class="product-detail-content row">
				<div class="product-detail-content-left row">
					<div class="product-detail-content-left-big-img-container">
						<div class="product-detail-content-left-big-img">
							<c:forEach items="${productImages}" var="productImage"
								varStatus="loop">
								<c:if
									test="${loop.index == 0 && productImage.product.gender.toLowerCase() eq product.gender.toLowerCase()}">
									<img src="${classpath}/FileUploads/${productImage.path}" alt="">
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="product-detail-content-left-small-img">
						<c:forEach items="${productImages }" var="productImage">
							<c:if
								test="${productImage.product.gender.toLowerCase() eq 'male'}">
								<img src="${classpath }/FileUploads/${productImage.path }"
									alt="">
							</c:if>
							<c:if
								test="${productImage.product.gender.toLowerCase() eq 'female'}">
								<img src="${classpath }/FileUploads/${productImage.path }"
									alt="">
							</c:if>
							<c:if
								test="${productImage.product.gender.toLowerCase() eq 'kid'}">
								<img src="${classpath }/FileUploads/${productImage.path }"
									alt="">
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="product-detail-content-right">
					<div class="product-detail-content-right-product-name">
						<h1>${product.name}</h1>
						<p>SKU: 67M8562</p>
					</div>
					<div class="product-detail-content-right-product-price">
						<p class="new-price">
							<fmt:formatNumber value="${product.price}" minFractionDigits="0" />
							đ <span class="sale-price"> <fmt:formatNumber
									value="${product.salePrice}" minFractionDigits="0" />đ
							</span><sup>-20%</sup>
						</p>
					</div>
					<div class="product-detail-content-right-product-color">
						<p>
							<span>Màu sắc</span>
						</p>
						<div class="product-detail-content-right-product-color-img">
							<span class="color-product-detail white"></span> <span
								class="color-product-detail black"></span> <span
								class="color-product-detail red"></span> <span
								class="color-product-detail yellow"></span> <span
								class="color-product-detail light-blue"></span> <span
								class="color-product-detail pink"></span>
						</div>
					</div>
					<div class="product-detail-content-right-product-size">
						<p>
							<span>Size</span>
						</p>
						<div class="size">
							<span>S</span> <span>M</span> <span>L</span> <span>XL</span> <span>XXL</span>
						</div>
					</div>
					<div class="quantity-product-detail">
						<p>Số lượng:</p>
						<input type="number" min="0" value="1">
					</div>
					<div class="product-detail-content-right-product-button">
						<button class="btn-add-product-detail">
							<p>Thêm vào giỏ</p>
						</button>
						<button class="btn-cart-product-detail">
							<a href=""><p>Mua hàng</p></a>
						</button>
					</div>
					<div class="product-detail-content-right-bottom">
						<div class="product-detail-content-right-bottom-top">
							<i class="fa-solid fa-angle-down"></i>
						</div>

						<div class="product-detail-content-right-bottom-content-big">
							<div
								class="product-detail-content-right-bottom-content-title row">
								<div
									class="product-detail-content-right-bottom-content-title-item introduce-detail border-detail-active">
									<a href="#tab1">
										<p>Giới thiệu</p>
									</a>
								</div>
								<div
									class="product-detail-content-right-bottom-content-title-item detail">
									<a href="#tab2">
										<p>Chi tiết sản phẩm</p>
									</a>
								</div>
								<div
									class="product-detail-content-right-bottom-content-title-item preserve">
									<a href="#tab3">
										<p>Bảo quản</p>
									</a>
								</div>
							</div>
						</div>
						<div class="product-detail-content-right-bottom-content">
							<div id="tab1"
								class="product-detail-content-right-bottom-content-introduce tab-controls-detail">
								<p>Môt chiếc áo sơ mi với gam màu tươi tắn sẽ giúp nàng hoàn
									thiện bộ trang phục chỉn chu cho bất kì sự kiện nào. Thiết kế
									cơ bản nhưng không hề nhàm chán khi có thể mix&match với nhiều
									items khác nhau để tôn lên vẻ đẹp của người mặc.</p>

								<p>Đến với thiết kế sơ mi lần này, IVY moda giữ nguyên dáng
									cơ bản, được cách điệu thêm chi tiết đính hoa ở phần túi trước
									ngực. Áo có khuy cài nhỏ màu bạc. Chất liệu là lụa trơn cao
									cấp, mềm mại, tạo cảm giác mặc thoải mái.</p>

								<p>Thông tin mẫu:</p>

								<p>Chiều cao: 167 cm</p>

								<p>Cân nặng: 50 kg</p>

								<p>Số đo 3 vòng: 83-65-93 cm</p>

								<p>Mẫu mặc size M</p>

								<p>Lưu ý: Màu sắc sản phẩm thực tế sẽ có sự chênh lệch nhỏ
									so với ảnh do điều kiện ánh sáng khi chụp và màu sắc hiển thị
									qua màn hình máy tính/ điện thoại.</p>
							</div>
							<div id="tab2"
								class="product-detail-content-right-bottom-content-detail tab-controls-detail">
								<table>
									<tbody>
										<tr>
											<td class="border-detail-right">
												<p>Dòng sản phẩm</p>
											</td>
											<td>Ladies</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Nhóm sản phẩm</p>
											</td>
											<td>Áo khoác</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Cổ áo</p>
											</td>
											<td>Cổ đức không chân</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Tay áo</p>
											</td>
											<td>Tay dài</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Kiểu dáng</p>
											</td>
											<td>Xuông</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Độ dài</p>
											</td>
											<td>Ngang hông</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Họa tiết</p>
											</td>
											<td>Trơn</td>
										</tr>
										<tr>
											<td class="border-detail-right">
												<p>Chất liệu</p>
											</td>
											<td>Tuysi</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div id="tab3"
								class="product-detail-content-right-bottom-content-preserve tab-controls-detail">
								<p>Chi tiết bảo quản sản phẩm :</p>

								<p>* Các sản phẩm thuộc dòng cao cấp (Senora) và áo khoác
									(dạ, tweed, lông, phao) chỉ giặt khô, tuyệt đối không giặt ướt.
								</p>

								<p>* Vải dệt kim: sau khi giặt sản phẩm phải được phơi ngang
									tránh bai giãn.</p>

								<p>* Vải voan, lụa, chiffon nên giặt bằng tay.</p>

								<p>* Vải thô, tuytsi, kaki không có phối hay trang trí đá
									cườm thì có thể giặt máy.</p>

								<p>* Vải thô, tuytsi, kaki có phối màu tương phản hay trang
									trí voan, lụa, đá cườm thì cần giặt tay.</p>

								<p>* Đồ Jeans nên hạn chế giặt bằng máy giặt vì sẽ làm phai
									màu jeans. Nếu giặt thì nên lộn trái sản phẩm khi giặt, đóng
									khuy, kéo khóa, không nên giặt chung cùng đồ sáng màu, tránh
									dính màu vào các sản phẩm khác.</p>

								<p>* Các sản phẩm cần được giặt ngay không ngâm tránh bị
									loang màu, phân biệt màu và loại vải để tránh trường hợp vải
									phai. Không nên giặt sản phẩm với xà phòng có chất tẩy mạnh,
									nên giặt cùng xà phòng pha loãng.</p>

								<p>* Các sản phẩm có thể giặt bằng máy thì chỉ nên để chế độ
									giặt nhẹ, vắt mức trung bình và nên phân các loại sản phẩm cùng
									màu và cùng loại vải khi giặt.</p>

								<p>* Nên phơi sản phẩm tại chỗ thoáng mát, tránh ánh nắng
									trực tiếp sẽ dễ bị phai bạc màu, nên làm khô quần áo bằng cách
									phơi ở những điểm gió sẽ giữ màu vải tốt hơn.</p>

								<p>* Những chất vải 100% cotton, không nên phơi sản phẩm
									bằng mắc áo mà nên vắt ngang sản phẩm lên dây phơi để tránh
									tình trạng rạn vải.</p>

								<p>* Khi ủi sản phẩm bằng bàn là và sử dụng chế độ hơi nước
									sẽ làm cho sản phẩm dễ ủi phẳng, mát và không bị cháy, giữ màu
									sản phẩm được đẹp và bền lâu hơn. Nhiệt độ của bàn là tùy theo
									từng loại vải.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="product-related">
		<div class="product-related-title">
			<p>Sản phẩm liên quan</p>
		</div>
		<div class="container margin-top-head">
			<div class="product-related-content">
				<c:forEach items="${products}" var="relatedProduct">
					<c:if
						test="${relatedProduct.gender.toLowerCase() eq product.gender.toLowerCase() && relatedProduct.id ne product.id}">
						<div class="product-related-item">
							<div class="img-related-scale">
								<a href="${classpath}/product-detail/${relatedProduct.id}"><img
									src="${classpath}/FileUploads/${relatedProduct.avatar}" alt=""></a>
							</div>
							<h1>${relatedProduct.name}</h1>
							<div class="price margin-price margin-detail-price">
								<p class="new-price">
									<fmt:formatNumber value="${relatedProduct.price}"
										minFractionDigits="0" />
									<sup>đ</sup> <span class="sale-price"> <fmt:formatNumber
											value="${relatedProduct.salePrice}" minFractionDigits="0" /><sup>đ</sup>
								</p>
								<button class="btn-buy">
									<i class="fa-solid fa-bag-shopping"></i>
								</button>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- Begin: Footer -->
	<jsp:include page="/WEB-INF/views/frontend/layout/footer.jsp"></jsp:include>
	<!-- End: Footer -->
	<a href="#"><i class="fa-solid fa-circle-chevron-up up-header"></i></a>
</body>
<!-- JS -->
<jsp:include page="/WEB-INF/views/frontend/layout/js.jsp"></jsp:include>
<script>
	// Đổi img
	const bigImg = document
			.querySelector(".product-detail-content-left-big-img img")
	const smallImg = document
			.querySelectorAll(".product-detail-content-left-small-img img")
	smallImg.forEach(function(imgItem, X) {
		imgItem.addEventListener("click", function() {
			bigImg.src = imgItem.src
		})
	})

	// Đổi item 
	$('.tab-controls-detail').hide();
	$('.tab-controls-detail:first-child').fadeIn();

	// Xử lý sự kiện khi nhấp vào tab
	$('.product-detail-content-right-bottom-content-title-item').click(
			function() {
				$('.product-detail-content-right-bottom-content-title-item')
						.removeClass('border-detail-active');
				$(this).addClass('border-detail-active');
				let id_tab_content = $(this).children('a').attr('href');
				$('.tab-controls-detail').fadeOut(1);
				$(id_tab_content).fadeIn(1);
				return false;
			});

	// Chọn màu sắc
	$('.color-product-detail').click(function() {
		$('.color-product-detail').removeClass('active-color-product-detail');
		$(this).addClass('active-color-product-detail');
	});

	// Zoom ảnh
	const imgContElm = document
			.querySelector(".product-detail-content-left-big-img");
	const imgElm = document
			.querySelector(".product-detail-content-left-big-img img");

	// Tỷ lệ phóng to thu nhỏ
	const ZOOM = 1.5; // Bạn có thể điều chỉnh giá trị này theo sở thích của mình

	// Sự kiện khi rê chuột vào
	imgContElm.addEventListener('mouseenter', function() {
		imgElm.style.transform = `scale(${ZOOM})`;
	});

	// Sự kiện khi rời chuột
	imgContElm.addEventListener('mouseleave', function() {
		imgElm.style.transform = 'scale(1)';
	});

	// Sự kiện khi di chuyển chuột
	imgContElm.addEventListener('mousemove', function(mouseEvent) {
		const rect = imgContElm.getBoundingClientRect();
		let mouseX = mouseEvent.clientX - rect.left;
		let mouseY = mouseEvent.clientY - rect.top;

		mouseX = Math.min(Math.max(0, mouseX), imgContElm.clientWidth);
		mouseY = Math.min(Math.max(0, mouseY), imgContElm.clientHeight);

		const offsetX = (mouseX / imgContElm.clientWidth) * 100;
		const offsetY = (mouseY / imgContElm.clientHeight) * 100;

		imgElm.style.transformOrigin = `${offsetX}% ${offsetY}%`;
	});

	// Thay đổi chiều cao của vùng chứa hình ảnh
	function changeHeight() {
		imgContElm.style.height = imgContElm.clientWidth + 'px';
	}

	changeHeight();

	// Sự kiện thay đổi chiều cao
	window.addEventListener('resize', changeHeight);
</script>

</html>