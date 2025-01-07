package com.kh.dev.join.model;

import com.kh.dev.common.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileUploadDAO {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;	
    private ConnectionPool pool = ConnectionPool.getInstance();

    // 게시글 목록 조회 (페이징)
    public List<FileUploadVO> getFileUploadList(int page, int pageSize) throws SQLException {
        String query = "SELECT * FROM (SELECT ROWNUM AS RN, B.* FROM (SELECT * FROM FILEUPLOAD ORDER BY ID DESC) B) WHERE RN BETWEEN ? AND ?";
        List<FileUploadVO> list = new ArrayList<>();
        try (Connection conn = pool.dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, (page - 1) * pageSize + 1);
            pstmt.setInt(2, page * pageSize);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FileUploadVO vo = new FileUploadVO();
                    vo.setId(rs.getInt("ID"));
                    vo.setTitle(rs.getString("TITLE"));
                    vo.setAuthor(rs.getString("AUTHOR"));
                    vo.setContent(rs.getString("CONTENT"));
                    vo.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
                    list.add(vo);
                }
            }
        }
        return list;
    }
    
    public List<String> getFileNamesByBoardId(int boardId) throws SQLException {
        List<String> fileNames = new ArrayList<>();
        String query = "SELECT FILE_NAME FROM FILES WHERE BOARD_ID = ?";
        try {
        	Connection conn = pool.dbCon();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                fileNames.add(rs.getString("FILE_NAME"));
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        return fileNames;
    }
    
    public boolean checkPassword(int id, String password) throws SQLException {
    	Connection conn = pool.dbCon();
    	String query = "SELECT PASSWORD FROM FILEUPLOAD WHERE ID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next() && rs.getString("PASSWORD").equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    // 게시글 작성
    public int insertFileUpload(FileUploadVO vo) throws SQLException {
        int boardId = 0;
        String query = "INSERT INTO BOARD (TITLE, AUTHOR, CONTENT, PASSWORD) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getAuthor());
            pstmt.setString(3, vo.getContent());
            pstmt.setString(4, vo.getPassword());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    boardId = rs.getInt(1);
                }
            }
        }
        return boardId;
    }


    // 게시글 조회
    public FileUploadVO getFileUploadById(int id) throws SQLException {
        String query = "SELECT * FROM FILEUPLOAD WHERE ID = ?";
        try (Connection conn = pool.dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    FileUploadVO vo = new FileUploadVO();
                    vo.setId(rs.getInt("ID"));
                    vo.setTitle(rs.getString("TITLE"));
                    vo.setAuthor(rs.getString("AUTHOR"));
                    vo.setContent(rs.getString("CONTENT"));
                    vo.setCreatedDate(rs.getTimestamp("CREATED_DATE"));
                    return vo;
                }
            }
        }
        return null;
    }

    // 게시글 삭제
    public void deleteFileUpload(int id) throws SQLException {
        String query = "DELETE FROM FILEUPLOAD WHERE ID = ?";
        try (Connection conn = pool.dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // 게시글 수정
    public void updateFileUpload(FileUploadVO vo) throws SQLException {
        String query = "UPDATE FILEUPLOAD SET TITLE = ?, CONTENT = ? WHERE ID = ?";
        try (Connection conn = pool.dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getId());
            pstmt.executeUpdate();
        }
    }
}
