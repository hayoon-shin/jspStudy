package com.kh.dev.join.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.dev.common.ConnectionPool;

public class ProductDAO {
    public List<ProductVO> getAllProducts() throws SQLException {
        List<ProductVO> products = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTS";
        System.out.println("실행되는 쿼리: " + query); // 디버깅 메시지
        
        try (Connection conn = ConnectionPool.getInstance().dbCon();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                ProductVO product = new ProductVO();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("NAME"));
                product.setDescription(rs.getString("DESCRIPTION"));
                product.setPrice(rs.getDouble("PRICE"));
                product.setStock(rs.getInt("STOCK"));
                product.setImagePath(rs.getString("IMAGE_PATH")); // 이미지 경로 설정
                System.out.println("상품 이름: " + rs.getString("NAME")); // 디버깅 메시지

                products.add(product);
            }
        }
        System.out.println("조회된 상품 수: " + products.size()); // 디버깅 메시지
        return products;
    }

    public ProductVO getProductById(int id) throws SQLException {
        ProductVO product = null;
        String query = "SELECT * FROM PRODUCTS WHERE ID = ?";

        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    product = new ProductVO();
                    product.setId(rs.getInt("ID"));
                    product.setName(rs.getString("NAME"));
                    product.setDescription(rs.getString("DESCRIPTION"));
                    product.setPrice(rs.getDouble("PRICE"));
                    product.setStock(rs.getInt("STOCK"));
                }
            }
        }
        return product;
    }
}
