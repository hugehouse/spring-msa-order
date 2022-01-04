package com.msa.order.service;

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

    public void purchaseProduct(Long productId,int amount) {
        restTemplate.put(LinkList.purchaseProduct.getLink(), String.class, productId, amount);
    }
}
