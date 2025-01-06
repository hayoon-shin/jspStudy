package co.kh.dev.home.model;

public class ShopImgVO {
    private int no;         // 이미지 번호
    private String type;    // 이미지 타입 (TITLE, CONTENT)
    private int shopNo;     // 가게 번호
    private String url;     // 이미지 URL

    // 기본 생성자
    public ShopImgVO() {}

    // 생성자
    public ShopImgVO(int no, String type, int shopNo, String url) {
        this.no = no;
        this.type = type;
        this.shopNo = shopNo;
        this.url = url;
    }
    public ShopImgVO( String type, int shopNo, String url) {
    	this.type = type;
    	this.shopNo = shopNo;
    	this.url = url;
    }

    // Getter와 Setter
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getShopNo() {
        return shopNo;
    }

    public void setShopNo(int shopNo) {
        this.shopNo = shopNo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ShopImgVO [no=" + no + ", type=" + type + ", shopNo=" + shopNo + ", url=" + url + "]";
    }
}

