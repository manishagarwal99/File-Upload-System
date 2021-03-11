package com.I2PL.fileupload.fileop.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.I2PL.fileupload.fileop.compress.EncryptionCompression;
import com.I2PL.fileupload.fileop.dao.FileUploadDao;
import com.I2PL.fileupload.fileop.model.UploadDetail;

@WebServlet(description = "Upload File To The Server", urlPatterns = { "/fileUploadServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
		* 1000)
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIR = "uploadedFiles";
	public static int fileId = 1;
	@SuppressWarnings("unused")
	private File file;

	/*****
	 * This Method Is Called By The Servlet Container To Process A 'POST' Request
	 *****/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/***** Get The Absolute Path Of The Web Application *****/
		String applicationPath = getServletContext().getRealPath(""),
				uploadPath = applicationPath + File.separator + UPLOAD_DIR;
		
		String relative_uploadPath = request.getContextPath()+ File.separator + File.separator + UPLOAD_DIR;
		File fileUploadDirectorytoSave = new File(relative_uploadPath);
		String relative_path_name = fileUploadDirectorytoSave.toString();

		File fileUploadDirectory = new File(uploadPath);
		if (!fileUploadDirectory.exists()) {
			fileUploadDirectory.mkdirs();
		}

		String fileName = "";
		UploadDetail details = null;

		for (Part part : request.getParts()) {

			fileName = extractFileName(part);
			if (fileName == null || fileName.equals("")) {
				continue;
			}

			file = new File(fileName);

			details = new UploadDetail();
			details.setFileName(fileName);
			details.setFileSize(part.getSize() / 1024);

			try {
				part.write(uploadPath + File.separator + fileName);
				details.setUploadStatus("Success");
			} catch (IOException ioObj) {
				details.setUploadStatus("Failure : " + ioObj.getMessage());
			}

			int user_id = Integer.parseInt(request.getParameter("staticId"));
			details.setUserId(user_id);

			EncryptionCompression ob = new EncryptionCompression();
			File n1 = new File(uploadPath + File.separator + fileName);

			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			Timestamp upload_ts = new Timestamp(now.getTime());
			details.setTimestamp(upload_ts);

			Long upload_milli = upload_ts.getTime();
			String newfilename = String.valueOf(upload_milli) + "_" + String.valueOf(user_id) + ".txt";
			details.setNewFileName(newfilename);
			File n2 = new File(uploadPath + File.separator + newfilename);

			ob.compressFile(n1, n2);
			ob.encrypt(n2);
			n1.delete();

			int file_id = fileId++;
			details.setFileId(file_id);

			String relative_file_path = File.separator + relative_path_name + File.separator + File.separator + fileName;
			details.setFilePath(relative_file_path);

			String file_desciption = request.getParameter("description");
			details.setFileDescription(file_desciption);


			FileUploadDao applications = new FileUploadDao();
			applications.insert(details);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/fileChoose.jsp");
		dispatcher.forward(request, response);

	}

	/***** Helper Method #1 - This Method Is Used To Read The File Names *****/
	private String extractFileName(Part part) {
		String fileName = "", contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}
}