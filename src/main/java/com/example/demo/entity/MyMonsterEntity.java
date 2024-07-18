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
@Table(name = "mymonster")
@Data
public class MyMonsterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mmId;

	private String mmName;

	private Integer mmHp;

	private Integer mmAttack;

	private Integer mmLevel;

	private Integer mmEx;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserTableEntity userId;

	@ManyToOne
	@JoinColumn(name = "imId")
	private InitialMonsterEntity im;

}
