package co.kh.dev.home.model;

public class CartVO {
    private int no;                // PK
    private String customerId;     // FK
    private String title;     
    private int price;     
	private int shopNo;            // FK
    private int qt;

    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public CartVO(int no, String customerId, String title, int price, int shopNo, int qt) {
		super();
		this.no = no;
		this.customerId = customerId;
		this.title = title;
		this.price = price;
		this.shopNo = shopNo;
		this.qt = qt;
	}

    // 기본 생성자
    public CartVO() {}

    // 모든 필드를 초기화하는 생성자
    public CartVO(int no, String customerId, int shopNo, int qt) {
        this.no = no;
        this.customerId = customerId;
        this.shopNo = shopNo;
        this.qt = qt;
    }

    // Getter와 Setter
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

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

}

