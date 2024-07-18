package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enemymonster")
@Data
public class EnemyMonsterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer emId;

	private String emName;

	private Integer emHp;

	private Integer emLevel;

	private Integer win;

	private Integer lose;
	
	private String emPhoto;

	@ManyToOne
	@JoinColumn(name = "emAction1")
	private ActionEntity emAction1;

	@ManyToOne
	@JoinColumn(name = "emAction2")
	private ActionEntity emAction2;

	@ManyToOne
	@JoinColumn(name = "emAction3")
	private ActionEntity emAction3;

}
