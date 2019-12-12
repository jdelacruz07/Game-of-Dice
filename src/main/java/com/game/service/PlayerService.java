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

	public void deleteByIdPlayer(int id) {
		playerRepository.deleteById(id);
	}

	public Iterable<Player> getAllPlayer() {
		return playerRepository.findAll();
	}

	public List<Player> playerRanking() {
		List<Play> playTotal = playRepository.findByOrderByPlayerAsc();
		List<Player> playerRank = doRanking(playTotal);
		return playerRank;
	}

	public List<Player> playerLoser() {
		List<Player> player = playerRepository.findTopByOrderByAvgAsc();

		return player;
	}

	public List<Player> playerWinner() {
		List<Player> player = playerRepository.findTopByOrderByAvgDesc();

		return player;
	}

	public List<Player> doRanking(List<Play> playTotal) {
		List<Player> playerRank = new ArrayList<>();
		int countFinal = 0;
		int lastPlayer = 0;
		double countWin = 0.0;
		double countTotal = 0.0;
		String lastName = null;
		double average = 0;
		for (Play play : playTotal) {
			countFinal++;
			int idPlayer = play.getPlayer().getIdPlayer();
			int isWin = play.getIsWin();
			if ((idPlayer == lastPlayer && isWin == 1) || lastPlayer == 0) {
				countWin = countWin + 1;
				countTotal = countTotal + 1;
			} else if (idPlayer != lastPlayer) {
				countTotal = countTotal + 1;
				Player player = updatePlayer(lastPlayer, lastName, average);
				playerRank.add(player);
				countWin = 0;
				average = 0;
				countTotal = 1;
				if (isWin == 1) {
					countWin = 1;
					countTotal = 1;
				}
			} else {
				if (idPlayer == lastPlayer && isWin == 0) {
					countTotal = countTotal + 1;
				}
			}
			average = countWin * 100 / countTotal;
			lastName = play.getPlayer().getName();
			lastPlayer = idPlayer;

			int totalSize = playTotal.size();
			if (totalSize == countFinal) {
				Player player = updatePlayer(idPlayer, play.getPlayer().getName(), average);
				playerRepository.save(player);
				playerRank.add(player);
			}
		}
		return playerRank;
	}

	public Player updatePlayer(int lastPlayer, String lastName, double average) {
		Player player = new Player();
		player.setIdPlayer(lastPlayer);
		player.setName(lastName);
		player.setAvg(average);
		playerRepository.save(player);
		return player;
	}
}
