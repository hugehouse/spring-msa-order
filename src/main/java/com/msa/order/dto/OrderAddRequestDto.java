package com.msa.order.dto;

import com.msa.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class OrderAddRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    @Size(min = 11, max = 11)
    private String orderer;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    private int payment;

    @PositiveOrZero
    @NotNull
    private int amount;

    public Orders toEntity() {
        return Orders.builder()
                .productId(productId)
                .orderer(orderer)
                .address(address)
                .payment(payment)
                .amount(amount)
                .build();
    }
}
