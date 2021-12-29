package com.msa.order.controller;

import com.msa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final OrderService orderService;

    // detail 링크(리스트 rel 표시 없음), 권한 체크
    @GetMapping(path = "/orders/order/{id}")


    @GetMapping(path = "/orders/orders/{id}")


    @GetMapping(path = "/orders/product/{id}")
}
