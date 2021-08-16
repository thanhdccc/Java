<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Member</title>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<h2 style="text-align: center;">Edit Member</h2>
	<div align="center">
		<form action="<%=request.getContextPath()%>/home" method="POST">
			<input type="hidden" name="action" value="edit" />
			<input type="hidden" name="id_update" value="${memberUpdate.getId()}" />
			<table>
				<tr>
					<td>
						<label>Name: </label>
					</td>
					<td>
						<input type="text" name="name_create" required="required" value="${memberUpdate.getName()}" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Date of Birth: </label>
					</td>
					<td>
						<input type="date" name="dob_create" required="required" value="${memberUpdate.getDob()}" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Email: </label>
					</td>
					<td>
						<input type="text" name="email_create" required="required" value="${memberUpdate.getEmail()}" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Phone: </label>
					</td>
					<td>
						<input type="text" name="phone_create" required="required" value="${memberUpdate.getPhone()}" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Address: </label>
					</td>
					<td>
						<input type="text" name="address_create" required="required" value="${memberUpdate.getAddress()}" />
					</td>
				</tr>
				<tr>
					<td>
						<input class="btn btn-success" type="submit" value="Update" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>