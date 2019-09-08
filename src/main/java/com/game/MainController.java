package com.game;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;

@Controller
@RequestMapping(path="/players")
public class MainController {
	
	PlayRepository playRepository;
	PlayerRepository playerRepository;
	
	@Autowired
	public MainController(PlayRepository playRepository, PlayerRepository playerRepository) {
		super();
		this.playRepository = playRepository;
		this.playerRepository = playerRepository;
	}
	
	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player newPlayer) {
		playerRepository.save(newPlayer);
	}

	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player newPlayer) {
		
		Player player = new Player();
		player.setIdPlayer(newPlayer.getIdPlayer());
		player.setName(newPlayer.getName());
		playerRepository.save(player);
	}
	
	@PostMapping(path="/{id}/games")
	@ResponseBody
	public void createPlay(@RequestBody Play newPlay) {
	
		Play play = new Play();
		play.setDiceOne(newPlay.getDiceOne());
		play.setDiceTwo(newPlay.getDiceTwo());
		playRepository.save(play);
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseBody
	public void deletePlayer(@PathVariable("id") int id) {
		playerRepository.deleteById(id);
	}

	@DeleteMapping(path="/{idPlayer}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("idPlayer") int id) {
		playRepository.deleteById(id);
	}

	@GetMapping
//	@ResponseBody
	public  String  getPlayer(Model model) {
		model.addAttribute("players", playerRepository.findAll());
		return "player";
	}
	
	@GetMapping(path="/{id}/games")
	@ResponseBody
	public  Optional<Play> getPlayId(@PathVariable("id") int id) {
		return playRepository.findById(id);
	}
	
	@GetMapping(path="/ranking")
	@ResponseBody
	public void getPlayerRanking(@RequestBody Player newPlayer) {
		
		Player player = new Player();
		player.setName(newPlayer.getName());
		playerRepository.save(player);
	}
	
	@GetMapping(path="/ranking/loser")
	@ResponseBody
	public void getPlayerSortDesc(@RequestBody Player newPlayer) {
		
		Player player = new Player();
		player.setName(newPlayer.getName());
		playerRepository.save(player);
	}
	
	@GetMapping(path="/ranking/winner")
	@ResponseBody
	public void getPlayerSortAsc(@RequestBody Player newPlayer) {
		
		Player player = new Player();
		player.setName(newPlayer.getName());
		playerRepository.save(player);
	}

}
