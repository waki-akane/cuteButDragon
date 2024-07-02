package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enemyex")
@Data
public class EnemyExEntity {
	
	Integer emId;
	
	Integer win;
	
	Integer lose;

}
