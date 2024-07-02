package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enemymonster")
@Data
public class EnemyMonsterEntity {
	
	Integer emId;
	
	String emName;
	
	Integer emHp;
	
	Integer emLevel;
	
	//Integer emAction1;
	
	//Integer emAction2;
	
	//Integer emAction3;

}
