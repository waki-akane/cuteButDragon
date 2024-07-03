package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enemymonster")
@Data
public class EnemyMonsterEntity {

	private Integer emId;

	private String emName;

	private Integer emHp;

	private Integer emLevel;

	private Integer win;

	private Integer lose;

	//Integer emAction1;

	//Integer emAction2;

	//Integer emAction3;

}
