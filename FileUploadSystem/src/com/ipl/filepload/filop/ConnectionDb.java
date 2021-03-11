package com.ipl.filepload.filop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * To store the static final variables common to many servlet and java file.
 */

public class ConnectionDb {

	/**
	 * The credentials for connecting to the live database.
	 */

	private final static String DB_URL = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12384133";
	private final static String DB_NAME = "sql12384133";
	private final static String DB_PASSWORD = "14CHZGZpHN";
	private final static String DB_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * To set the driver for database connection.
	 *
	 *
	 * @param DB_DRIVER Driver details.
	 * 
	 * @return Nothing.
	 * 
	 * @exception ClassNotFoundException when the requested class is not found.
	 *
	 */

	/**
	 * To connect to the database.
	 * 
	 * @return Connection object.
	 * 
	 * @exception SQLException if the request for the connection to database could
	 *                         not be handled.
	 */

	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);

	}

}
