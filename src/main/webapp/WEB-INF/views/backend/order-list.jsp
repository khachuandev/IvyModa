<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html dir="ltr" lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- Favicon icon -->
	<link rel="icon" type="image/png" sizes="16x16"
		href="${classpath}/admin/assets/images/favicon.png">
	<title>${projectTitle }</title>
	
	<!-- variables -->
	<jsp:include page="/WEB-INF/views/common/variables.jsp"></jsp:include>
	
	<!-- Custome css resource file -->
	<jsp:include page="/WEB-INF/views/backend/layout/css.jsp"></jsp:include>

</head>

<body>


	<!-- End test -->
	<!-- ============================================================== -->
	<!-- Preloader - style you can find in spinners.css -->
	<!-- ============================================================== -->

	<!-- ============================================================== -->
	<!-- Main wrapper - style you can find in pages.scss -->
	<!-- ============================================================== -->
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">

		<!-- Topbar header - style you can find in pages.scss -->
		<jsp:include page="/WEB-INF/views/backend/layout/header.jsp"></jsp:include>
		<!-- End Topbar header -->

		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<jsp:include page="/WEB-INF/views/backend/layout/left-slide-bar.jsp"></jsp:include>
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->

		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row">
					<div class="col-12 align-self-center">
						<h2
							class="page-title text-truncate text-dark font-weight-medium mb-1">Order
							Tracking</h2>
					</div>
				</div>
			</div>
			
			<!-- ============================================================== -->
			<!-- End Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<!-- basic table -->
				<form action="${classpath }/admin/order-list" method="get">
					
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-body">
										<div class="table-responsive">
											
											<!-- Tìm kiếm -->
											<div class="row">
												<div class="col-md-3">
													<div class="form-group mb-4">
				                                        <select class="form-control"
															id="status" name="status">
																<option value="2">Tất cả đơn hàng</option>
																<option value="1">Đơn hàng đã giao</option>
																<option value="0">Đơn hàng chưa giao</option>
														</select>
													</div>
												</div>
												
												<div class="col-md-2">
													<input class="form-control" type="date" 
														id="beginDate" name="beginDate"/>		
												</div>
												<div class="col-md-2">
													<input class="form-control"
																	type="date" id="endDate" name="endDate" />		
												</div>
												
												<div class="col-md-3">
													<input type="text" class="form-control" id="keyword"
															name="keyword" placeholder="Search keyword" />		
												</div>
												
												<div class="col-md-1">
													<button type="submit" id="btnSearch" name="btnSearch" class="btn btn-primary">Search</button>
												</div>
												<div class="col-md-1">
													<input id="page" name="page" type="hidden"
																		class="form-control" value="${saleOrderSearch.currentPage }"></th>
												</div>
											</div>
											<!-- Hết tìm kiếm -->
		
											<table id="zero_config"
												class="table table-striped table-bordered no-wrap">
												<thead>
													<tr align="center">
														<th scope="col">No.</th>
														<th scope="col">Code</th>
														<th scope="col">Customer</th>
														<th scope="col">Mobile</th>
														<th scope="col">Address</th>
														<th scope="col">Payment</th>
														<th scope="col">Create by</th>
														<!-- <th scope="col">Update by</th> -->
														<th scope="col">Create date</th>
														<th scope="col">Delivery date</th>
														<th scope="col">Status</th>
														<th scope="col">Edit</th>
														<th scope="col">Delete</th>
	
													</tr>
												</thead>
												<tbody>
													<c:forEach var="saleOrder" items="${saleOrders }"
														varStatus="loop">
														<tr>
															<th scope="row">${loop.index + 1 }</th>
	
															<td align="center">${saleOrder.code }</td>
															<td>${saleOrder.customerName }</td>
															<td align="center">${saleOrder.customerMobile }</td>
															<td>${saleOrder.customerAddress }</td>
															<td align="right"><fmt:formatNumber
																	value="${saleOrder.total }" minFractionDigits="0"></fmt:formatNumber>
															</td>
															<td>${saleOrder.userCreateSaleOrder.username }</td>
															<%-- <td>${saleOrder.updateBy }</td> --%>
															<td><fmt:formatDate pattern="dd-MM-yyyy"
																	value="${saleOrder.createDate}" /></td>
															<td><fmt:formatDate pattern="dd-MM-yyyy"
																	value="${saleOrder.updateDate}" /></td>
	
															<td><c:choose>
																	<c:when test="${saleOrder.status }">
																		Đã giao hàng
																	</c:when>
																	<c:otherwise>Chưa giao hàng</c:otherwise>
																</c:choose></td>
	
															<td><a
																href="${rootpath }/edit-product/${saleOrder.id }"
																role="button" class="btn btn-primary">Edit</a>
															<td><a
																href="${rootpath }/delete-product/${saleOrder.id }"
																role="button" class="btn btn-secondary">Delete</a>
														</tr>
													</c:forEach>
												</tbody>
											</table>

											<div class="row">
												<div class="col-md-6">
													<div class="form-group mb-4">
														<h3>
															Total sales:
															<fmt:formatNumber value="${totalSales }"
																minFractionDigits="0"></fmt:formatNumber>(vnđ)
														</h3>
													</div>
												</div>
	
												<div class="col-md-6" >
													<!-- Phan trang -->
													<div class="pagination float-right">
														<div id="paging"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					
				</form>
			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<jsp:include page="/WEB-INF/views/backend/layout/footer.jsp"></jsp:include>
			<!-- ============================================================== -->
			<!-- End footer -->
			<!-- ============================================================== -->
		</div>
		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->
	</div>
	<!-- ============================================================== -->
	<!-- End Wrapper -->
	<!-- ============================================================== -->

	<!-- Slider js: All Jquery-->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
	
	<script type="text/javascript">
		$( document ).ready(function() {
			//Dat gia tri cua status ung voi dieu kien search truoc do
			$("#status").val(${saleOrderSearch.status});
			
			$("#paging").pagination({
				currentPage: ${saleOrderSearch.currentPage}, //Trang hien tai
				items: ${saleOrderSearch.totalItems}, //Tong so don hang (total sale orders)
				itemsOnPage: ${saleOrderSearch.sizeOfPage},
				cssStyle: 'light-theme',
				onPageClick: function(pageNumber, event) {
					$('#page').val(pageNumber);
					$('#btnSearch').trigger('click');
				},
			});
		});
	</script>
	
</body>

</html>