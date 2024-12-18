package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class CommentDAO {
	ConnectionPool cp = ConnectionPool.getInstance();
	private final String INSERT_SQL = "INSERT INTO B_COMMENT VALUES ((SELECT NVL(MAX(NO),0)+1 FROM B_COMMENT),?,?,?,?,?,sysdate)";
	private final String SELECT_SQL = "SELECT LEVEL,NO,  BOARD_NO, CUSTOMER_ID, PARENT_NO,CONTENT,DEPTH,SUBDATE FROM B_COMMENT WHERE BOARD_NO = ? START WITH PARENT_NO =0 CONNECT BY PRIOR NO = PARENT_NO ORDER SIBLINGS BY SUBDATE ASC";
	private final String SELECT_COUNT_BY_BOARD_NO_SQL = "SELECT COUNT(*) COUNT FROM B_COMMENT WHERE BOARD_NO = ?";
	private final String SELECT_LOGIN_CHECK_SQL = "SELECT * FROM Customer WHERE ID = ? AND PWD = ?";
	private final String UPDATE_SQL = "UPDATE CUSTOMER SET PWD = ?, NAME = ?, NICKNAME = ?, EMAIL = ?, TEL = ?, PHONE = ?, BIRTH = ?, ZIPCODE = ?, ADDRESS1 = ?, ADDRESS2 = ? WHERE ID = ? AND PWD = ?";
	private final String DELETE_SQL = "DELETE FROM CUSTOMER WHERE ID=?";
	private static CommentDAO cDAO;

	private CommentDAO() {

	}

	public static CommentDAO getInstance() {
		try {
			if (cDAO == null) {
				synchronized (CommentDAO.class) {
					cDAO = new CommentDAO();
				}
			}
		} catch (Exception e) {
		}
		return cDAO;
	}

	// 데이터 삽입 메서드
	public boolean insertDB(CommentVO cmvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, cmvo.getCustomerId());
			pstmt.setInt(2, cmvo.getBoardNo());
			pstmt.setInt(3, cmvo.getParentNo());
			pstmt.setInt(4, cmvo.getDepth());
			pstmt.setString(5, cmvo.getContent());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return (rs != 0) ? true : false; // 성공 여부 반환

	}

	// bvo를 받아서 해당 no에 맞는 comment반환
	public ArrayList<CommentVO> selectByBoardNoDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommentVO> cList = new ArrayList<CommentVO>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, bvo.getNo());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String customerId = rs.getString("CUSTOMER_ID");
				int boardNO = rs.getInt("BOARD_NO");
				int parentNo = rs.getInt("PARENT_NO");
				int depth = rs.getInt("DEPTH");
				String content = rs.getString("CONTENT");
				Date subdate = rs.getDate("SUBDATE");
				cList.add(new CommentVO(no, customerId, boardNO, parentNo, depth, content, subdate));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}
		return cList;
	}

	// bvo를 받아서 해당 no에 맞는 comment개수
	public int selectCountByBoardNoDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_COUNT_BY_BOARD_NO_SQL);
			pstmt.setInt(1, bvo.getNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}
		return count;
	}

//	// ID 삭제 메서드
//	public boolean deleteDB(CommentVO cmvo) {
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = null;
//		int rs = 0;
//		try {
//			pstmt = con.prepareStatement(DELETE_SQL);
//			pstmt.setString(1, cmvo.getId());
//			rs = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			cp.dbClose(con, pstmt);
//		}
//
//		return (rs != 0) ? true : false;
//	}

//	public CustomerVO selectByIdDB(CommentVO cmvo) {
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = con.prepareStatement(SELECT_LOGIN_ID_CHECK_SQL);
//			pstmt.setString(1, cmvo.getId());
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				String id = rs.getString("id");
//				String pwd = rs.getString("pwd");
//				String name = rs.getString("name");
//				String nickName = rs.getString("nickName");
//				String email = rs.getString("email");
//				String tel = rs.getString("tel");
//				String phone = rs.getString("phone");
//				String birth = rs.getString("birth");
//				String zipCode = rs.getString("zipCode");
//				String address1 = rs.getString("address1");
//				String address2 = rs.getString("address2");
//				cmvo = new CustomerVO(id, pwd, name, nickName, email, tel, phone, birth, zipCode, address1, address2);
//			} else {
//				cmvo = null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			cp.dbClose(con, rs, pstmt);
//		}
//
//		return cmvo;
//	}

	// id, pwd를받아서 맞는 레코드를 출력
//	public CustomerVO selectLoginCheckDB(CommentVO cmvo) {
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			pstmt = con.prepareStatement(SELECT_LOGIN_CHECK_SQL);
//			pstmt.setString(1, cmvo.getId());
//			pstmt.setString(2, cmvo.getPwd());
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				String id = rs.getString("id");
//				String pwd = rs.getString("pwd");
//				String name = rs.getString("name");
//				String nickName = rs.getString("nickName");
//				String email = rs.getString("email");
//				String tel = rs.getString("tel");
//				String phone = rs.getString("phone");
//				String birth = rs.getString("birth");
//				String zipCode = rs.getString("zipCode");
//				String address1 = rs.getString("address1");
//				String address2 = rs.getString("address2");
//				cmvo = new CustomerVO(id, pwd, name, nickName, email, tel, phone, birth, zipCode, address1, address2);
//			} else {
//				cmvo = null;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			cp.dbClose(con, rs, pstmt);
//		}
//		return cmvo;
//	}
//
//	// 데이터 업데이트 메서드
//	public boolean updateDB(CommentVO cmvo) {
//		Connection con = cp.getConnection();
//		PreparedStatement pstmt = null;
//		int rs = 0;
//		try {
//			pstmt = con.prepareStatement(UPDATE_SQL);
//			pstmt.setString(1, cmvo.getPwd());
//			pstmt.setString(2, cmvo.getName());
//			pstmt.setString(3, cmvo.getNickName());
//			pstmt.setString(4, cmvo.getEmail());
//			pstmt.setString(5, cmvo.getTel());
//			pstmt.setString(6, cmvo.getPhone());
//			pstmt.setString(7, cmvo.getBirth());
//			pstmt.setString(8, cmvo.getZipcode());
//			pstmt.setString(9, cmvo.getAddress1());
//			pstmt.setString(10, cmvo.getAddress2());
//			pstmt.setString(11, cmvo.getId());
//			pstmt.setString(12, cmvo.getPwd());
//			rs = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			cp.dbClose(con, pstmt);
//		}
//		return (rs != 0) ? true : false; // 성공 여부 반환
//	}

	//
}
