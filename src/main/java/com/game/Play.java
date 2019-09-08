package com.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int idPlay;
	int diceOne;
	int diceTwo;
	
	
	public int getIdPlay() {
		return idPlay;
	}
	public void setIdPlay(int idPlay) {
		this.idPlay = idPlay;
	}
	public int getDiceOne() {
		return diceOne;
	}
	public void setDiceOne(int diceOne) {
		this.diceOne = diceOne;
	}
	public int getDiceTwo() {
		return diceTwo;
	}
	public void setDiceTwo(int diceTwo) {
		this.diceTwo = diceTwo;
	}
	
	
	
	
	
	
	
	
}
