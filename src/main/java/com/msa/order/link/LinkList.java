package com.msa.order.link;

public enum LinkList {
    productInfo("http://122.45.184.159:8081/products/purchase/{productId}?amount={amount}");

    private String link;

    private LinkList(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
