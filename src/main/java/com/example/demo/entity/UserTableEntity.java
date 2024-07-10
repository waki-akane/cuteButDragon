package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usertable")
@Data
public class UserTableEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String userName;

	private String pass;

//	private Integer status;
//
//	@OneToMany(mappedBy = "usertable")
//	private List<MyMonsterEntity> mmList;
	
	private Integer roleId;

}
