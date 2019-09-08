package com.game.repository;

import org.springframework.data.repository.CrudRepository;

import com.game.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
