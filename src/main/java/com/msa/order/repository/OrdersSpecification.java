package com.msa.order.repository;

import com.msa.order.domain.Orders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public class OrdersSpecification {

    // 조건
    public Specification<Orders> equalOrderer(String orderer) {
        return new Specification<Orders>() {
            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("orderer"), orderer);
            }
        };
    }

    // where
    public Specification<Orders> searchForOrderer(String orderer) {
        return Specification.where(equalOrderer(orderer));
    }
}
