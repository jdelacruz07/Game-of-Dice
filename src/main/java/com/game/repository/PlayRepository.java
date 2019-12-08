package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.domain.Play;
import com.game.domain.Player;

public interface PlayRepository extends JpaRepository<Play, Integer> {

	public List<Play> findByOrderByPlayerAsc();

	public List<Play> findByPlayer(Player player);

}
