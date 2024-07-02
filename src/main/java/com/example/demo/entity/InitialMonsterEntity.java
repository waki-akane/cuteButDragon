package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "initialmonster")
@Data
public class InitialMonsterEntity {
	
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
	
}