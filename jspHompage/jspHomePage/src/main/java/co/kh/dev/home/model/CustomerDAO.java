package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.kh.dev.common.ConnectionPool;

public class CustomerDAO {
	ConnectionPool cp = ConnectionPool.getInstance();
	private final String INSERT_SQL = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String SELECT_BY_ID_SQL = "SELECT COUNT(*) COUNT FROM CUSTOMER WHERE ID = ?";
	private final String SELECT_LOGIN_ID_CHECK_SQL = "SELECT * FROM Customer WHERE ID = ?";
	private final String SELECT_LOGIN_CHECK_SQL = "SELECT * FROM Customer WHERE ID = ? AND PWD = ?";
	private final String UPDATE_SQL = "UPDATE CUSTOMER SET PWD = ?, NAME = ?, NICKNAME = ?, EMAIL = ?, TEL = ?, PHONE = ?, BIRTH = ?, ZIPCODE = ?, ADDRESS1 = ?, ADDRESS2 = ? WHERE ID = ? AND PWD = ?";
	private final String DELETE_SQL = "DELETE FROM CUSTOMER WHERE ID=?";
	private static CustomerDAO cDAO;

	private CustomerDAO() {

	}

	public static CustomerDAO getInstance() {
		try {
			if (cDAO == null) {
				synchronized (CustomerDAO.class) {
					cDAO = new CustomerDAO();
				}
			}
		} catch (Exception e) {
		}
		return cDAO;
	}

	// 데이터 삽입 메서드
	public boolean insertDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, cvo.getId());
			pstmt.setString(2, cvo.getPwd());
			pstmt.setString(3, cvo.getName());
			pstmt.setString(4, cvo.getNickName());
			pstmt.setString(5, cvo.getEmail());
			pstmt.setString(6, cvo.getTel());
			pstmt.setString(7, cvo.getPhone());
			pstmt.setString(8, cvo.getBirth());
			pstmt.setString(9, cvo.getZipcode());
			pstmt.setString(10, cvo.getAddress1());
			pstmt.setString(11, cvo.getAddress2());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return (rs != 0) ? true : false; // 성공 여부 반환

	}

	// ID 중복 체크 메서드
	public boolean selectCountByIdDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
			pstmt.setString(1, cvo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}

		return (count == 0) ? true : false;
	}

	// ID 삭제 메서드
	public boolean deleteDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setString(1, cvo.getId());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}

		return (rs != 0) ? true : false;
	}

	public CustomerVO selectByIdDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_LOGIN_ID_CHECK_SQL);
			pstmt.setString(1, cvo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String nickName = rs.getString("nickName");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				String zipCode = rs.getString("zipCode");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				cvo = new CustomerVO(id, pwd, name, nickName, email, tel, phone, birth, zipCode, address1, address2);
			} else {
				cvo = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}

		return cvo;
	}

	// id, pwd를받아서 맞는 레코드를 출력
	public CustomerVO selectLoginCheckDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_LOGIN_CHECK_SQL);
			pstmt.setString(1, cvo.getId());
			pstmt.setString(2, cvo.getPwd());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String nickName = rs.getString("nickName");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				String phone = rs.getString("phone");
				String birth = rs.getString("birth");
				String zipCode = rs.getString("zipCode");
				String address1 = rs.getString("address1");
				String address2 = rs.getString("address2");
				cvo = new CustomerVO(id, pwd, name, nickName, email, tel, phone, birth, zipCode, address1, address2);
			} else {
				cvo = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}
		return cvo;
	}

	// 데이터 업데이트 메서드
	public boolean updateDB(CustomerVO cvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, cvo.getPwd());
			pstmt.setString(2, cvo.getName());
			pstmt.setString(3, cvo.getNickName());
			pstmt.setString(4, cvo.getEmail());
			pstmt.setString(5, cvo.getTel());
			pstmt.setString(6, cvo.getPhone());
			pstmt.setString(7, cvo.getBirth());
			pstmt.setString(8, cvo.getZipcode());
			pstmt.setString(9, cvo.getAddress1());
			pstmt.setString(10, cvo.getAddress2());
			pstmt.setString(11, cvo.getId());
			pstmt.setString(12, cvo.getPwd());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return (rs != 0) ? true : false; // 성공 여부 반환
	}

	//
}
