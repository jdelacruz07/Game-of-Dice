package com.game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ranking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRanking;
	private int idPlayer;
	private String name;
	private int isWin;
	
	
	/**
	 * @return the idRanking
	 */
	public int getIdRanking() {
		return idRanking;
	}
	/**
	 * @param idRanking the idRanking to set
	 */
	public void setIdRanking(int idRanking) {
		this.idRanking = idRanking;
	}
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
	/**
	 * @return the isWin
	 */
	public int getIsWin() {
		return isWin;
	}
	/**
	 * @param isWin the isWin to set
	 */
	public void setIsWin(int isWin) {
		this.isWin = isWin;
	}
	/**
	 * @return the avg
	 */
	@Override
	public String toString() {
		return "Ranking [idRanking=" + idRanking + ", idPlayer=" + idPlayer + ", name=" + name + ", isWin=" + isWin
				+ "]";
	}
	
	

	
	
	
	
}
