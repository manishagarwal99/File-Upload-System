package com.I2PL.fileupload.fileop.controller;

import java.io.File;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.I2PL.fileupload.fileop.dao.FileUploadDao;

/**
 * Servlet implementation class FileDeleteServlet
 */
@WebServlet(description = "Delete File From The Server", urlPatterns = { "/deleteServlet" })
public class FileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";

	/**
	 * Default constructor.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/***** Get The Absolute Path Of The File To Be Downloaded *****/
		String fileName = request.getParameter("fileName"), applicationPath = getServletContext().getRealPath(""),
				downloadPath = applicationPath + File.separator + UPLOAD_DIR,
				filePath = downloadPath + File.separator + fileName;

		File file = new File(filePath);

		/*
		 * try {
		 * 
		 * Connection con = ConnectionDb.getConnection(); CallableStatement cstmt =
		 * (CallableStatement) con .prepareCall("{call sql12384133.delete_file('" +
		 * fileName + "')}"); cstmt.executeQuery();
		 * 
		 * } catch (Exception e) { System.out.println("SQL exception occured" + e); }
		 */

		FileUploadDao ob = new FileUploadDao();
		ob.deleteFile(fileName);

		file.delete();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/uploadedFilesServlet");
		dispatcher.forward(request, response);

	}
}