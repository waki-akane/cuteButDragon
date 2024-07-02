package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usertable")
@Data
public class UserTableEntity {
	
	String userName;
	
	String pass;
	
	Integer userId;
	
	Integer status;
	
	//@OneToMany(mappedBy )

}
