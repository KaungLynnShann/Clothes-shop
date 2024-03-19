package com.clothes.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.Boot;
import com.clothes.shop.entity.Category;

@Repository
@EnableJpaRepositories
public interface BootRepository extends JpaRepository<Boot, Integer> {
	
	public List<Boot> getByBootId(int id);
	
	public List<Boot> findByBootCategory(Category category);
	
	public Boot findByBootType(Boot bootType);
	
	public Boot findByBootType(String bootType);
	
	@Query("SELECT b FROM Boot b " + "WHERE lower(b.bootType) LIKE lower(concat('%', :bootType, '%')) ")
	List<Boot> findAllByBootType(@Param("bootType") String bootType);

}
