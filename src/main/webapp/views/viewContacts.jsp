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
<!-- <script>
		function confirmDelete(){
				return confirm("Are you sure to delete?");
			}

		$(document).ready(function() {
		    $('#contact_table').DataTable( {
		        "pagingType": "full_numbers"
		    } );
		} );
	</script>
 -->
<script>
	$(document).ready(function() {
		$('#contactTbl').DataTable({
			"pagingType" : "full_numbers"
		});
		
		$('#roleId').on('change', function() {
                this.form.submit();
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
					aria-expanded="false"> USERS </a>
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
				<form action="viewContacts">
					<table>
						<tr>
							<td>Select Roles</td>
							<td>
							<select name="role" id="roleId">
									<option value="select  ">  --Select--  </option>
									<option value="Case-Worker">Case Worker</option>
									<option value="Admin"> Admin </option>
									
									
							</select></td>
						</tr>
					</table>
				</form></br></br>

				<table border="1" id="contactTbl">
					<thead>
						<tr>
							<th>S.no</th>
							<th>First Name</th>
							<th>Email</th>
							<th>Role</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="c" varStatus="index">
							<tr>
								<td>${index.count}</td>
								<td>${c.firstName}${c.lastName}</td>
								<td>${c.email}</td>
								<td>${c.role}</td>
								<td><a href="editContact?cid=${c.id}">Edit</a>| <c:if
										test="${c.deleteSW eq 'N' }">
										<a href="active?cid=${c.id}" onclick="return confirmActive()">Active</a>
									</c:if> <c:if test="${c.deleteSW eq 'Y'}">
										<a href="deleteContact?cid=${c.id}"
											onclick="return confirmDelete()">Delete</a>
									</c:if>
							</tr>

						</c:forEach>

					</tbody>
				</table>
				<c:if test="${cpn > 1}">
					<a href="viewContacts?pno=${cpn-1}">PREV</a>
				</c:if>
				<c:forEach begin="1" end="${tp}" var="pno">
					<c:if test="${cpn == pno}">
						${pno}
					</c:if>
					<c:if test="${cpn != pno}">
						<a href="viewContacts?pno=${pno}">${pno}</a>
					</c:if>
				</c:forEach>
				<c:if test="${cpn <tp}">
					<a href="viewContacts?pno=${cpn+1}">NEXT</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>