package com.msa.order.service;

import com.msa.order.link.LinkList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RequiredArgsConstructor
@Service
public class ProductRemoteService {

    private final RestTemplate restTemplate;

    public String getProductInfo(Long productId, int amount) {
        return restTemplate.getForObject(LinkList.productInfo.getLink(), String.class, productId, amount);
    }
}
