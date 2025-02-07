package com.kh.dev.join.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.dev.common.ConnectionPool;

public class UsersDAO {
	
	private static UsersDAO instance = new UsersDAO();

    private UsersDAO() {}

    public static UsersDAO getInstance() {
        return instance;
    }

    private final String INSERT_SQL = "insert into users values(?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SELECT_SQL = "SELECT * FROM users WHERE id = ? AND pwd = ?";
    private final String UPDATE_SQL = "UPDATE users SET name = ?, email = ?, phone1 = ?, phone2 = ?, phone3 = ?, gender = ? WHERE id = ?";

    // 회원 정보 저장
    public Boolean insertDB(UsersVO uvo) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.dbCon();
        PreparedStatement pstmt = null;
        int count = 0;

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
        } finally {
            cp.dbClose(con, pstmt);
        }
        return (count > 0) ? true : false;
    }

    // 로그인 검증
    public UsersVO authenticateUser(String id, String pwd) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.dbCon();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsersVO user = null;

        try {
            pstmt = con.prepareStatement(SELECT_SQL);
            pstmt.setString(1, id.trim().toLowerCase());
            pstmt.setString(2, pwd); 

            rs = pstmt.executeQuery();
            

            if (rs.next()) {
                user = new UsersVO();
                user.setId(rs.getString("id"));
                user.setPwd(rs.getString("pwd"));
                user.setName(rs.getString("name"));
                user.setYear(rs.getInt("year"));
                user.setMonth(rs.getInt("month"));
                user.setDay(rs.getInt("day"));
                user.setGender(rs.getString("gender"));
                user.setEmail(rs.getString("email"));
                user.setPhone1(rs.getString("phone1"));
                user.setPhone2(rs.getString("phone2"));
                user.setPhone3(rs.getString("phone3"));
                
                System.out.println("Executing SQL: " + SELECT_SQL);
                System.out.println("Parameters: id = " + id + ", pwd = " + pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt, rs);
        }

        return user;
    }
    
    public boolean updateUser(UsersVO uvo) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.dbCon();
        PreparedStatement pstmt = null;
        int count = 0;

        try {
            pstmt = con.prepareStatement(UPDATE_SQL);
            pstmt.setString(1, uvo.getName());
            pstmt.setString(2, uvo.getEmail());
            pstmt.setString(3, uvo.getPhone1());
            pstmt.setString(4, uvo.getPhone2());
            pstmt.setString(5, uvo.getPhone3());
            pstmt.setString(6, uvo.getGender());
            pstmt.setString(7, uvo.getId());

            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt);
        }

        return count > 0;
    }

}
