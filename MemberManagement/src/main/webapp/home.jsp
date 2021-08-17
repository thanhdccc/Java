<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Home Page</title>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<style type="text/css">
		a{
			width: 80px;
			height: 33px;
		}
		th{
			text-align: center;
		}
	</style>
</head>
<body>
	<h2 style="text-align: center;">
		<%
			String username = (String) session.getAttribute("username");
			out.print("Welcome, " + username);
		%>
		<a href="home?action=logout" class="btn btn-danger">Logout</a>
	</h2>
	<h2 style="text-align: center;">
		<a href="account?action=new" class="btn btn-success">Add</a>
	</h2>
	<div class="row" align="center">
		<div class="container">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Username</th>
						<th>Name</th>
						<th>DOB</th>
						<th>Email</th>
						<th>Phone</th>
						<th>Address</th>
						<th>Action</th>
					</tr>
				</thead>
				
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td style="text-align: center;"><c:out value="${member.getId()}"></c:out></td>
						<td><c:out value="${member.getUsername()}"></c:out></td>
						<td><c:out value="${member.getName()}"></c:out></td>
						<td style="text-align: center;"><c:out value="${member.getDob()}"></c:out></td>
						<td><c:out value="${member.getEmail()}"></c:out></td>
						<td><c:out value="${member.getPhone()}"></c:out></td>
						<td><c:out value="${member.getAddress()}"></c:out></td>
						<td style="text-align: center;">
							<a class="btn btn-success" href="account?action=edit&id=${member.getId()}">Edit</a>
							<a class="btn btn-warning" href="account?action=delete&id=${member.getId()}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>