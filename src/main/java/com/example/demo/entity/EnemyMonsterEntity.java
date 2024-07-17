package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	private String emPhoto;

	@ManyToOne
	@JoinColumn(name = "actionId")
	private ActionEntity emAction1;

	@ManyToOne
	@JoinColumn(name = "actionId")
	private ActionEntity emAction2;

	@ManyToOne
	@JoinColumn(name = "actionId")
	private ActionEntity emAction3;

}
