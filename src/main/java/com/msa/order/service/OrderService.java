package com.msa.order.service;

import com.msa.order.domain.Order;
import com.msa.order.dto.OrderAddRequestDto;
import com.msa.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order findOrder(Long itemId) {
        return orderRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
    }

    @Transactional(readOnly = true)
    public Page<Order> findPagingOrders(int offset, int limit) {
        return orderRepository.findAll(PageRequest.of(
                offset,
                limit,
                Sort.Direction.DESC, "id"));
    }

    @Transactional
    public Order addOrder(@Valid OrderAddRequestDto order) {
        return orderRepository.save(order.toEntity());
    }

    @Transactional
    public void deleteProduct(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
        orderRepository.delete(order);
    }
}
