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
@Table(name = "initialmonster")
@Data
public class InitialMonsterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imId;

	private String imName;

	private String photo;

	@ManyToOne
	@JoinColumn(name = "imAction1")
	private ActionEntity imAction1;

	@ManyToOne
	@JoinColumn(name = "imAction2")
	private ActionEntity imAction2;

	@ManyToOne
	@JoinColumn(name = "imAction3")
	private ActionEntity imAction3;


}