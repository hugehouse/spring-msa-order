package com.msa.order.link;

public enum LinkList {
    purchaseProduct("http://122.45.184.159:8081/products/purchase/{productId}?amount={amount}"),
    productInfo("http://122.45.184.159:8081/products/{productId}");

    private String link;

    private LinkList(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
