package com.msa.order.dto;

import com.msa.order.domain.Orders;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderDetailResponseDto {
    private Orders orders;
    private String productTitle;

    @Builder
    public OrderDetailResponseDto(Orders orders, String productTitle) {
        this.orders = orders;
        this.productTitle = productTitle;
    }
}
