package com.clothes.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Hat;

@Repository
@EnableJpaRepositories
public interface HatRepository extends JpaRepository<Hat, Integer> {
	
	public List<Hat> getByHatId(int id);
	
	public Hat findByHatType(Hat hatType);
	
	public List<Hat> findByHatCategory(Category category);
	
	public Hat findByHatType(String hatType);
	
	@Query("SELECT h FROM Hat h " + "WHERE lower(h.hatType) LIKE lower(concat('%', :hatType, '%')) ")
	List<Hat> findByAllHatType(@Param("hatType")String hatType);

}
