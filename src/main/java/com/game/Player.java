	package com.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPlayer;
	@NotNull
    @Size(min=2, max=40)
	private String name;
	
	
	
	/**
	 * @return the idPlayer
	 */
	public int getIdPlayer() {
		return idPlayer;
	}
	/**
	 * @param idPlayer the idPlayer to set
	 */
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + "]";
	}
	
	
	
	
	
}
