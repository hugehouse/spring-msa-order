package com.msa.order.controller.api;

import com.msa.order.controller.converter.EntityToModelConverter;
import com.msa.order.domain.Orders;
import com.msa.order.dto.OrderAddRequestDto;
import com.msa.order.service.OrderService;
import com.msa.order.service.ProductRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderApiController {
    private final EntityToModelConverter entityToModelConverter;
    private final ProductRemoteService productRemoteService;
    private final OrderService orderService;

    public ResponseEntity<EntityModel<Orders>> addOrder(OrderAddRequestDto entity) {
        // 재고 감소 요청 remote로 보내기
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entityToModelConverter.toModel(orderService.addOrder(entity)));
    }
}
