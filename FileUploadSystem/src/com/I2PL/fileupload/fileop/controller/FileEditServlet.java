package com.I2PL.fileupload.fileop.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.I2PL.fileupload.fileop.dao.FileUploadDao;

@WebServlet("/EditInfo")
public class FileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uniqueId = request.getParameter("uniqueId");
		String fileName = request.getParameter("fileName");
		String extension = request.getParameter("extension");
		String fileNameWithExtension = fileName + extension;
		String fileDescription = request.getParameter("fileDescription");

		/*
		 * try {
		 * 
		 * Connection con = ConnectionDb.getConnection(); CallableStatement cstmt =
		 * (CallableStatement) con.prepareCall("{call sql12384133.edit_file('" +
		 * fileNameWithExtension + "', '" + fileDescription + "' , '" + uniqueId +
		 * "')}"); cstmt.executeQuery();
		 * 
		 * } catch (Exception e) { System.out.println("SQL exception occured" + e); }
		 */

		FileUploadDao ob = new FileUploadDao();
		ob.editFile(fileNameWithExtension, fileDescription, uniqueId);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/uploadedFilesServlet");
		dispatcher.forward(request, response);

	}
}