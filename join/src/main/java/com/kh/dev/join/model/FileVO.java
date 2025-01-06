package com.kh.dev.join.model;

public class FileVO {
    private int id; // 파일 ID
    private int boardId; // 게시글 ID
    private String fileName; // 실제 파일 이름
    private String filePath; // 파일 경로
    private long fileSize; // 파일 크기 (바이트 단위)

    // Getter and Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public long getFileSize() {
        return fileSize;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}

