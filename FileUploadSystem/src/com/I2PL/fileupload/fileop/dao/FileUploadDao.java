package com.I2PL.fileupload.fileop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.I2PL.fileupload.fileop.model.UploadDetail;
import com.ipl.filepload.filop.ConnectionDb;

public class FileUploadDao {

	/**
	 * To insert the Loan application details into the live database .
	 *
	 * @param applicant an Applicant class object containing the application
	 *                  details.
	 * 
	 * @return a string value to notify if the insertion was successful or not.
	 * 
	 * @exception SQLException if the request for the connection to database could
	 *                         not be handled.
	 *
	 */

	public String insert(UploadDetail applicant) {

		Connection con = null;
		CallableStatement cstmt = null;

		int user_id = applicant.getUserid();
		String file_path = applicant.getFilepath();
		Timestamp upload_ts = applicant.getTimestamp();
		String file_title = applicant.getFileName();
		String file_desciption = applicant.getFiledesciption();
		String newfile_title = applicant.getNewFileName();

		String result = "";
		try {
			con = ConnectionDb.getConnection();
			result = "Data entry successful!";

			cstmt = (CallableStatement) con.prepareCall("{call ifezqbxe_i2pldb.insert_into_filetable(?,?,?,?,?,?)}");
			cstmt.setInt(1, user_id);
			cstmt.setString(2, file_path);
			cstmt.setTimestamp(3, upload_ts);
			cstmt.setString(4, file_title);
			cstmt.setString(5, file_desciption);
			cstmt.setString(6, newfile_title);

			cstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			result = "Data entry failed!";
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return result;
	}

	public void deleteFile(String fileName) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = ConnectionDb.getConnection();
			cstmt = (CallableStatement) con.prepareCall("{call ifezqbxe_i2pldb.delete_file(?)}");
			cstmt.setString(1, fileName);
			cstmt.executeQuery();

		} catch (Exception e) {
			System.out.println("SQL exception occured" + e);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public void editFile(String fileNameWithExtension, String fileDescription, String uniqueId) {

		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = ConnectionDb.getConnection();
			cstmt = (CallableStatement) con.prepareCall("{call ifezqbxe_i2pldb.edit_file(?,?,?)}");

			cstmt.setString(1, fileNameWithExtension);
			cstmt.setString(2, fileDescription);
			cstmt.setString(3, uniqueId);

			cstmt.executeQuery();

		} catch (Exception e) {
			System.out.println("SQL exception occured" + e);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public List<UploadDetail> showUploadedFile() {

		UploadDetail details = null;
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();

		Connection con = null;
		CallableStatement cstmt = null;

		try {

			con = ConnectionDb.getConnection();
			cstmt = (CallableStatement) con.prepareCall("{call ifezqbxe_i2pldb.list_all_files()}");
			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) {
				details = new UploadDetail();
				details.setFileName(rs.getString(5));
				details.setNewFileName(rs.getString(7));
				details.setTimestamp(rs.getTimestamp(4));
				details.setFileDescription(rs.getString(6));
				fileList.add(details);

			}
		} catch (Exception e) {
			System.out.println("SQL exception occured" + e);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return fileList;
	}

}
