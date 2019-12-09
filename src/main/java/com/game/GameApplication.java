package com.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

	PlayRepository playRepository;
	PlayerRepository playerRepository;

	@Autowired
	public GameApplication(PlayRepository playRepository, PlayerRepository playerRepository) {
		super();
		this.playRepository = playRepository;
		this.playerRepository = playerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Game game = new Game(playerRepository, playRepository);
		String wishPlay = null;
		do {
			wishPlay = game.playGame();
		} while (wishPlay.equals("y"));
		wishPlay = game.playGame();
	}

}
