package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game.Play;
import com.game.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

	@Query("select  p from Player p order by p.avg desc")
	List<Player> OrderByAvgDesc();
	
	@Query("select  p from Player p order by p.avg asc ")
	List<Player> OrderByAvgAsc();
}
