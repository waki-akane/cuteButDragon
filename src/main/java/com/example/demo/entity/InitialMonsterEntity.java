package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@JoinColumn(name = "actionId")
	private ActionEntity imAction1;

	@ManyToOne
	@JoinColumn(name = "actionId")
	private ActionEntity imAction2;

	@ManyToOne
	@JoinColumn(name = "actionId")
	private ActionEntity imAction3;

	@OneToMany(mappedBy = "initialmonster")
	private List<MyMonsterEntity> mmList;

}