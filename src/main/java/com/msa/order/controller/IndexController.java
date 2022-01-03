package com.msa.order.controller;

import com.msa.order.controller.converter.EntityToModelConverter;
import com.msa.order.domain.Orders;
import com.msa.order.service.OrderService;
import com.msa.order.service.ProductRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final EntityToModelConverter entityToModelConverter;
    private final ProductRemoteService productRemoteService;
    private final OrderService orderService;

    @GetMapping(path = "/pay/{id}")
    public ResponseEntity get(@PathVariable Long id, @RequestParam int amount) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productRemoteService.getProductInfo(id, amount));
    }

    @GetMapping(path = "/orders/{id}")
    public EntityModel<Orders> getOrder(@PathVariable Long id) {
        return entityToModelConverter.toModel(orderService.findOrder(id));
    }
}
