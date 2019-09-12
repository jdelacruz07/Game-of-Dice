package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.game.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Integer> {
	
	@Query("select r from Ranking r order by r.idPlayer, r.isWin")
	List<Ranking> OrderByIdPlayer();

	@Query("select r from Ranking r group by r.idPlayer")
	List<Ranking> GroupByIdPlayer();
}
