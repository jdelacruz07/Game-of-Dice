package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game.Play;

public interface PlayRepository extends JpaRepository<Play, Integer>{
	
	@Query("select  p from Play p order by p.player")
	List<Play> OrderByPlayer();
	
	
}
