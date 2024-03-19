package com.clothes.shop.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.clothes.shop.enumType.RecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	
	@NotBlank(message = "required")
	@Column(length = 50)
	private String name;
	
	@NotNull(message = "required")
	@Column(length = 50)
	private double phNo;
	
	@NotBlank(message = "required")
	@Column(length=150,nullable = false)
	private String address;
	
	@NotBlank(message = "required")
	@Column(length = 50)
	private String city;
	
//	 @JsonIgnore
//	 @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	 @OneToMany(mappedBy = "customerData",
//	            fetch = FetchType.LAZY)
//	   private List<Order> orders;

}
