package co.kh.dev.home.model;

import java.sql.Date;

public class ShopVO {
    private int rownum; // 순번
    private int no; // SHOP 테이블의 기본 키
    private int type; // SHOP 테이블의 타입 (NUMBER(1))
    private int productNo; // 연관된 PRODUCT 테이블의 기본 키
    private String name;
    private int price;
    private int amount;
    private String title; // SHOP 테이블의 타이틀
    private String content; // SHOP 테이블의 내용
    private String titleUrl; // SHOP 테이블의 타이틀img url
    private Date subdate; // 등록일

    public ShopVO(int rownum, int no, int type, int productNo, String name, int price, int amount, String title,
			String content, String titleUrl, Date subdate) {
		super();
		this.rownum = rownum;
		this.no = no;
		this.type = type;
		this.productNo = productNo;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.title = title;
		this.content = content;
		this.titleUrl = titleUrl;
		this.subdate = subdate;
	}


	public String getTitleUrl() {
		return titleUrl;
	}


	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}


	// 기본 생성자
    public ShopVO() {}


	public ShopVO(int type, int productNo, String title, String content) {
		super();
		this.type = type;
		this.productNo = productNo;
		this.title = title;
		this.content = content;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int pirce) {
		this.price = pirce;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public ShopVO(int no, int type, int productNo, String title, String content) {
		super();
		this.no = no;
		this.type = type;
		this.productNo = productNo;
		this.title = title;
		this.content = content;
	}


	// Getter와 Setter
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
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

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }

    @Override
    public String toString() {
        return "ShopVO{" +
                "rownum=" + rownum +
                ", no=" + no +
                ", type=" + type +
                ", productNo=" + productNo +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", subdate=" + subdate +
                '}';
    }
}
