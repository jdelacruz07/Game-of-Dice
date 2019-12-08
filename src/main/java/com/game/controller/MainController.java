package com.game.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.game.domain.Play;
import com.game.domain.Player;
import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;
import com.game.service.PlayService;
import com.game.service.PlayerService;

@Controller
@RequestMapping(path = "/players")
@Transactional
public class MainController implements WebMvcConfigurer {
	PlayRepository playRepository;
	PlayerRepository playerRepository;
	@Autowired
	PlayerService playerService;
	@Autowired
	PlayService playService;

	public MainController(PlayRepository playRepository, PlayerRepository playerRepository) {
		super();
		this.playRepository = playRepository;
		this.playerRepository = playerRepository;
	}

	// Parte de la vista para insertar jugador
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")
	public String showForm(Player player) {
		return "form";
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid Player player, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		createPlayer(player);

		return "redirect:/results";
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
		model.addAttribute("players", playerRepository.findAll());

		return "player";
	}

	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player player) {
		playerService.addPlayer(player);
	}

	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player player) {
		playerService.newUpdatePlayer(player);
	}

	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		playService.addPlay(id, newPlay);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public void deletePlayerId(@PathVariable("id") int id) {
		playerService.deletePlayerById(id);
	}

	@DeleteMapping(path = "/{id}/games")
	@ResponseBody
	public void deletePlayId(@PathVariable("id") int id) {
		playService.deletePlayById(id);
	}

	@GetMapping
	@ResponseBody
	public Iterable<Player> getPlayer() {

		return playerService.getAllPlayers();
	}

	@GetMapping(path = "/{id}/games")
	@ResponseBody
	public List<Play> getPlayIdPlayer(@PathVariable("id") int id) {
		return playService.playByIdPlayer(id);
	}

	@GetMapping(path = "/ranking")
	@ResponseBody
	public List<Play> getPlayerRanking() {
		return playerService.playerRanking();
	}

	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public List<Player> getPlayerLoser() {
		return playerService.playerLoser();
	}

	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public List<Player> getPlayerWinner() {
		return playerService.playerWinner();
	}

}
