package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	List<Player> findTopByOrderByAvgAsc();

	List<Player> findTopByOrderByAvgDesc();

	List<Player> findTop3ByOrderByAvgAsc();

	List<Player> findTop3ByOrderByAvgDesc();

	Player findByName(String name);

}
