<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- directive của JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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
	href="${classpath}/backend/assets/images/favicon.png">
<title>${title }</title>

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
					<div class="col-7 align-self-center">
						<h2
							class="page-title text-truncate text-dark font-weight-medium mb-1">Add
							new role</h2>
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
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<sf:form class="form"
									action="${classpath }/admin/role/edit-save" method="post"
									modelAttribute="role" enctype="multipart/form-data">
									<div class="form-body">

										<sf:hidden path="id" />
										<!-- id > 0 => update -->

										<div class="row">

											<div class="col-md-6">
												<div class="form-group mb-4">
													<label for="name">Role name</label>
													<sf:input path="name" type="text" class="form-control"
														id="name" name="name" placeholder="role name"></sf:input>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-6">
												<div class="form-group mb-4">
													<label for="role">Create by</label>
													<sf:select path="userCreateRole.id" class="form-control"
														id="userCreateRole">
														<sf:options items="${users }" itemValue="id"
															itemLabel="username"></sf:options>
													</sf:select>
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group mb-4">
													<label for="role">Update by</label>
													<sf:select path="userUpdateRole.id" class="form-control"
														id="userUpdateRole">
														<sf:options items="${users }" itemValue="id"
															itemLabel="username"></sf:options>
													</sf:select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group mb-4">
													<label for="createdate">Create date</label>

													<sf:input path="createDate" class="form-control"
														type="date" id="createDate" name="createDate"></sf:input>
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group mb-4">
													<label for="updatedate">Update date</label>

													<sf:input path="updateDate" class="form-control"
														type="date" id="updateDate" name="updateDate"></sf:input>
												</div>
											</div>
										</div>

										<div class="row">
											<div class="col-md-12">
												<div class="form-group mb-4">
													<label for="description">Description</label>
													<sf:textarea path="description" id="description"
														name="description" class="form-control" rows="3"
														placeholder="desription..."></sf:textarea>
												</div>
											</div>
										</div>

										<div class="row">

											<div class="col-md-10">
												<div class="form-group mb-4">
													<label for="status">&nbsp;&nbsp;&nbsp;&nbsp;</label>
													<sf:checkbox path="status" class="form-check-input"
														id="status" name="status"></sf:checkbox>
													<label for="status">Active</label>
												</div>
											</div>

										</div>


										<div class="row">
											<div class="col-md-12">
												<div class="form-group mb-4">
													<a href="${classpath }/admin/role/list"
														class="btn btn-secondary active" role="button"
														aria-pressed="true"> Back to list </a>
													<button type="submit" class="btn btn-primary">Save
														role</button>
												</div>
											</div>
										</div>

									</div>
								</sf:form>
							</div>
						</div>
					</div>
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
		<!-- End Wrapper -->
		<!-- ============================================================== -->
	</div>
	<!-- Slider js: All Jquery-->
	<jsp:include page="/WEB-INF/views/backend/layout/js.jsp"></jsp:include>
</body>

</html>