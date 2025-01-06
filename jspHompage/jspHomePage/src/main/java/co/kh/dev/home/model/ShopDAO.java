package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class ShopDAO {
	private static ShopDAO sDAO;
	private ConnectionPool cp = ConnectionPool.getInstance();

	// SQL 쿼리문 정의
	private final String SELECT_SQL = "SELECT \r\n" + "    RNUM, \r\n"
			+ "    NO, TYPE, PRODUCT_NO, NAME, PRICE, AMOUNT, TITLE, CONTENT, TITLEURL, SUBDATE from(\r\n"
			+ "select  ROWNUM RNUM, NO, TYPE, PRODUCT_NO, NAME, PRICE, AMOUNT,TITLE, CONTENT, TITLEURL, SUBDATE FROM (\r\n"
			+ "    SELECT \r\n" + "        s.NO, s.TYPE, s.PRODUCT_NO, p.NAME, p.PRICE, p.AMOUNT, \r\n"
			+ "        s.TITLE, s.CONTENT, i.URL AS TITLEURL, s.SUBDATE \r\n" + "    FROM (\r\n"
			+ "        SELECT * \r\n" + "        FROM SHOP \r\n" + "        WHERE TYPE = ? \r\n"
			+ "        ORDER BY NO DESC\r\n" + "    ) s \r\n" + "    LEFT JOIN PRODUCT p ON s.PRODUCT_NO = p.NO \r\n"
			+ "    LEFT JOIN (\r\n" + "        SELECT URL, SHOP_NO \r\n" + "        FROM SHOP_IMG \r\n"
			+ "        WHERE TYPE = 'TITLE'\r\n" + "    ) i ON s.NO = i.SHOP_NO\r\n" + "    order by s.no\r\n"
			+ ") \r\n" + ")\r\n" + "WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_BY_TITLE_SQL = "SELECT \r\n" + "    RNUM, \r\n"
			+ "    NO, TYPE, PRODUCT_NO, NAME, PRICE, AMOUNT, TITLE, CONTENT, TITLEURL, SUBDATE from(\r\n"
			+ "select  ROWNUM RNUM, NO, TYPE, PRODUCT_NO, NAME, PRICE, AMOUNT,TITLE, CONTENT, TITLEURL, SUBDATE FROM (\r\n"
			+ "    SELECT \r\n" + "        s.NO, s.TYPE, s.PRODUCT_NO, p.NAME, p.PRICE, p.AMOUNT, \r\n"
			+ "        s.TITLE, s.CONTENT, i.URL AS TITLEURL, s.SUBDATE \r\n" + "    FROM (\r\n"
			+ "        SELECT * \r\n" + "        FROM SHOP \r\n"
			+ "        WHERE TYPE = ? AND TITLE LIKE '%'||?||'%'\r\n" + "        ORDER BY NO DESC\r\n" + "    ) s \r\n"
			+ "    LEFT JOIN PRODUCT p ON s.PRODUCT_NO = p.NO \r\n" + "    LEFT JOIN (\r\n"
			+ "        SELECT URL, SHOP_NO \r\n" + "        FROM SHOP_IMG \r\n" + "        WHERE TYPE = 'TITLE'\r\n"
			+ "    ) i ON s.NO = i.SHOP_NO\r\n" + "    order by s.no\r\n" + ") \r\n" + ")\r\n"
			+ "WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_LIST_SQL = "SELECT * FROM (SELECT ROWNUM AS RNUM , s.NO ,s.type,s.PRODUCT_NO,p.name,p.price,p.amount,s.TITLE,s.CONTENT,url as titleurl,s.SUBDATE FROM SHOP s left join\r\n"
			+ "product p\r\n" + "on s.product_no=p.no\r\n" + "left join\r\n"
			+ "(select url,shop_no from SHOP_IMG where type='TITLE') i\r\n" + "on s.no=i.shop_no\r\n"
			+ "ORDER BY S.SUBDATE) WHERE no = ?  ORDER BY RNUM DESC";
	private final String SELECT_LAST_NO_SQL = "SELECT * FROM (SELECT * FROM SHOP_RNUM ORDER BY NO DESC) WHERE ROWNUM=1 ";
	private final String SELECT_RECORD_SQL = "SELECT COUNT(*) COUNT FROM SHOP WHERE TYPE=?";
	private final String SELECT_RECORD_BY_TITLE_SQL = "SELECT COUNT(*) COUNT FROM SHOP WHERE TYPE=? AND TITLE LIKE '%'||?||'%'";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM SHOP_RNUM WHERE NO = ?";
	private final String INSERT_SQL = "INSERT INTO SHOP VALUES((SELECT NVL(MAX(NO), 0) + 1 FROM SHOP), ?, ?, ?, ?, SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM SHOP WHERE NO = ?";

	private ShopDAO() {
	}

	public static ShopDAO getInstance() {
		if (sDAO == null) {
			synchronized (ShopDAO.class) {
				if (sDAO == null) {
					sDAO = new ShopDAO();
				}
			}
		}
		return sDAO;
	}

	public boolean insertDB(ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setInt(1, svo.getType());
			pstmt.setInt(2, svo.getProductNo());
			pstmt.setString(3, svo.getTitle());
			pstmt.setString(4, svo.getContent());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return rs != 0;
	}

	public ArrayList<ShopVO> selectDB(int startListNum, int endListNum, ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ShopVO> sList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, svo.getType());
			pstmt.setInt(2, startListNum);
			pstmt.setInt(3, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				int type = rs.getInt("TYPE");
				int productNo = rs.getInt("PRODUCT_NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String titleUrl = rs.getString("TITLEURL");
				Date subdate = rs.getDate("SUBDATE");
				svo = new ShopVO(rownum, no, type, productNo, name, price, amount, title, content, titleUrl, subdate);
				sList.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return sList;
	}

	public ShopVO selectListDB(ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_LIST_SQL);
			pstmt.setInt(1, svo.getNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				int type = rs.getInt("TYPE");
				int productNo = rs.getInt("PRODUCT_NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String titleUrl = rs.getString("TITLEURL");
				Date subdate = rs.getDate("SUBDATE");
				svo = new ShopVO(rownum, no, type, productNo, name, price, amount, title, content, titleUrl, subdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return svo;
	}

	public ShopVO selectLastDB() {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopVO svo = new ShopVO();
		try {
			pstmt = con.prepareStatement(SELECT_LAST_NO_SQL);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				int type = rs.getInt("TYPE");
				int productNo = rs.getInt("PRODUCT_NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String titleUrl = rs.getString("TITLEURL");
				Date subdate = rs.getDate("SUBDATE");
				svo = new ShopVO(rownum, no, type, productNo, name, price, amount, title, content, titleUrl, subdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return svo;
	}

	public ArrayList<ShopVO> selectByTitleDB(String findText, int startListNum, int endListNum, ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ShopVO> sList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(SELECT_BY_TITLE_SQL);
			pstmt.setInt(1, svo.getType());
			pstmt.setString(2, findText);
			pstmt.setInt(3, startListNum);
			pstmt.setInt(4, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				int type = rs.getInt("TYPE");
				int productNo = rs.getInt("PRODUCT_NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String titleUrl = rs.getString("TITLEURL");
				Date subdate = rs.getDate("SUBDATE");
				svo = new ShopVO(rownum, no, type, productNo, name, price, amount, title, content, titleUrl, subdate);
				sList.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return sList;
	}

	public ShopVO selectByNoDB(ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
			pstmt.setInt(1, svo.getNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				int type = rs.getInt("TYPE");
				int productNo = rs.getInt("PRODUCT_NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				String titleUrl = rs.getString("TITLEURL");
				Date subdate = rs.getDate("SUBDATE");
				svo = new ShopVO(rownum, no, type, productNo, name, price, amount, title, content, titleUrl, subdate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, rs, pstmt);
		}
		return svo;
	}

	public int selectRecordDB(ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_RECORD_SQL);
			pstmt.setInt(1, svo.getType());
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

	public int selectRecordByTitleDB(String findText, ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement(SELECT_RECORD_BY_TITLE_SQL);
			pstmt.setInt(1, svo.getType());
			pstmt.setString(2, findText);
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

	public boolean deleteDB(ShopVO svo) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		int rs = 0;
		try {
			pstmt = con.prepareStatement(DELETE_SQL);
			pstmt.setInt(1, svo.getNo());
			rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cp.dbClose(con, pstmt);
		}
		return rs != 0;
	}
}
