package com.game.service;

import static org.junit.Assert.assertEquals;
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

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class PlayerServiceTest {

	@Autowired
	PlayerService playerService;
	@Autowired
	PlayerRepository playerRepository;

	@Test
	public void addPlayerTest() {
		Player player = new Player();
		player.setName("Teresa");

		playerService.addPlayer(player);
		Player newPlayer = playerRepository.findByName("Teresa");

		assertNotNull(newPlayer);
		assertNotNull(newPlayer.getName());
		assertEquals("Teresa", newPlayer.getName());

	}

	@Ignore
	@Test
	public void deleteByIdPlayerTest() {
		int idPlayer = 1;
		playerService.deleteByIdPlayer(idPlayer);
	}
}
