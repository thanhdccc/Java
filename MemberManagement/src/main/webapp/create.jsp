<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create Member</title>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<h2 style="text-align: center;">Add New</h2>
	<div align="center">
		<form action="<%=request.getContextPath()%>/account" method="POST">
		<input type="hidden" name="action" value="new" />
			<table>
				<tr>
					<td>
						<label>Username: </label>
					</td>
					<td>
						<input type="text" name="username_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Password: </label>
					</td>
					<td>
						<input type="password" name="password_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Name: </label>
					</td>
					<td>
						<input type="text" name="name_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Date of Birth: </label>
					</td>
					<td>
						<input type="date" name="dob_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Email: </label>
					</td>
					<td>
						<input type="text" name="email_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Phone: </label>
					</td>
					<td>
						<input type="text" name="phone_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<label>Address: </label>
					</td>
					<td>
						<input type="text" name="address_create" required="required" />
					</td>
				</tr>
				<tr>
					<td>
						<input class="btn btn-success" type="submit" value="Create" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>