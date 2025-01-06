package co.kh.dev.home.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class ProductDAO {
 private static ProductDAO pDAO;
 private ConnectionPool cp = ConnectionPool.getInstance();
	private final String SELECT_SQL = "SELECT * FROM PRODUCT_RNUM WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_ALL_SQL = "SELECT * FROM PRODUCT_RNUM ORDER BY RNUM DESC";
	private final String SELECT_RECORD_SQL = "SELECT COUNT(*) COUNT FROM PRODUCT_RNUM";
	private final String SELECT_BY_TITLE_SQL = "SELECT * FROM (select rownum as rnum, no, NAME, PRICE, AMOUNT,subdate from PRODUCT WHERE NAME LIKE '%'||?||'%' order by subdate ) WHERE RNUM BETWEEN ? AND ? ORDER BY RNUM DESC";
	private final String SELECT_BY_TITLE_RECORD_SQL = "SELECT COUNT(*) COUNT FROM PRODUCT_RNUM WHERE NAME LIKE '%'||?||'%'";
	private final String SELECT_BY_NO_SQL = "SELECT * FROM PRODUCT_RNUM WHERE NO=?";
//	private final String UPDATE_COUNT_SQL = "UPDATE PRODUCT SET COUNT = ? WHERE NO = ?";
	private final String UPDATE_SQL = "UPDATE PRODUCT SET NAME = ?, PRICE=?, AMOUNT = ? WHERE NO = ?";
	private final String INSERT_SQL = "INSERT INTO PRODUCT VALUES((SELECT NVL(MAX(NO),0)+1 FROM PRODUCT),?,?,?,SYSDATE)";
	private final String DELETE_SQL = "DELETE FROM PRODUCT WHERE NO = ?";

 private ProductDAO() {}

 public static ProductDAO getInstance() {
     if (pDAO == null) {
         synchronized (ProductDAO.class) {
             if (pDAO == null) {
                 pDAO = new ProductDAO();
             }
         }
     }
     return pDAO;
 }

 public boolean insertDB(ProductVO pvo) {
     Connection con = cp.getConnection();
     PreparedStatement pstmt = null;
     int rs = 0;
     try {
         pstmt = con.prepareStatement(INSERT_SQL);
         pstmt.setString(1, pvo.getName());
         pstmt.setInt(2, pvo.getPrice());
         pstmt.setInt(3, pvo.getAmount());
         rs = pstmt.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         cp.dbClose(con, pstmt);
     }
     return rs != 0;
 }

 public ArrayList<ProductVO> selectAllDB() {
	 Connection con = cp.getConnection();
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 ArrayList<ProductVO> pList = new ArrayList<ProductVO>();
	 try {
		 pstmt = con.prepareStatement(SELECT_ALL_SQL);
		 rs = pstmt.executeQuery();
		 while (rs.next()) {
			 int rownum = rs.getInt("RNUM");
			 int no = rs.getInt("NO");
			 String name = rs.getString("NAME");
			 int price = rs.getInt("PRICE");
			 int amount = rs.getInt("AMOUNT");
			 Date subdate = rs.getDate("SUBDATE");
			 ProductVO pvo = new ProductVO(rownum, no, name, price, amount, subdate);
			 pList.add(pvo);
		 }
	 } catch (SQLException e) {
		 e.printStackTrace();
	 }
	 cp.dbClose(con, rs, pstmt);
	 return pList;
 }
	public ArrayList<ProductVO> selectDB(int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> pList = new ArrayList<ProductVO>();
		try {
			pstmt = con.prepareStatement(SELECT_SQL);
			pstmt.setInt(1, startListNum);
			pstmt.setInt(2, endListNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				Date subdate = rs.getDate("SUBDATE");
				ProductVO pvo = new ProductVO(rownum, no, name, price, amount, subdate);
				pList.add(pvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return pList;
	}
 
	// 제목을 입력받아서 비슷한 내용들을출력
	public ArrayList<ProductVO> selectByTitleDB(String findText, int startListNum, int endListNum) {
		Connection con = cp.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> pList = new ArrayList<ProductVO>();
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
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				Date subdate = rs.getDate("SUBDATE");
				ProductVO pvo2 = new ProductVO(rownum, no, name, price, amount, subdate);
				pList.add(pvo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cp.dbClose(con, rs, pstmt);
		return pList;
	}

 public ProductVO selectByNoDB(ProductVO pvo) {
     Connection con = cp.getConnection();
     PreparedStatement pstmt = null;
     ResultSet rs = null;
     try {
         pstmt = con.prepareStatement(SELECT_BY_NO_SQL);
         pstmt.setInt(1, pvo.getNo());
         rs = pstmt.executeQuery();
         if (rs.next()) {
        		int rownum = rs.getInt("RNUM");
				int no = rs.getInt("NO");
				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int amount = rs.getInt("AMOUNT");
				Date subdate = rs.getDate("SUBDATE");
				pvo = new ProductVO(rownum, no, name, price, amount, subdate);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         cp.dbClose(con, rs, pstmt);
     }
     return pvo;
 }

	// 레코드 개수를 출력
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
 
 public boolean updateDB(ProductVO pvo) {
     Connection con = cp.getConnection();
     PreparedStatement pstmt = null;
     int rs = 0;
     try {
         pstmt = con.prepareStatement(UPDATE_SQL);
         pstmt.setString(1, pvo.getName());
         pstmt.setInt(2, pvo.getPrice());
         pstmt.setInt(3, pvo.getAmount());
         pstmt.setInt(4, pvo.getNo());
         rs = pstmt.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         cp.dbClose(con, pstmt);
     }
     return rs != 0;
 }

 public boolean deleteDB(ProductVO pvo) {
     Connection con = cp.getConnection();
     PreparedStatement pstmt = null;
     int rs = 0;
     try {
         pstmt = con.prepareStatement(DELETE_SQL);
         pstmt.setInt(1, pvo.getNo());
         rs = pstmt.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     } finally {
         cp.dbClose(con, pstmt);
     }
     return rs != 0;
 }
}

