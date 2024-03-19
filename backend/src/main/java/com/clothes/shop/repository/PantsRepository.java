package com.clothes.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clothes.shop.entity.Category;
import com.clothes.shop.entity.Pants;

@EnableJpaRepositories
@Repository
public interface PantsRepository extends JpaRepository<Pants, Integer> {
	
	public List<Pants> getByPantsId(int pantsId);
	
	public List<Pants> findByPantsCategory(Category category);
	
	public Pants findByPantsType(Pants pantsType);
	
	public Pants findByPantsType(String pantsType);
	
	@Query("SELECT p FROM Pants p " + "WHERE lower(p.pantsType) LIKE lower(concat('%', :pantsType, '%')) ")
	List<Pants> findAllByPantsType(@Param("pantsType")String pantsType);

}
