package com.msa.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //상품 ID
    @Column(nullable = false)
    private Long productId;

    //주문자
    @Column(nullable = false, length = 16)
    private String orderer;

    //주문자 비밀번호
    @Column(nullable = false, length = 16)
    private String passward;

    //할인금액
    private int discount;

    //지불금액
    @Column(nullable = false)
    private int payment;

    @Builder
    public Order(Long productId, String orderer, String passward, int discount, int payment) {
        this.productId = productId;
        this.orderer = orderer;
        this.passward = passward;
        this.discount = discount;
        this.payment = payment;
    }
}
