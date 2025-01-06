package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class NoticeDAO {
	ConnectionPool cp = ConnectionPool.getInstance();
	private final String SELECT_SQL = "SELECT * FROM NOTICE_RNUM WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_RECORD_SQL = "SELECT COUNT(*) COUNT FROM NOTICE_RNUM";
	private final String SELECT_BY_TITLE_SQL = "SELECT * FROM (select rownum as rnum, no, title, content, count,subdate from notice WHERE TITLE LIKE '%'||?||'%' order by subdate ) WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_BY_TITLE_RECORD_SQL = "SELECT COUNT(*) COUNT FROM NOTICE_RNUM WHERE TITLE LIKE '%'||?||'%'";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM NOTICE_RNUM WHERE NO=?";
	private final String UPDATE_COUNT_SQL = "UPDATE NOTICE SET COUNT = ? WHERE NO = ?";
	private final String UPDATE_SQL = "UPDATE NOTICE SET TITLE = ?, CONTENT = ? WHERE NO = ?";
	private final String INSERT_SQL = "INSERT INTO NOTICE VALUES((SELECT NVL(MAX(NO),0)+1 FROM NOTICE),?,?,0,SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM NOTICE WHERE NO = ?";
	private static NoticeDAO bDAO = null;

		
	private NoticeDAO() {
		super();
	}

	public static NoticeDAO getInstance() {
		if (bDAO == null) {
			synchronized (NoticeDAO.class) {
				bDAO = new NoticeDAO();
			}
		}
		return bDAO;
	}

	// 전체를 DB에서 출력
	public ArrayList<NoticeVO> selectDB(int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeVO> mList = new ArrayList<NoticeVO>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, startListNum);
			pstmt.setInt(2, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				NoticeVO mvo = new NoticeVO(rownum, no, title, content, count, subdate);
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
	public ArrayList<NoticeVO> selectByTitleDB(String findText, int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeVO> mList = new ArrayList<NoticeVO>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_TITLE_SQL);
			pstmt.setString(1, findText);
			pstmt.setInt(2, startListNum);
			pstmt.setInt(3, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				NoticeVO mvo2 = new NoticeVO(rownum, no, title, content, count, subdate);
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

	// ROWNUM 입력받아서 내용들을출력
	public NoticeVO selectByNoDB(NoticeVO nvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
			pstmt.setInt(1, nvo.getNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int count = rs.getInt("COUNT");
				Date subdate = rs.getDate("SUBDATE");
				nvo = new NoticeVO(rownum, no, title, content, count, subdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return nvo;
	}

	public Boolean insertDB(NoticeVO nvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, nvo.getTitle());
			pstmt.setString(2, nvo.getContent());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean deleteDB(NoticeVO nvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, nvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean updateDB(NoticeVO nvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(UPDATE_COUNT_SQL);
			pstmt.setInt(1, nvo.getCount());
			pstmt.setInt(2, nvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}

	public Boolean updateTCDB(NoticeVO nvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, nvo.getTitle());
			pstmt.setString(2, nvo.getContent());
			pstmt.setInt(3, nvo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, pstmt);
		return (rs == 0) ? false : true;
	}
}
