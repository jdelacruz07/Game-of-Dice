package com.game.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.repository.PlayRepository;

@Service
public class PlayService {

	@Autowired
	PlayRepository playRepository;

	public void addPlay(int idPlayer, Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(idPlayer);
		newPlay.setPlayer(player);
		playRepository.save(newPlay);
	}

	public void deletePlayById(int id) {
		playRepository.deleteById(id);
	}

	public List<Play> playByIdPlayer(int id) {
		Player player = new Player();
		player.setIdPlayer(id);
		List<Play> playidPlayer = playRepository.findByPlayer(player);
		return playidPlayer;
	}
}
