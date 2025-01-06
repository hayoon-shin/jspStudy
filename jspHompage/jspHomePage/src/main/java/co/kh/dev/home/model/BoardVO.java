package co.kh.dev.home.model;

import java.sql.Date;

public class BoardVO {
	
	public BoardVO(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
	}
	private int rownum;          // Primary Key
    private int no;          // Primary Key
    private String customerId;       // Foreign Key
    private String name;       // Foreign Key
    private String title;       // Not Null
    private String content;        // Not Null
    private int count;          // Default 0
    private Date subdate;       // Not Null
    private int commentNum;          // Default 0
    
    
    
    public int getCommentNum() {
		return commentNum;
	}


	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}


	public BoardVO(int rownum, int no, String customerId, String name, String title, String content, int count,
			 Date subdate, int commentNum) {
		super();
		this.rownum = rownum;
		this.no = no;
		this.customerId = customerId;
		this.name = name;
		this.title = title;
		this.content = content;
		this.count = count;
		this.commentNum = commentNum;
		this.subdate = subdate;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public int getNo() {
		return no;
	}
	
    


	public int getRownum() {
		return rownum;
	}











	public BoardVO(int rownum, int no, String customerId, String name, String title, String content, int count,
			Date subdate) {
		super();
		this.rownum = rownum;
		this.no = no;
		this.customerId = customerId;
		this.name = name;
		this.title = title;
		this.content = content;
		this.count = count;
		this.subdate = subdate;
	}





	public void setRownum(int rownum) {
		this.rownum = rownum;
	}


	public void setNo(int no) {
		this.no = no;
	}



	public BoardVO(String customerId, String title, String content) {
		super();
		this.customerId = customerId;
		this.title = title;
		this.content = content;
	}


	public BoardVO() {
		super();
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getSubdate() {
		return subdate;
	}
	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
    
    

}
