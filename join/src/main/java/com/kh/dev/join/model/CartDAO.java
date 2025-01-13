package com.kh.dev.join.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.dev.common.ConnectionPool;

public class CartDAO {

    /**
     * 데이터베이스에서 사용자 ID 가져오기
     */
    public int getUserIdByUsername(String username) throws SQLException {
        String query = "SELECT ID FROM USERS WHERE USERNAME = ?";
        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID");
                } else {
                    throw new SQLException("사용자를 찾을 수 없습니다: " + username);
                }
            }
        }
    }

    /**
     * 장바구니에 항목 추가
     */
    public void addToCart(int userId, int productId, int quantity) throws SQLException {
        String query = "INSERT INTO CART (USER_ID, PRODUCT_ID, QUANTITY) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        }
    }

    /**
     * 장바구니에서 특정 사용자 ID의 항목 조회
     */
    public List<CartVO> getCartItems(int userId) throws SQLException {
        List<CartVO> cartItems = new ArrayList<>();
        String query = "SELECT C.ID, C.USER_ID, C.PRODUCT_ID, C.QUANTITY, " +
                       "P.NAME AS PRODUCT_NAME, P.PRICE " +
                       "FROM CART C " +
                       "JOIN PRODUCTS P ON C.PRODUCT_ID = P.ID " +
                       "WHERE C.USER_ID = ?";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CartVO cart = new CartVO();
                    cart.setId(rs.getInt("ID"));
                    cart.setUserId(rs.getInt("USER_ID"));
                    cart.setProductId(rs.getInt("PRODUCT_ID"));
                    cart.setQuantity(rs.getInt("QUANTITY"));
                    cart.setProductName(rs.getString("PRODUCT_NAME"));
                    cart.setPrice(rs.getDouble("PRICE"));
                    cartItems.add(cart);
                }
            }
        }
        return cartItems;
    }

    /**
     * 장바구니 항목 삭제
     */
    public void removeFromCart(int userId, int productId) throws SQLException {
        String query = "DELETE FROM CART WHERE USER_ID = ? AND PRODUCT_ID = ?";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
        }
    }

    /**
     * 장바구니 항목 수량 업데이트
     */
    public void updateCartQuantity(int userId, int productId, int quantity) throws SQLException {
        String query = "UPDATE CART SET QUANTITY = ? WHERE USER_ID = ? AND PRODUCT_ID = ?";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, userId);
            pstmt.setInt(3, productId);
            pstmt.executeUpdate();
        }
    }

    /**
     * 특정 사용자의 장바구니 비우기
     */
    public void clearCart(int userId) throws SQLException {
        String query = "DELETE FROM CART WHERE USER_ID = ?";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
}

