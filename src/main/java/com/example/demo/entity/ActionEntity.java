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
	
	private Integer attack;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer actionId;
	
	private String techName;
	
	private Integer techPoint;
	
	@OneToMany(mappedBy = "action")
	private List<InitialMonsterEntity> imList;
	

}
