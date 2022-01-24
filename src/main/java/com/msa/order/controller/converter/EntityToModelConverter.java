package com.msa.order.controller.converter;

import com.msa.order.controller.IndexController;
import com.msa.order.domain.Orders;
import com.msa.order.dto.OrderDetailResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EntityToModelConverter {

    // create 이후 노출
    public EntityModel<Orders> toModel(Orders entity) {
        return getDetailEntityModel(entity, 0);
    }

    // order detail 페이지에서의 링크 표현
    public EntityModel<OrderDetailResponseDto> toModel(OrderDetailResponseDto dto) {
        return getDetailEntityModel(dto, 0);
    }
    
    // list 페이지에서의 링크 표현
    public EntityModel<Orders> toModelWithPage(Orders entity, int offset) {
        return getDetailEntityModel(entity, offset);
    }

    // delete 이후 노출
    public RepresentationModel getListLink(String orderer) {
        return new RepresentationModel(getPagingLink(orderer, 0, "list"));
    }

    // list 페이지에서 페이징 링크 표현
    public CollectionModel<EntityModel<Orders>> toCollectionModel(List<EntityModel<Orders>> orders,
                                                                  Page<Orders> pagedOrders, int offset) {
        String orderer = orders.get(0).getContent().getOrderer();
        CollectionModel<EntityModel<Orders>> model = CollectionModel.of(orders,
                linkTo(methodOn(IndexController.class).getOrderList(orderer, offset)).withSelfRel());

        if(pagedOrders.hasPrevious()) {
            model.add(getPagingLink(orderer, 0, "first"));
            model.add(getPagingLink(orderer, offset-1, "previous"));
        }
        if(pagedOrders.hasNext()) {
            model.add(getPagingLink(orderer, offset+1, "next"));
            model.add(getPagingLink(orderer, pagedOrders.getTotalPages()-1, "last"));
        }
        return model;
    }

    private EntityModel<Orders> getDetailEntityModel(Orders entity, int offset) {
        return EntityModel.of(entity
                , getDetailSelfLink(entity.getId())
                , getPagingLink(entity.getOrderer(), offset, "list"));
    }

    private EntityModel<OrderDetailResponseDto> getDetailEntityModel(OrderDetailResponseDto dto, int offset) {
        return EntityModel.of(dto
                , getDetailSelfLink(dto.getOrders().getId())
                , getPagingLink(dto.getOrders().getOrderer(), offset, "list"));
    }

    private Link getPagingLink(String orderer, int offset, String rel) {
        return linkTo(methodOn(IndexController.class).getOrderList(orderer, offset)).withRel(rel);
    }

    private Link getDetailSelfLink(Long id) {
        return linkTo(methodOn(IndexController.class).getOrder(id)).withSelfRel();
    }
}
