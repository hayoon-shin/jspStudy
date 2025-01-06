package co.kh.dev.home.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.kh.dev.common.ConnectionPool;

public class CartDAO {
    ConnectionPool cp = ConnectionPool.getInstance();

    // SQL 쿼리문
    private final String INSERT_SQL = "INSERT INTO CART (NO, CUSTOMER_ID, SHOP_NO, QT) VALUES ((SELECT NVL(MAX(NO), 0) + 1 FROM CART), ?, ?, ?)";
    private final String SELECT_SQL = "SELECT * FROM CART_JOIN WHERE CUSTOMER_ID = ?";
    private final String UPDATE_SQL = "UPDATE CART SET QT = ? WHERE NO = ?";
    private final String DELETE_SQL = "DELETE FROM CART WHERE NO = ?";
    
    private static CartDAO cDAO;

    private CartDAO() {}

    public static CartDAO getInstance() {
        try {
            if (cDAO == null) {
                synchronized (CartDAO.class) {
                    cDAO = new CartDAO();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cDAO;
    }

    // 데이터 삽입 메서드
    public boolean insertCart(CartVO cvo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, cvo.getCustomerId());
            pstmt.setInt(2, cvo.getShopNo());
            pstmt.setInt(3, cvo.getQt());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt);
        }
        return rs != 0; // 성공 여부 반환
    }

    // 고객 ID로 카트 조회
    public ArrayList<CartVO> selectCartByCustomerId(CartVO ctvo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<CartVO> cartList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(SELECT_SQL);
            pstmt.setString(1, ctvo.getCustomerId());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("NO");
                int shopNo = rs.getInt("SHOP_NO");
                int price = rs.getInt("price");
                String title = rs.getString("TITLE");
                int qt = rs.getInt("QT");
                cartList.add(new CartVO(no, ctvo.getCustomerId(), title, price, shopNo, qt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, rs, pstmt);
        }
        return cartList;
    }

    // 카트 항목 업데이트
    public boolean updateDB(CartVO ctvo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(UPDATE_SQL);
            pstmt.setInt(1, ctvo.getQt());
            pstmt.setInt(2, ctvo.getNo());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt);
        }
        return rs != 0;
    }

    // 카트 항목 삭제
    public boolean deleteDB(CartVO ctvo) {
        Connection con = cp.getConnection();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = con.prepareStatement(DELETE_SQL);
            pstmt.setInt(1, ctvo.getNo());
            rs = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.dbClose(con, pstmt);
        }
        return rs != 0;
    }
}
