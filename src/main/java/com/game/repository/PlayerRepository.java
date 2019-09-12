package com.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}
