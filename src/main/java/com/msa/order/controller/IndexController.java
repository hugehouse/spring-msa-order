package com.msa.order.controller;

import com.msa.order.controller.converter.EntityToModelConverter;
import com.msa.order.domain.Orders;
import com.msa.order.service.OrderService;
import com.msa.order.service.ProductRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final EntityToModelConverter entityToModelConverter;
    private final ProductRemoteService productRemoteService;
    private final OrderService orderService;

    @GetMapping("/pay/{id}")
    public ResponseEntity getCheckOut(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productRemoteService.getProductInfo(id));
    }

    @GetMapping("/orders/{id}")
    public EntityModel<Orders> getOrder(@PathVariable Long id) {
        return entityToModelConverter.toModel(orderService.findOrder(id));
    }

    @GetMapping("/orders/list")
    public CollectionModel<EntityModel<Orders>> getOrderList(@RequestParam String orderer, @RequestParam int offset) {
        Page<Orders> pagedOrders = orderService.findPagingOrders(orderer, offset);
        List<EntityModel<Orders>> orders = pagedOrders.stream()
                .map(entity -> entityToModelConverter.toModelWithPage(entity, offset)).collect(Collectors.toList());
        return entityToModelConverter.toCollectionModel(orders, pagedOrders, offset);
    }
}
