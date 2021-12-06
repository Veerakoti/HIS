<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">

<title>Contact Details</title>
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.dataTables.min.css">

<link
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		$('#contactTbl').DataTable({
			"pagingType" : "full_numbers"
		});
	});
	function confirmActive() {
		return confirm("Are you sure to active?");
	}
	function confirmDelete() {
		return confirm("Are you sure to delete?");
	}
</script>
</head>
<body>



	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">HIS APPLICATION</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">HOME
						<span class="sr-only">(current)</span>
				</a></li>

				<!-- one option start-->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> CONTACTS </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="addUser">ADD USER</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="viewContacts">VIEW USER'S
							DETAILS</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="addPlan">CREATE PLAN</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="viewPlans">PLAN LIST</a>
					</div></li>
				<!-- one option end -->

			</ul>
		</div>
	</nav>

<div class="container"
		style="background-color: rgba(205, 220, 57, 0.1);">
		<div class="card">
			<div class="card-header bg-primary text-white text-center">
				<H3>USERS LIST</H3>
			</div>

			<div class="card-body">
	<table border="1" id="contactTbl">
		<thead>
			<tr>
				<th>S.no</th>
				<th>PLAN Name</th>
				<th>Plan DESC</th>
				<th>Plan Start Date</th>
				<th>Plan End Date</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${plans}" var="c" varStatus="index">
				<tr>
					<td>${index.count}</td>
					<td>${c.planName}</td>
					<td>${c.planDesc}</td>
					<td>${c.startDate}</td>
					<td>${c.endDate}</td>
					<td><a href="editPlan?pid=${c.planId}">Edit</a>|
					
					 <c:if test="${c.activeSW eq 'N' }">
							<a href="activeplan?cid=${c.planId}" onclick="return confirmActive()">Active</a>
						</c:if> 
						<c:if test="${c.activeSW eq 'Y'}">
							<a href="deleteplan?cid=${c.planId}" onclick="return confirmDelete()">Delete</a>
						</c:if>
				</tr>

			</c:forEach>

		</tbody>
	</table>
	</div>

			<!-- end of body -->
			<!-- Message-->
			<div>
				<font color='green'>${success}</font>
			</div>
			<div>
				<font color='red'>${error}</font>
			</div>
		</div>
		<!-- End of card -->
	</div>

</body>
</html>