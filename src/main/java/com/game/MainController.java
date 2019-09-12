package com.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.game.repository.PlayRepository;
import com.game.repository.PlayerRepository;
import com.game.repository.RankingRepository;

@Controller
@RequestMapping(path = "/players")
public class MainController implements WebMvcConfigurer {

	PlayRepository playRepository;
	PlayerRepository playerRepository;
	@Autowired
	RankingRepository rankingRepository;

	@Autowired
	public MainController(PlayRepository playRepository, PlayerRepository playerRepository) {
		super();
		this.playRepository = playRepository;
		this.playerRepository = playerRepository;
	}

	///////////////////// Parte de la vista para insertar jugador
	///////////////////// //////////////////////////
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
		playerRepository.save(player);
		return "redirect:/results";
	}

	////////////////////////////////////////////////////////////////////////////////////////

	@PostMapping
	@ResponseBody
	public void createPlayer(@RequestBody Player newPlayer) {
		playerRepository.save(newPlayer);
	}

	@PutMapping
	@ResponseBody
	public void updatePlayer(@RequestBody Player newPlayer) {
		playerRepository.save(newPlayer);
	}

	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(id);
		System.out.println(id);
		newPlay.setPlayer(player);
		playRepository.save(newPlay);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public void deletePlayer(@PathVariable("id") int id) {
		playerRepository.deleteById(id);
	}

	@DeleteMapping(path = "/{id}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("id") int id) {
		playRepository.deleteById(id);
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
		model.addAttribute("players", playerRepository.findAll());
		return "player";
	}

	@GetMapping
	@ResponseBody
	public Iterable<Player> getPlayer() {
		return playerRepository.findAll();
	}

	@GetMapping(path = "/{id}/games")
	@ResponseBody
	public List<Play> getPlayId(@PathVariable("id") int id) {
		Iterable<Play> play = playRepository.findAll();
		List<Play> playid = new ArrayList<>();
		Player player = new Player();
		for (Play play2 : play) {
			player = play2.getPlayer();
			if (id == player.getIdPlayer()) {
				playid.add(play2);
			}
		}

		return playid;
	}

	@GetMapping(path = "/ranking")
	@ResponseBody
	public Iterator<Ranking> getPlayerRanking() {
		List<Ranking> ranking = rankingRepository.OrderByIdPlayer();
		int i = 0;
		int total = 0;
		int isWin = 0;
		int idPlayer = 0;
		int lastPlayer = 0;
		String name = null;
		double avg = 0;
		List<Ranking> ranking4 = new ArrayList<>();
		for (Ranking ranking2 : ranking) {
			total = total + 1;
			isWin = ranking2.getIsWin();
			idPlayer = ranking2.getIdPlayer();
			name = ranking2.getName();
			System.out.println(idPlayer);
			System.out.println(lastPlayer);
			if (isWin == 1 && (idPlayer == lastPlayer || lastPlayer == 0)) {
				System.out.println("#################### 1");
				i = i + 1;
				avg = 100 * i / total;
			} else {
				if (isWin == 1 && (idPlayer != lastPlayer || lastPlayer == 0)) {
					System.out.println("#################### 2");
					i = i + 1;
					avg = 100 * i / total;
					Ranking ranking3 = new Ranking();
					ranking3.setIdPlayer(idPlayer);
					ranking3.setName(name);
					ranking3.setAvg(avg);
					ranking4.add(ranking3);
					i = 0;
					total = 0;
				} else {
					if (isWin == 0 && (idPlayer != lastPlayer || lastPlayer == 0)) {
						System.out.println("#################### 3");
						Ranking ranking3 = new Ranking();
						ranking3.setIdPlayer(idPlayer);
						ranking3.setName(name);
						ranking3.setAvg(avg);
						ranking4.add(ranking3);
						i = 0;
						total = 0;

					} else {
						System.out.println("#################### 4");
						avg = 100 * i / total;
						Ranking ranking3 = new Ranking();
						ranking3.setIdPlayer(idPlayer);
						ranking3.setName(name);
						ranking3.setAvg(avg);
						ranking4.add(ranking3);
						i = 0;
						total = 0;

					}
				}

			}

			System.out.println(avg);
			System.out.println("Ganadoras " + i);
			System.out.println("Total " + total);
			lastPlayer = idPlayer;
		}

		return ranking4.iterator();
	}

	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public Iterable<Player> getPlayerSortDesc(@RequestBody Player newPlayer) {
		return playerRepository.findAll();
	}

	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public Iterable<Player> getPlayerSortAsc(@RequestBody Player newPlayer) {
		return playerRepository.findAll();
	}

}
