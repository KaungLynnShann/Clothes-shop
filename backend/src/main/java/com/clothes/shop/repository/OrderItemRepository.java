package com.clothes.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.OrderItem;

@Repository
@EnableJpaRepositories
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
