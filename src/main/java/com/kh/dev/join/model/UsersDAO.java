package com.kh.dev.join.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.dev.common.ConnectionPool;

public class UsersDAO {
	private final String INSERT_SQL = "insert into users values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public Boolean insertDB(UsersVO uvo) {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection con = cp.dbCon();
		PreparedStatement pstmt = null;
		int count = 0; 
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, uvo.getId());
			pstmt.setString(2, uvo.getPwd());
			pstmt.setString(3, uvo.getPwdconfirm());
			pstmt.setString(4, uvo.getName());
			pstmt.setInt(5, uvo.getYear());
			pstmt.setInt(6, uvo.getMonth());
			pstmt.setInt(7, uvo.getDay());
			pstmt.setString(8, uvo.getGender());
			pstmt.setString(9, uvo.getEmail());
			pstmt.setString(10, uvo.getPhone1());
			pstmt.setString(11, uvo.getPhone2());
			pstmt.setString(12, uvo.getPhone3());

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cp.dbClose(con, pstmt);
		}
		return (count > 0) ? true : false;
	}
}
