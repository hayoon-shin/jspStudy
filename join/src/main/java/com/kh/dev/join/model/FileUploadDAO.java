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
    
    // 전체 게시글 수를 반환하는 메서드를 추가 

    public int getTotalCount() throws SQLException {
        String query = "SELECT COUNT(*) AS COUNT FROM FILEUPLOAD";
        try (Connection conn = ConnectionPool.getInstance().dbCon();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("COUNT");
            }
        }
        return 0;
    }


    // 게시글 목록 조회 (페이징)
    public List<FileUploadVO> getFileUploadList(int page, int pageSize) throws SQLException {
        List<FileUploadVO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().dbCon();
            String query = "SELECT * FROM ( " +
                           "    SELECT ROWNUM AS RNUM, T.* " +
                           "    FROM (SELECT * FROM FILEUPLOAD ORDER BY CREATED_DATE DESC) T " +
                           "    WHERE ROWNUM <= ? " +
                           ") WHERE RNUM > ?";
            pstmt = conn.prepareStatement(query);

            int endRow = page * pageSize;
            int startRow = (page - 1) * pageSize;

            pstmt.setInt(1, endRow);
            pstmt.setInt(2, startRow);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                FileUploadVO vo = new FileUploadVO();
                vo.setId(rs.getInt("ID"));
                vo.setTitle(rs.getString("TITLE"));
                vo.setAuthor(rs.getString("AUTHOR"));
                vo.setContent(rs.getString("CONTENT"));
                vo.setPassword(rs.getString("PASSWORD"));
                vo.setCreatedDate(rs.getDate("CREATED_DATE"));
                list.add(vo);
            }
        } finally {
            // 자원 반환
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) ConnectionPool.getInstance().releaseConnection(conn);
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
        validateFileUploadInputs(vo.getTitle(), vo.getAuthor(), vo.getContent(), vo.getPassword());

        int boardId = 0;

        // 데이터 삽입
        String insertQuery = "INSERT INTO FILEUPLOAD (ID, TITLE, AUTHOR, CONTENT, PASSWORD, CREATED_DATE) " +
                             "VALUES (FILEUPLOAD_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
        String selectQuery = "SELECT FILEUPLOAD_SEQ.CURRVAL AS ID FROM DUAL";

        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {

            insertStmt.setString(1, vo.getTitle());
            insertStmt.setString(2, vo.getAuthor());
            insertStmt.setString(3, vo.getContent());
            insertStmt.setString(4, vo.getPassword());
            insertStmt.executeUpdate();

            // 생성된 ID 가져오기
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    boardId = rs.getInt("ID");
                    System.out.println("Generated Key using CURRVAL: " + boardId);
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
