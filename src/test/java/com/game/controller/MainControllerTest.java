package com.game.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.game.domain.Player;
import com.game.repository.PlayerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {

	@Autowired
	MainController mainController;
	@Autowired
	PlayerRepository playerRepository;

	@Test
	public void createPlayerTest() {
		Player player = new Player();
		player.setName("TeresitaOne");

		mainController.createPlayer(player);
		Player newplayer = playerRepository.findByName("TeresitaOne");
		assertNotNull(newplayer);
	}

	@Ignore
	@Test
	public void getPlayersTest() {

		mainController.getPlayers().forEach(x -> System.out.println(x));
	}
}
