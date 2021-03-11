package com.I2PL.fileupload.fileop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.I2PL.fileupload.fileop.dao.FileUploadDao;
import com.I2PL.fileupload.fileop.model.UploadDetail;

@WebServlet(description = "List The Already Uploaded Files", urlPatterns = { "/uploadedFilesServlet" })
public class UploadedFilesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";

	/*****
	 * This Method Is Called By The Servlet Container To Process A 'GET' Request
	 *****/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/***** Get The Absolute Path Of The Web Application *****/
		String applicationPath = getServletContext().getRealPath(""),
				uploadPath = applicationPath + File.separator + UPLOAD_DIR;

		File fileUploadDirectory = new File(uploadPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}

		List<UploadDetail> fileList = new ArrayList<UploadDetail>();

		/*
		 * try {
		 * 
		 * Connection con = ConnectionDb.getConnection(); CallableStatement cstmt =
		 * (CallableStatement) con.prepareCall("{call sql12384133.list_all_files()}");
		 * ResultSet rs = cstmt.executeQuery();
		 * 
		 * while (rs.next()) { details = new UploadDetail();
		 * details.setFileName(rs.getString(5));
		 * details.setNewFileName(rs.getString(7));
		 * details.setTimestamp(rs.getTimestamp(4));
		 * details.setFileDescription(rs.getString(6)); fileList.add(details);
		 * 
		 * } } catch (Exception e) { System.out.println("SQL exception occured" + e); }
		 */

		FileUploadDao ob = new FileUploadDao();
		fileList = ob.showUploadedFile();

		request.setAttribute("uploadedFiles", fileList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/allfiles.jsp");
		dispatcher.forward(request, response);
	}
}