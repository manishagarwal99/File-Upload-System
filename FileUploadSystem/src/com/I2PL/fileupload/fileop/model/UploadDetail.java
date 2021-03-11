package com.I2PL.fileupload.fileop.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UploadDetail implements Serializable {

	private long fileSize;
	private String fileName, uploadStatus;
	private int user_id;
	private int file_id;
	private String file_path;
	private String file_desciption, newfilename;
	private Timestamp upload_ts;

	private static final long serialVersionUID = 1L;

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;

	}

	public void setFileId(int file_id) {
		this.file_id = file_id;
	}

	public void setFilePath(String file_path) {
		this.file_path = file_path;
	}

	public void setFileDescription(String file_desciption) {
		this.file_desciption = file_desciption;
	}

	public void setTimestamp(Timestamp upload_ts) {
		this.upload_ts = upload_ts;
	}

	public int getUserid() {
		return user_id;
	}

	public int getFileid() {
		return file_id;
	}

	public String getFilepath() {
		return file_path;
	}

	public String getFiledesciption() {
		return file_desciption;
	}

	public Timestamp getTimestamp() {
		return upload_ts;
	}

	public void setNewFileName(String newfilename) {
		this.newfilename = newfilename;
	}

	public String getNewFileName() {
		return newfilename;
	}

}