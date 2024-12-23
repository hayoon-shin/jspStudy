package co.kh.dev.home.zipcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;


public class ZipCodeDAO {
	private final String SELECT_SQL = "SELECT * FROM ZipCode ORDER BY NO";
	private final String SELECT_BY_DONG_SQL = "select * from zipcode where dong like '%'||?||'%'";
	private final String INSERT_SQL = "INSERT INTO ZipCode VALUES((SELECT NVL(MAX(NO),0)+1 FROM ZipCode),?,?,?,SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM ZipCode WHERE NO = ?";
	private final String UPDATE_SQL = "UPDATE ZipCode SET NAME= ? , PWD = ? WHERE ID = ?";
	ConnectionPool cp = ConnectionPool.getInstance();

	// 전체를 DB에서 출력
	/*
	 * public ArrayList<ZipCodeVO> selectDB() { Connection con = cp.getConnection();
	 * PreparedStatement pstmt = null; ResultSet rs = null; ArrayList<ZipCodeVO>
	 * mList = new ArrayList<ZipCodeVO>(); try { pstmt =
	 * con.prepareStatement(SELECT_SQL); rs = pstmt.executeQuery(); while
	 * (rs.next()) { int no = rs.getInt("NO"); String name = rs.getString("NAME");
	 * String id = rs.getString("ID"); String pwd = rs.getString("PWD"); Date
	 * regdate = rs.getDate("REGDATE"); ZipCodeVO svo = new ZipCodeVO();
	 * mList.add(svo); } } catch (SQLException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } cp.dbClose(con, rs, pstmt); return mList; }
	 */
	// 아이디를 받아서 아이디에 맞는 레코드 출력
	public ArrayList<ZipCodeVO> selectByDongDB(ZipCodeVO zvo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ZipCodeVO> zList = new ArrayList<ZipCodeVO>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_DONG_SQL);
			pstmt.setString(1, (zvo.getDong()));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String zipCode = rs.getString("ZIPCODE");
				String sido = rs.getString("SIDO");
				String gugun = rs.getString("GUGUN");
				String dong = rs.getString("DONG");
				String bunji = rs.getString("BUNJI");
				ZipCodeVO vo = new ZipCodeVO(zipCode, sido, gugun, dong, bunji);
				zList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}
		return zList;
	}

	/*
	 * // id, pwd를받아서 맞는 레코드를 출력 public ZipCodeVO selectLoginCheckDB(ZipCodeVO svo)
	 * { Connection con = DBUtility.dbCon(); PreparedStatement pstmt = null;
	 * ResultSet rs = null; try { pstmt =
	 * con.prepareStatement(SELECT_LOGIN_CHECK_SQL); rs = pstmt.executeQuery(); if
	 * (rs.next()) { int no = rs.getInt("NO"); String name = rs.getString("NAME");
	 * String id = rs.getString("ID"); String pwd = rs.getString("PWD"); Date
	 * regdate = rs.getDate("REGDATE"); svo = new ZipCodeVO(); } else { svo = null;
	 * } } catch (SQLException e) { e.printStackTrace(); } DBUtility.dbClose(con,
	 * rs, pstmt); return svo; }
	 * 
	 * public Boolean insertDB(ZipCodeVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(INSERT_SQL); rs = pstmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } DBUtility.dbClose(con, pstmt);
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean deleteDB(ZipCodeVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(DELETE_SQL); rs = pstmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } DBUtility.dbClose(con, pstmt);
	 * return (rs == 0) ? false : true; }
	 * 
	 * public Boolean updateDB(ZipCodeVO svo) { Connection con = DBUtility.dbCon();
	 * PreparedStatement pstmt = null; int rs = 0; try { pstmt =
	 * con.prepareStatement(UPDATE_SQL); rs = pstmt.executeUpdate(); } catch
	 * (SQLException e) { e.printStackTrace(); } DBUtility.dbClose(con, pstmt);
	 * return (rs == 0) ? false : true; }
	 */
}
