package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class BoardDAO {
	ConnectionPool cp = ConnectionPool.getInstance();
	private final String SELECT_SQL = "SELECT * FROM BOARD_J_CUSTOMER_ROWNUM A LEFT JOIN(SELECT BOARD_NO,COUNT(*) COMMENT_NUM FROM B_COMMENT GROUP BY BOARD_NO) B ON A.NO=B.BOARD_NO WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_RECORD_SQL = "SELECT COUNT(*) COUNT FROM BOARD_J_CUSTOMER_ROWNUM";
	private final String SELECT_BY_TITLE_SQL = "SELECT * FROM (SELECT ROWNUM AS RNUM,CUSTOMER_ID, NO, NAME,TITLE,CONTENT,COUNT,SUBDATE  FROM BOARD_J_CUSTOMER WHERE TITLE LIKE '%'||?||'%') A LEFT JOIN(SELECT BOARD_NO,COUNT(*) COMMENT_NUM FROM B_COMMENT GROUP BY BOARD_NO) B ON A.NO=B.BOARD_NO WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_BY_TITLE_RECORD_SQL = "SELECT COUNT(*) COUNT FROM BOARD_J_CUSTOMER_ROWNUM WHERE TITLE LIKE '%'||?||'%'";
	private final String SELECT_BY_ID_SQL = "SELECT * FROM (SELECT ROWNUM AS RNUM,CUSTOMER_ID, NO, NAME,TITLE,CONTENT,COUNT,SUBDATE  FROM BOARD_J_CUSTOMER WHERE CUSTOMER_ID LIKE '%'||?||'%') A LEFT JOIN(SELECT BOARD_NO,COUNT(*) COMMENT_NUM FROM B_COMMENT GROUP BY BOARD_NO) B ON A.NO=B.BOARD_NO WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_BY_ID_RECORD_SQL = "SELECT COUNT(*) COUNT FROM BOARD_J_CUSTOMER_ROWNUM WHERE CUSTOMER_ID LIKE '%'||?||'%'";
	private final String SELECT_BY_RNUM_SQL = "SELECT * FROM BOARD_J_CUSTOMER_ROWNUM WHERE NO=?";
	private final String UPDATE_SQL = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, COUNT = ? WHERE NO = ?";
	private final String UPDATE_TC_SQL = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
	private final String INSERT_SQL = "INSERT INTO Board VALUES((SELECT NVL(MAX(NO),0)+1 FROM Board),?,?,?,0,SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM Board WHERE NO = ?";
	private static BoardDAO bDAO = null;

	public static BoardDAO getInstance() {
		if (bDAO == null) {
			synchronized (BoardDAO.class) {
				bDAO = new BoardDAO();
			}
		}
		return bDAO;
	}

	// 전체를 DB에서 출력
	public ArrayList<BoardVO> selectDB(int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> mList = new ArrayList<BoardVO>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, startListNum);
			pstmt.setInt(2, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String customerId = rs.getString("CUSTOMER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				int commentNum = rs.getInt("COMMENT_NUM");
				BoardVO mvo = new BoardVO(rownum, no, customerId, name, title, content, count, subdate, commentNum);
				mList.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return mList;
	}

	// BOARD의 레코드 개수를 출력
	public int selectRecordDB() {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_RECORD_SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return count;
	}

	// 제목을 입력받아서 비슷한 내용들을출력
	public ArrayList<BoardVO> selectByTitleDB(String findText, int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> mList = new ArrayList<BoardVO>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_TITLE_SQL);
			pstmt.setString(1, findText);
			pstmt.setInt(2, startListNum);
			pstmt.setInt(3, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String customerId = rs.getString("CUSTOMER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				int commentNum = rs.getInt("COMMENT_NUM");
				BoardVO mvo2 = new BoardVO(rownum, no, customerId, name, title, content, count, subdate, commentNum);
				mList.add(mvo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return mList;
	}
	// 작성자를 입력받아서 비슷한 내용들을출력
	public ArrayList<BoardVO> selectByIdDB(String id, int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> mList = new ArrayList<BoardVO>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_ID_SQL);
			pstmt.setString(1, id);
			pstmt.setInt(2, startListNum);
			pstmt.setInt(3, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String customerId = rs.getString("CUSTOMER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				int commentNum = rs.getInt("COMMENT_NUM");
				BoardVO mvo2 = new BoardVO(rownum, no, customerId, name, title, content, count, subdate, commentNum);
				mList.add(mvo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return mList;
	}

	// 제목을 입력받아서 비슷한 내용의BOARD의 레코드 개수를 출력
	public int selectRecordByTitleDB(String findText) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_BY_TITLE_RECORD_SQL);
			pstmt.setString(1, findText);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return count;
	}
	// 제목을 입력받아서 비슷한 내용의BOARD의 레코드 개수를 출력
	public int selectRecordByIdDB(String id) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_BY_ID_RECORD_SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return count;
	}

	// ROWNUM 입력받아서 내용들을출력
	public BoardVO selectByNoDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_BY_RNUM_SQL);
			pstmt.setInt(1, bvo.getNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				String customerId = rs.getString("CUSTOMER_ID");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				bvo = new BoardVO(rownum, no, customerId, name, title, content, count, subdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return bvo;
	}

	public Boolean insertDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, bvo.getCustomerId());
			pstmt.setString(2, bvo.getTitle());
			pstmt.setString(3, bvo.getContent());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean deleteDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, bvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean updateDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContent());
			pstmt.setInt(3, bvo.getCount());
			pstmt.setInt(4, bvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean updateTCDB(BoardVO bvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(UPDATE_TC_SQL);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContent());
			pstmt.setInt(3, bvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}
}



















