package com.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String wishPlay = null;
		do {
			wishPlay = Game.playGame();
		} while (wishPlay.equals("y"));
		wishPlay = Game.playGame();
	}

}
