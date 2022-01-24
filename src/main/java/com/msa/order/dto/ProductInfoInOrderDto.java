package com.msa.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;

@NoArgsConstructor
@Getter
public class ProductInfoInOrderDto {
    private Link link;
    private String title;
}
