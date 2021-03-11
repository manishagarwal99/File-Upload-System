<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servlet File Upload/Download</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/yeti/bootstrap.min.css" />
<script type="text/javascript" src="jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="fileupload.js"></script>
</head>
<body>
	<%
		String result = (String) session.getAttribute("name");
	String id = (String) session.getAttribute("id");
	%>
	<div class="container">
		<div style="margin: 50px">
			<!-- <h1 style="text-align:center;font-family: garamond, serif ">File Upload</h1> -->
			<h2 style="text-align: center; color: black; font-family: courier">
				Welcome
				<%=result%>!
			</h2>
			<br>
			<form id="fileUploadForm" method="post" action="fileUploadServlet"
				enctype="multipart/form-data">
				<div class="form_group">

					<div class="form-group">
						<label for="staticEmail" class="col-sm-0 col-form-label"><strong>User
								Id :</strong></label>
						<div class="col-sm-0">
							<input type="text" readonly="" class="form-control-plaintext"
								id="staticId" name="staticId" value="<%=id%>"
								style="font-family: courier">
						</div>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1"><b><strong>Full
									Name :</strong></b></label> <input type="text" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"
							placeholder="Enter Full Name" required
							style="font-family: courier">
					</div>
					<br>
					<div class="form-group">
						<label for="exampleTextarea"><b><strong>File
									Description :</strong></b></label>
						<textarea class="form-control" id="exampleTextarea" rows="3"
							style="font-family: courier" name="description"></textarea>
					</div>

					<label><i>Upload File</i></label><span id="colon">: </span>
					<button id="addFileButton" type="button">Add Files</button>
					<div id="newInput">
						<div>
							<input id="fileAttachment" type="file" name="fileUpload"
								multiple="multiple" accept="image/jpeg, image/png, .pdf, .mp4"
								onchange="restrictTypeAndSize(this)" style="display: none" />
						</div>
					</div>
					<span id="fileUploadErr"><i><strong>Please
								Choose A File!</strong></i></span>
					<ul class="list-group" id="file-list"></ul>
				</div>
				<br>
				<button id="uploadBtn" type="submit" class="btn btn-primary">Upload</button>
			</form>
			<br> <br>
			<!-- List All Uploaded Files -->
			<div class="panel">
				<a id="allFiles" class="hyperLink"
					href="<%=request.getContextPath()%>/uploadedFilesServlet"><button
						type="button" class="btn btn-outline-warning">List all
						uploaded files</button></a>
			</div>
			<div class="panel">
				<a id="fileUpload" class="hyperLink"
					href="<%=request.getContextPath()%>/index.jsp"><button
						type="button" class="btn btn-danger" style="float: right">Logout</button></a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var c = 1;
		var inputFile = document.getElementById("fileAttachment");
		var button = document.getElementById("addFileButton");
		var countextralist = 0;

		button.onclick = function() {
			inputFile.click();
		}

		function listFilesFunction(name) {
			var fileList = document.getElementById("file-list");
			var node = document.createElement("li");
			node.className = "list-group-item justify-content-between align-items-center";
			var fileInfo = "<strong>" + name + "</strong>.";
			node.innerHTML = fileInfo;
			fileList.appendChild(node);
		}

		function addFilesFunction() {
			var addFiles = document.getElementById("newInput");
			var node = document.createElement("div");
			var newID = "fileAttachment" + c;
			var fileInfo = "<input id=\""
					+ newID
					+ "\" type=\"file\" name=\"fileUpload\" multiple=\"multiple\" accept=\"image/jpeg, image/png, .pdf, .mp4\" style=\"display:none\" onchange=\"restrictTypeAndSize(this)\" />";
			node.innerHTML = fileInfo;
			addFiles.appendChild(node);
			inputFile = document.getElementById(newID);
			c = c + 1;
		}

		function deleteExtraList(count, v, ide) {
			var total = $("#file-list").children().length;
			if (count != 0 && total >= count) {
				let menu = document.getElementById('file-list');
				var i = total - count;
				for (var j = 0; j < count; j++) {
					menu.removeChild(menu.childNodes[i]);
				}
			}
			if (v == '') {
				inputFile = document.getElementById(ide);
			}
		}

		function checkTrue(name, v) {
			if (v == '') {
			} else {
				addFilesFunction();
				listFilesFunction(name);
			}
		}

		function restrictTypeAndSize(obj) {
			countextralist = 0;
			for (var i = 0; i < obj.files.length; i++) {
				s = writefiles(obj.files[i], obj.id);
			}
		}

		function writefiles(file, idele) {
			inputFilethis = document.getElementById(idele);
			if (file.type.indexOf("image") == -1
					&& file.type.indexOf("pdf") == -1
					&& file.type.indexOf("mp4") == -1) {
				alert("File : "+file.name+"\nError! Invalid Type!");
				inputFilethis.value = "";
				return false;
			}

			if (file.size > 10485760) {
				alert("File : "+file.name+"\nError! Individual Image size should not be greater than 10 Mb!");
				inputFilethis.value = "";
				return false;
			}
			var result = $('div#result');
			if (window.FileReader && window.Blob) {
				var file = file;
				var reader = new FileReader();
				reader.onloadend = function(e) {
					var arr = (new Uint8Array(e.target.result)).subarray(0, 4);
					var header = '';
					for (var i = 0; i < arr.length; i++) {
						header += arr[i].toString(16);
					}
					var type = 'unknown';
					switch (header) {
					case '89504e47':
						type = 'image/png';
						break;
					case 'ffd8ffe0':
					case 'ffd8ffe1':
					case 'ffd8ffe2':
						type = 'image/jpeg';
						break;
					case '25504446':
						type = 'application/pdf';
						break;
					}

					if (file.type !== type) {
						alert("File : "+file.name+"\nError! File extension doesn't match the file type!");
						inputFilethis.value = "";
						deleteExtraList(countextralist, inputFilethis.value,
								idele);
						countextralist = 0;
					} else {
						countextralist += 1;
						checkTrue(file.name, inputFilethis.value);
					}
				};
				reader.readAsArrayBuffer(file);
			} else {
				result
						.html('<span style="color: red; ">Your browser is not supported. Sorry.</span>');
				console
						.error('FileReader or Blob is not supported by the browser.');
			}
		}
	</script>
</body>
</html>