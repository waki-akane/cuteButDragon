package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "action")
@Data
public class ActionEntity {
	
	Integer attack;
	
	Integer actionId;
	
	String techName;
	
	Integer techPoint;
	
	@OneToMany(mappedBy = "action")
	private List<InitialMonsterEntity> imList;
	

}
