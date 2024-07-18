package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "action")
@Data
public class ActionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer actionId;

	private Integer attack;

	private String techName;

	private Integer techPoint;


	// リレーションシップの定義
	@OneToMany(mappedBy = "imAction1")
	private List<InitialMonsterEntity> imList1;
	@OneToMany(mappedBy = "imAction2")
	private List<InitialMonsterEntity> imList2;
	@OneToMany(mappedBy = "imAction3")
	private List<InitialMonsterEntity> imList3;
	
	@OneToMany(mappedBy = "emAction1")
	private List<EnemyMonsterEntity> emList1;
	@OneToMany(mappedBy = "emAction2")
	private List<EnemyMonsterEntity> emList2;
	@OneToMany(mappedBy = "emAction3")
	private List<EnemyMonsterEntity> emList3;
}
