package com.msa.order.dto;

import com.msa.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class OrderAddRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    @Size(max = 16)
    private String orderer;

    @NotNull
    @Size(max = 16)
    private String passward;

    @NotNull
    private int discount;

    @NotNull
    private int payment;

    public Orders toEntity() {
        return Orders.builder()
                .productId(productId)
                .orderer(orderer)
                .passward(passward)
                .discount(discount)
                .payment(payment)
                .build();
    }
}
