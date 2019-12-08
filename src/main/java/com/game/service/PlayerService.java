package com.game.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;
	@Autowired
	PlayRepository playRepository;

	public void addPlayer(Player Player) {
		playerRepository.save(Player);
	}

	public void newUpdatePlayer(Player player) {
		playerRepository.save(player);
	}

	public void deletePlayerById(int id) {
		playerRepository.deleteById(id);
	}

	public Iterable<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	public List<Play> playerRanking() {
		List<Play> play1 = playRepository.findByOrderByPlayerAsc();
		int lastPlayer = 0;
		int total = 0;
		int total2 = 0;
		int count = play1.size();
		int i = 0;
		List<Play> play4 = new ArrayList<>();
		Play play8 = new Play();
		for (Play play2 : play1) {
			total2 = 1 + total2;
			total = 1 + total;

			Player player2 = new Player();
			Play play6 = new Play();

			int isWin = play2.getIsWin();
			player2 = play2.getPlayer();
			int idPlayer = player2.getIdPlayer();
			double avg = player2.getAvg();

			if (idPlayer == lastPlayer || lastPlayer == 0) {
				if (lastPlayer != 0 && isWin == 1) {
					i = i + 1;
				} else {
					if (lastPlayer == 0 && isWin == 1) {
						i = i + 1;
					}
				}
			} else {
				play6 = play8;
				play4.add(play6);
				total = 1;
				i = 0;
				if (lastPlayer != 0 && isWin == 1) {
					i = i + 1;
				}
			}

			lastPlayer = idPlayer;
			avg = 100 * i / total;
			player2.setAvg(avg);
			play2.setPlayer(player2);
			play8 = play2;
			if (total2 == count) {
				play4.add(play2);
			}
		}
		return play4;
	}

	public List<Player> playerLoser() {
		List<Player> player = playerRepository.findTopByOrderByAvgAsc();

		return player;
	}

	public List<Player> playerWinner() {
		List<Player> player = playerRepository.findTopByOrderByAvgDesc();

		return player;
	}
}
