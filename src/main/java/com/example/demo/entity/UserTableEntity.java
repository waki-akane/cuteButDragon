package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usertable")
@Data
public  class UserTableEntity {
	
	private String userName;
	
	private String pass;
	
	private Integer userId;
	
	private Integer status;
	
	//@OneToMany(mappedBy )

}
