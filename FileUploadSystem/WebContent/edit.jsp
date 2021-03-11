<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/yeti/bootstrap.min.css" />
<title>Edit Time Table</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function getQueryVariable(variable) {
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			if (pair[0] == variable) {
				return pair[1];
			}
		}
	}
	var fileName = getQueryVariable("fileName");
	var uniqueId = getQueryVariable("uniqueId");
	var fileDescription = getQueryVariable("fileDescription");
	$(document)
			.ready(
					function() {
						document.getElementById("fileName").value = fileName
								.substring(0, fileName.lastIndexOf('.'))
								.replaceAll("%20", " ");
						document.getElementById("extension").value = fileName
								.substring(fileName.lastIndexOf('.'));
						document.getElementById("uniqueId").value = uniqueId;
						document.getElementById("fileDescription").value = fileDescription
								.replaceAll("%20", " ");
					});
</script>
</head>
<body>
	<br>
	<header>
		<h1 style="text-align: center; font-family: garamond, serif">Edit
			Patient Info</h1>
	</header>
	<div class="container">

		<form method="get" action="EditInfo">
			<input type="hidden" name="uniqueId" id="uniqueId">
			<div class="form-group">

				<label for="fileName"><i><strong>File Name :</strong></i></label> <input
					type="text" class="form-control" name="fileName" id="fileName"><br>
			</div>
			<div class="form-group">

				<label for="extension"><i><strong>Extension :</strong></i></label> <input
					type="text" class="form-control" name="extension" id="extension"
					readOnly><br>
			</div>
			<div class="form-group">

				<label for="fileDescription"><i><strong>File
							Description :</strong></i></label> <input type="text" class="form-control"
					name="fileDescription" id="fileDescription"><br>
			</div>
			<input type="submit" class="btn btn-primary" value="Update Info">
		</form>
	</div>
</body>
</html>