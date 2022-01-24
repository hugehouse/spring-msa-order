package com.msa.order.link;

public enum LinkList {
    purchaseProduct("http://localhost:8081/products/purchase/{productId}?amount={amount}"),
    productInfo("http://localhost:8081/products/{productId}"),
    productInfoInOrder("http://localhost:8081/products/orders/{productId}");

    private String link;

    private LinkList(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
