package com.msa.order.dto;

import com.msa.order.domain.Orders;

// 현재 미사용, 사용 예정
public class OrderListResponseDto {
    private Long id;
    private Long productId;
    private int payment;
    private int amount;

    public OrderListResponseDto(Orders entity) {
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.payment = entity.getPayment();
        this.amount = entity.getAmount();
    }
}
