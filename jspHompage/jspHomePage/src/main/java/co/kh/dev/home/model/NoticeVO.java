package co.kh.dev.home.model;

import java.sql.Date;

public class NoticeVO {
	
	private int rownum;          // Primary Key
    private int no;          // Primary Key
    private String title;       // Not Null
    private String content;        // Not Null
    private int count;          // Default 0
    private Date subdate;       // Not Null
    
    
    
    public NoticeVO(int rownum, int no, String title, String content, int count, Date subdate) {
		super();
		this.rownum = rownum;
		this.no = no;
		this.title = title;
		this.content = content;
		this.count = count;
		this.subdate = subdate;
	}
    
    public NoticeVO( String title, String content) {
    	super();
    	this.title = title;
    	this.content = content;
    }
	public NoticeVO(int no, String title, String content) {
    	super();
    	this.no = no;
    	this.title = title;
    	this.content = content;
    }

	public NoticeVO() {
		super();
	}



	public int getRownum() {
		return rownum;
	}



	public void setRownum(int rownum) {
		this.rownum = rownum;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
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
