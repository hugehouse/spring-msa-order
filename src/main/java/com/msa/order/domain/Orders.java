package com.msa.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //상품 ID
    @Column(nullable = false)
    private Long productId;

    //주문자,
    @Column(nullable = false, length = 11)
    private String orderer;

    //주문자,
    @Column(nullable = false, length = 100)
    private String address;

    //지불금액
    @Column(nullable = false)
    private int payment;

    //구매수량
    @Column(nullable = false)
    private int amount;

    @Builder
    public Orders(Long productId, String orderer, String address, int payment, int amount) {
        this.productId = productId;
        this.orderer = orderer;
        this.address = address;
        this.payment = payment;
        this.amount = amount;
    }
}
