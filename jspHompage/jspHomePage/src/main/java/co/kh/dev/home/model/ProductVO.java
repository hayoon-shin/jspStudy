package co.kh.dev.home.model;

import java.sql.Date;

public class ProductVO {
	private int rownum;
	private int no;
	private String name;
	private int price;
	private int amount;
	private Date subdate;

	public ProductVO() {
	}

	public ProductVO(int rownum, int no, String name, int price, int amount, Date subdate) {
		this.rownum = rownum;
		this.no = no;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.subdate = subdate;
	}

	public ProductVO(String name, int price, int amount) {
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	public ProductVO(int no,String name, int price, int amount) {
		this.no = no;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}

	public int getNo() {
		return no;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public void setNo(int no) {
		this.no = no;
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

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getSubdate() {
		return subdate;
	}

	public void setSubdate(Date subdate) {
		this.subdate = subdate;
	}
}