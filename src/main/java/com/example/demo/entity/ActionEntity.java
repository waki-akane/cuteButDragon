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

	@OneToMany(mappedBy = "action")
	private List<InitialMonsterEntity> imList;

	@OneToMany(mappedBy = "action")
	private List<EnemyMonsterEntity> emList;

	// リレーションシップの定義
	/*	@OneToMany(mappedBy = "action")
		private List<InitialMonsterEntity> imList1;
		@OneToMany(mappedBy = "action")
		private List<InitialMonsterEntity> imList2;
		@OneToMany(mappedBy = "action")
		private List<InitialMonsterEntity> imList3;
	
		@OneToMany(mappedBy = "action")
		private List<EnemyMonsterEntity> emList1;
		@OneToMany(mappedBy = "action")
		private List<EnemyMonsterEntity> emList2;
		@OneToMany(mappedBy = "action")
		private List<EnemyMonsterEntity> emList3;
	*/
}
