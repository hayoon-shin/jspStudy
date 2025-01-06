package co.kh.dev.home.model;

import java.sql.Date;

public class CommentVO {
    public CommentVO(String customerId, int boardNo, int parentNo, int depth, String content) {
		super();
		this.customerId = customerId;
		this.boardNo = boardNo;
		this.parentNo = parentNo;
		this.depth = depth;
		this.content = content;
	}

	// 필드 선언
    private int no;              // 댓글 고유 번호
    private String customerId;   // 작성자 ID
    private int boardNo;      // 게시글 번호
    private int parentNo;    // 부모 댓글 번호 (대댓글)
    private int depth;           // 댓글 깊이
    private String content;      // 댓글 내용
    private Date subdate;        // 작성일자

    // 기본 생성자
    public CommentVO() {
    }

    // 필드를 초기화하는 생성자
    public CommentVO(int no, String customerId, int boardNo, int parentNo, int depth, String content, Date subdate) {
        this.no = no;
        this.customerId = customerId;
        this.boardNo = boardNo;
        this.parentNo = parentNo;
        this.depth = depth;
        this.content = content;
        this.subdate = subdate;
    }

    // Getter & Setter 메서드
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public int getParentNo() {
        return parentNo;
    }

    public void setParentNo(int parentNo) {
        this.parentNo = parentNo;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }
}
