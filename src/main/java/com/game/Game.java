package com.game;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@Service
public class Game {

	PlayerRepository playerRepository;
	PlayRepository playRepository;

	@Autowired
	public Game(PlayerRepository playerRepository, PlayRepository playRepository) {
		super();
		this.playerRepository = playerRepository;
		this.playRepository = playRepository;
	}

	public String playGame() {
		String wishPlay = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Dame tu IdPlayer ");
		int id = sc.nextInt();
		System.out.println("idPlayer " + id);
		Optional<Player> player = playerRepository.findById(id);
		verifyPlayer(sc, player);
		System.out.println("Quieres volver a jugar (y/n) ");
		return wishPlay = sc.next();
	}

	public void verifyPlayer(Scanner sc, Optional<Player> player) {
		if (player.isPresent()) {
			Player play4 = player.get();
			int idPlayer = play4.getIdPlayer();
			String name = play4.getName();
			System.out.println("IdPlayer Correcto ");
			System.out.println(player);
			System.out.println("Lanza dados (y/n)");
			String isThrow = sc.next();
			System.out.println(isThrow);
			throwDice(isThrow, idPlayer, name);
		} else {
			System.out.println("idPlayer inCorrecto ");
		}
	}

	public void throwDice(String isThrow, int idPlayer, String name) {

		if (isThrow.equals("y")) {
			Play play = new Play();
			Player player2 = new Player();
			int diceOne = (int) Math.floor(Math.random() * 6 + 1);
			int diceTwo = (int) Math.floor(Math.random() * 6 + 1);
			int isWin = 0;
			System.out.println("diceOne " + diceOne);
			System.out.println("diceTwo " + diceTwo);

			if (diceOne + diceTwo == 7) {
				isWin = 1;
				System.out.println("Jugada Ganadora ");
			} else {
				isWin = 0;
				System.out.println("Jugada Perdedora ");

			}
			play.setDiceOne(diceOne);
			play.setDiceTwo(diceTwo);
			play.setIsWin(isWin);
			player2.setIdPlayer(idPlayer);
			player2.setName(name);
			play.setPlayer(player2);
			playRepository.save(play);
		}

	}
}
