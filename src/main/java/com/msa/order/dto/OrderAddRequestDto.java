package com.msa.order.dto;

import com.msa.order.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class OrderAddRequestDto {

    @PositiveOrZero
    private Long productId;

    @NotBlank
    @Pattern(regexp = "[0-9]{11}")
    private String orderer;

    @NotBlank
    @Size(max = 100)
    private String address;

    @PositiveOrZero
    private int payment;

    @Min(value = 1)
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
