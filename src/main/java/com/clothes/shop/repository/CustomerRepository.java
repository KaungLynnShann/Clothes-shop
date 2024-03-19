package com.clothes.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.Customer;
import com.clothes.shop.enumType.RecordStatus;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	

}
