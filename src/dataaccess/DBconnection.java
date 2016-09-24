
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Keith Levi
 * @since 14 Feb 2016 A very simple example of JDBC connection to a MySQL
 *        database. Uses same technique taught in the FPP course. Does not do
 *        any connection management or pooling, just opens and closes the
 *        connection for each request.
 */
public class DBconnection {

	public static Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/entries", "root", "");

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return con;
	}

	public void closeConnection(Connection con) throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}

}
