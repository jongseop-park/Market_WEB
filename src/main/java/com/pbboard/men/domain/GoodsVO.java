package com.pbboard.men.domain;

public class GoodsVO {
    /** 상품명 */
    private String name;

    /** 사이즈명 */
    private String sizeName;

    /** 재고개수 */
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
