<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servlet File Upload/Download</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/yeti/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<div style="margin: 50px">
			<form id="my-form" method="post" action="LoginPage">
				<fieldset>
					<h1 style="text-align: center; font-family: garamond, serif">
						<b>ABC Hospital</b>
					</h1>

					<div class="form-group">
						<label class="col-form-label" for="inputDefault"><strong>User
								Name :</strong></label> <input type="text" class="form-control"
							id="inputDefault name user" name="user"
							aria-describedby="emailHelp" placeholder="User Name"
							style="font-family: courier">
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1"><strong>Password
								:</strong></label> <input type="password" class="form-control"
							id="exampleInputPassword1 name pass" name="pass"
							placeholder="Password" style="font-family: courier">
					</div>

					<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>