package com.clothes.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Shirt;

@EnableJpaRepositories
@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Integer> {
	
	public Shirt findByShirtType(Shirt shirtType);
	
	public List<Shirt> findByShirtCategory(Category category);
	
	public List<Shirt> getByShirtId(int shirtId);
	
	public Shirt findByShirtType(String shirtType);

	@Query("SELECT s FROM Shirt s " + "WHERE lower(s.shirtType) LIKE lower(concat('%', :shirtType, '%')) ")
	 List<Shirt> findAllByShirtType(@Param("shirtType")String shirtType);



}
