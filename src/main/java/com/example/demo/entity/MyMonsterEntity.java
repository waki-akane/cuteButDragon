package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mymonster")
@Data
public class MyMonsterEntity {
	
	private Integer mmId;
	
	private String mmName;
	
	private Integer mmHp;
	
	private Integer mmAttack;
	
	private Integer mmLevel;
	
	private Integer mmEx;
	
	//Integer userId;
	
	//Integer imId;
	
	

}
