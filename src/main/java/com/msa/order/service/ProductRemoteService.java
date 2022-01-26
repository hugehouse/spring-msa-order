package com.msa.order.service;

import com.msa.order.dto.ProductInfoInOrderDto;
import com.msa.order.link.LinkList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@RequiredArgsConstructor
@Service
public class ProductRemoteService {

    private final RestTemplate restTemplate;

    public String getProductInfo(Long productId) {
        return restTemplate.getForObject(LinkList.productInfo.getLink(), String.class, productId);
    }

    // 가져오는데 생성자가 필요
    public ProductInfoInOrderDto getProductInfoInOrder(Long productId) {
        return restTemplate.getForObject(
                LinkList.productInfoInOrder.getLink()
                , ProductInfoInOrderDto.class
                , productId);
    }

    public void purchaseProduct(Long productId,int amount) {
        restTemplate.put(LinkList.purchaseProduct.getLink(), String.class, productId, amount);
    }
}
