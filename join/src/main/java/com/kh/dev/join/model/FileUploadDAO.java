package com.kh.dev.join.model;

import com.kh.dev.common.ConnectionPool;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileUploadDAO {
	
	private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;	
    
    public FileUploadDAO() {
        try {
            // ConnectionPool에서 연결을 가져와 초기화
            this.conn = ConnectionPool.getInstance().dbCon();
            if (this.conn == null) {
                throw new SQLException("데이터베이스 연결에 실패했습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
    	return ConnectionPool.getInstance().dbCon();
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 게시글 목록 조회 (페이징)
    public List<FileUploadVO> getFileUploadList(int page, int pageSize) throws SQLException {
        String query = "SELECT * FROM (SELECT ROWNUM AS RN, B.* FROM (SELECT * FROM FILEUPLOAD ORDER BY ID DESC) B) WHERE RN BETWEEN ? AND ?";
        List<FileUploadVO> list = new ArrayList<>();
        try (Connection conn = ConnectionPool.getInstance().dbCon(); 
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
        	Connection conn = ConnectionPool.getInstance().dbCon();
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
    
    public void saveFileInfo(int boardId, String fileName, String filePath, long fileSize) throws SQLException {
    	validateFileInputs(boardId, fileName, filePath, fileSize); // 입력 데이터 검증
    	String query = "INSERT INTO FILES (ID, BOARD_ID, FILE_NAME, FILE_PATH, FILE_SIZE) VALUES (FILES_SEQ.NEXTVAL, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, boardId); // BOARD_ID
            pstmt.setString(2, fileName); // FILE_NAME
            pstmt.setString(3, filePath); // FILE_PATH
            pstmt.setLong(4, fileSize); // FILE_SIZE
            pstmt.executeUpdate();
        }
    }

    
    public boolean checkPassword(int id, String password) throws SQLException {
    	Connection conn = ConnectionPool.getInstance().dbCon();
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
        validateFileUploadInputs(vo.getTitle(), vo.getAuthor(), vo.getContent(), vo.getPassword()); // 데이터 검증

        int boardId = 0;
        String query = "INSERT INTO FILEUPLOAD (ID, TITLE, AUTHOR, CONTENT, PASSWORD, CREATED_DATE) " +
                       "VALUES (FILEUPLOAD_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
        try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getTitle());   // TITLE
            pstmt.setString(2, vo.getAuthor()); // AUTHOR
            pstmt.setString(3, vo.getContent()); // CONTENT
            pstmt.setString(4, vo.getPassword()); // PASSWORD
            pstmt.executeUpdate();

            // 자동 생성된 ID 가져오기
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    boardId = rs.getInt(1); // 생성된 ID 반환
                }
            }
        }
        return boardId;
    }



    // 게시글 조회
    public FileUploadVO getFileUploadById(int id) throws SQLException {
        String query = "SELECT * FROM FILEUPLOAD WHERE ID = ?";
        try (Connection conn = ConnectionPool.getInstance().dbCon(); 
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
        try (Connection conn = ConnectionPool.getInstance().dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // 게시글 수정
    public void updateFileUpload(FileUploadVO vo) throws SQLException {
        String query = "UPDATE FILEUPLOAD SET TITLE = ?, CONTENT = ? WHERE ID = ?";
        try (Connection conn = ConnectionPool.getInstance().dbCon(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContent());
            pstmt.setInt(3, vo.getId());
            pstmt.executeUpdate();
        }
    }
    
 // 입력 데이터 검증 메서드 (게시글용)
    private void validateFileUploadInputs(String title, String author, String content, String password) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어 있습니다.");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("작성자가 비어 있습니다.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용이 비어 있습니다.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 비어 있습니다.");
        }
    }

    // 입력 데이터 검증 메서드 (파일용)
    private void validateFileInputs(int boardId, String fileName, String filePath, long fileSize) {
        if (boardId <= 0) {
            throw new IllegalArgumentException("유효하지 않은 게시글 ID입니다.");
        }
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("파일 이름이 비어 있습니다.");
        }
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("파일 경로가 비어 있습니다.");
        }
        if (fileSize <= 0) {
            throw new IllegalArgumentException("파일 크기가 유효하지 않습니다.");
        }
    }

	
}
