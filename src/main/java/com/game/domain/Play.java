package com.game.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Play {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlay;
	private int diceOne;
	private int diceTwo;
	private int isWin;
	@ManyToOne
	@JoinColumn(name = "idPlayer", nullable = false)
	private Player player;

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

	public int getIsWin() {
		return isWin;
	}

	public void setIsWin(int isWin) {
		this.isWin = isWin;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Play [idPlay=" + idPlay + ", diceOne=" + diceOne + ", diceTwo=" + diceTwo + ", isWin=" + isWin
				+ ", player=" + player + "]";
	}

}
