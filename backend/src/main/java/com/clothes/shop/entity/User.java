package com.clothes.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
//	@Column(length = 30,nullable = false)
//	@NotBlank(message = "required")
	private String userName;
	
	@Column(length = 100,nullable = false)
	@NotBlank(message = "required")
	private String userPassword;
	
	@Column(length = 30,nullable = false,unique = true)
	@NotBlank(message = "required")
	private String userEmail;
}
