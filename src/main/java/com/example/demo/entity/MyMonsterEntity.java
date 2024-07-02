package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mymonster")
@Data
public class MyMonsterEntity {
	
	Integer mmId;
	
	String mmName;
	
	Integer mmHp;
	
	Integer mmAttack;
	
	Integer mmLevel;
	
	Integer mmEx;
	
	//Integer userId;
	
	//Integer imId;
	
	

}
