<%@page import="java.util.List"%>
<%@page import="com.I2PL.fileupload.fileop.model.UploadDetail"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servlet File Upload/Download</title>


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/yeti/bootstrap.min.css" />
</head>
<body>
	<div style="margin: 50px">
		<div class="panel">
			<tr align="center">
				<h1 style="text-align: center; font-family: garamond, serif">
					<b>Uploaded Files</b>
				</h1>
			</tr>
			<br>
			<table class="table table-hover">
				<thead>
					<tr class="table-primary" align="center"
						style="font-family: courier">
						<th scope="col">File Name</th>
						<th scope="col">File Description</th>
						<th scope="col">Upload Time</th>
						<th scope="col">Action</th>
						<th scope="col">Action</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<UploadDetail> uploadDetails = (List<UploadDetail>) request.getAttribute("uploadedFiles");
					if (uploadDetails != null && uploadDetails.size() > 0) {
						for (int i = 0; i < uploadDetails.size(); i++) {
					%>
					<tr class="table-secondary">
						<td align="center"><span id="fileName"><%=uploadDetails.get(i).getFileName()%></span></td>
						<td align="center"><span id="fileName"><%=uploadDetails.get(i).getFiledesciption()%></span></td>
						<td align="center"><span id="fileSize"><%=uploadDetails.get(i).getTimestamp()%></span></td>
						<td align="center"><span id="fileDownload"><a
								id="downloadLink" class="hyperLink"
								href="<%=request.getContextPath()%>/downloadServlet?fileName=<%=uploadDetails.get(i).getNewFileName()%>&newFileName=<%=uploadDetails.get(i).getFileName()%>"><button
										type="button" class="btn btn-outline-success">Download</button></a></span></td>
						<td align="center"><span id="fileDelete"><a
								id="fileDelete" class="hyperLink"
								href="<%=request.getContextPath()%>/deleteServlet?fileName=<%=uploadDetails.get(i).getNewFileName()%>"><button
										type="button" class="btn btn-outline-warning">Delete</button></a></span></td>
						<td align="center"><span id="fileEdit"><a
								id="fileEdit" class="hyperLink"
								href="<%=request.getContextPath()%>/edit.jsp?fileName=<%=uploadDetails.get(i).getFileName()%>&uniqueId=<%=uploadDetails.get(i).getNewFileName()%>&fileDescription=<%=uploadDetails.get(i).getFiledesciption()%>"><button
										type="button" class="btn btn-outline-success">Edit</button></a></span></td>
					</tr>
					<%
						}
					} else {
					%>
					<tr>
						<td colspan="6" align="center"><span id="noFiles">No
								Files Uploaded !</span></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<div class="margin_top_15px">
				<a id="fileUpload" class="hyperLink"
					href="<%=request.getContextPath()%>/fileChoose.jsp"><button
						type="button" class="btn btn-outline-info">Back</button></a>
			</div>
		</div>
		<br>
		<div class="panel">
			<a id="fileUpload" class="hyperLink"
				href="<%=request.getContextPath()%>/index.jsp"><button
					type="button" class="btn btn-danger" style="float: right">Logout</button></a>
		</div>
	</div>
</body>
</html>