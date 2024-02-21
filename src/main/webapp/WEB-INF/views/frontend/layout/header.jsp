<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<header>
	<div class="menu">
		<li><a href="${classpath}/product">NỮ</a></li>
		<li><a href="${classpath}/product">NAM</a></li>
		<li><a href="${classpath}/product">TRẺ EM</a></li>
		<li><a href="${classpath}/product">SALE MỪNG NĂM MỚI</a></li>
		<li><a href="${classpath}/product">BỘ SƯU TẬP</a></li>
		<li><a href="#">VỀ CHÚNG TÔI</a>
			<ul class="sub-menu">
				<li><a href="#">Về IVY modal</a></li>
				<li><a href="#">Fashion Show</a></li>
				<li><a href="#">Hoạt động cộng đồng</a></li>
			</ul></li>
	</div>

	<div class="logo">
		<a href="${classpath }/index"><img
			src="${classpath }/frontend/asset/image/Logo.png" alt=""></a>
	</div>

	<div class="orther">
		<li><input type="text" placeholder="TÌM KIẾM SẢN PHẨM"><i
			class="fa-solid fa-magnifying-glass"></i></li>
		<li class="orther-headphones"><a href="#"><i
				class="fa-solid fa-headphones"></i></a>
			<ul class="sub-menu-orther">
				<div>
					<div class="border-bottom-orther">
						<h3 class="title-orther">Trợ giúp</h3>
					</div>
					<li class="item-orther"><a href=""><i
							class="fa-solid fa-phone-volume icon"></i>Hotline</a></li>
					<li class="item-orther"><a href=""><i
							class="fa-solid fa-comment-dots icon"></i>Live Chat</a></li>
					<li class="item-orther"><a href=""><i
							class="fa-solid fa-arrows-rotate icon"></i>Messenger</a></li>
					<li class="item-orther"><a href=""><i
							class="fa-regular fa-envelope icon"></i>Email</a></li>
					<li class="item-orther"><a href=""><i
							class="fa-solid fa-paw icon"></i>Tra cứu đơn hàng</a></li>
				</div>
			</ul></li>
		<li><a href="#"><i class="fa-solid fa-user"></i></a></li>
		<li class="last-orther"><a href="#"><i
				class="fa-solid fa-bag-shopping"></i></a><span id="totalCartProductsId"
			class="quantity">${totalItems}</span></li>
	</div>
</header>