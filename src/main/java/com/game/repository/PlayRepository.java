package com.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.Play;

public interface PlayRepository extends JpaRepository<Play, Integer>{
	

}
