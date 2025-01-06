package co.kh.dev.home.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class ShopImgDAO {
    private static ShopImgDAO instance = new ShopImgDAO();
    ConnectionPool cp = ConnectionPool.getInstance();

    private final String INSERT_SQL = "INSERT INTO SHOP_IMG (NO, TYPE, SHOP_NO, URL) VALUES ((SELECT NVL(MAX(NO), 0) + 1 FROM SHOP_IMG), ?, ?, ?)";
    private final String SELECT_TITLE_SQL = "SELECT * FROM SHOP_IMG ORDER BY NO DESC WHERE SHOP_NO = ? AND TYPE='TITLE'";
    private final String SELECT_CONTENT_SQL = "SELECT * FROM SHOP_IMG  WHERE SHOP_NO = ? AND TYPE= 'CONTENT' ORDER BY NO DESC";

    private ShopImgDAO() {}

    public static ShopImgDAO getInstance() {
        return instance;
    }

    public boolean insertDB(ShopImgVO pvo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, pvo.getType());
            pstmt.setInt(2, pvo.getShopNo());
            pstmt.setString(3, pvo.getUrl());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt);
        }
        return result > 0;
    }

    public ShopImgVO selectTitleDB(ShopImgVO sivo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SELECT_TITLE_SQL);
            pstmt.setInt(1, sivo.getShopNo());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                sivo = new ShopImgVO(
                    rs.getInt("NO"),
                    rs.getString("TYPE"),
                    rs.getInt("SHOP_NO"),
                    rs.getString("URL")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, rs, pstmt);
        }
        return sivo;
    }
    public ArrayList<ShopImgVO> selectContentDB(ShopImgVO sivo) {
    	Connection con = cp.getConnection();
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	ArrayList<ShopImgVO> siList = new ArrayList<>();
    	try {
    		pstmt = con.prepareStatement(SELECT_CONTENT_SQL);
    		 pstmt.setInt(1, sivo.getShopNo());
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			sivo = new ShopImgVO(
    					rs.getInt("NO"),
    					rs.getString("TYPE"),
    					rs.getInt("SHOP_NO"),
    					rs.getString("URL")
    					);
    			siList.add(sivo);
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		cp.dbClose(con, rs, pstmt);
    	}
    	return siList;
    }

}

