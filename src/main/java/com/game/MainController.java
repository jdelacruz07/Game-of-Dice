package com.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
	RankingRepository rankingRepository;


	public MainController(PlayRepository playRepository, PlayerRepository playerRepository,
			RankingRepository rankingRepository) {
		super();
		this.playRepository = playRepository;
		this.playerRepository = playerRepository;
		this.rankingRepository = rankingRepository;
	}

	///////////////////// Parte de la vista para insertar jugador
	///////////////////// ////////////////////////////////////////////////////////////////
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@GetMapping("/")

	public String showForm(Player player) {
		return "form";
	}

	@PostMapping("/")
	@Transactional
	public String checkPersonInfo(@Valid Player player, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";
		}
		playerRepository.save(player);
		return "redirect:/results";
	}

	@GetMapping("/web")
	public String getPlayer(Model model) {
		model.addAttribute("players", playerRepository.findAll());
		return "player";
	}
	////////////////////////////////////////////////////////////////////////////////////////

	//#################################          1           ###############################
	@PostMapping
	@ResponseBody
	@Transactional
	public void createPlayer(@RequestBody Player newPlayer) {
		playerRepository.save(newPlayer);
	}

	//#################################          2           ###############################
	@PutMapping
	@ResponseBody
	@Transactional
	public void updatePlayer(@RequestBody Player newPlayer) {
		playerRepository.save(newPlayer);
	}

	//#################################          3           ###############################
	@PostMapping(path = "/{idPlayer}/games")
	@ResponseBody
	@Transactional
	public void createPlay(@PathVariable("idPlayer") int id, @RequestBody Play newPlay) {
		Player player = new Player();
		player.setIdPlayer(id);
		newPlay.setPlayer(player);
		playRepository.save(newPlay);
	}

	//#################################          4           ###############################
	@DeleteMapping(path = "/{id}")
	@ResponseBody
	public void deletePlayer(@PathVariable("id") int id) {
		playerRepository.deleteById(id);
	}

	//#################################          5           ###############################
	@DeleteMapping(path = "/{id}/games")
	@ResponseBody
	public void deletePlay(@PathVariable("id") int id) {
		playRepository.deleteById(id);
	}


	//#################################          6           ###############################
	@GetMapping
	@ResponseBody
	@Transactional
	public Iterable<Player> getPlayer() {
		return playerRepository.findAll();
	}

	//#################################          7           ###############################
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

	//#################################          8           ###############################
	@GetMapping(path = "/ranking")
	@ResponseBody
	@Transactional
	public List<Ranking> getPlayerRanking() {
		List<Play> play = playRepository.findAll();
		List<Ranking>ranking = new ArrayList<>();
		Ranking ranking1 = new Ranking();
		int i =0;
		System.out.println("#####################  1 ");
		for (Play play2 : play) {
			ranking1.setIdPlayer(play2.getPlayer().getIdPlayer());
			ranking1.setName(play2.getPlayer().getName());
			ranking1.setIsWin(play2.getIsWin());
			System.out.println(ranking1);
			ranking.add(ranking1);
			rankingRepository.save(ranking1);
		}
		System.out.println("#####################  2 ");
		for (Ranking ranking2 : ranking) {
			System.out.println(" ");
			System.out.println(ranking2);
		}
		return ranking;
	}
//			if (idPlayer == lastPlayer || lastPlayer == 0) {
//				System.out.println("############################### 1 ");
//				if (lastPlayer != 0 && isWin == 1) {
//					i = i + 1;
//				}
//			} else {
//				System.out.println("############################### 2 ");
//				rankingRepository.save(rank);
//				rank2.add(rank);
//				total = 1;
//				i = 0;
//				if (lastPlayer != 0 && isWin == 1) {
//					i = i + 1;
//				}
//			}
//			
	

	//#################################          9           ###############################
	@GetMapping(path = "/ranking/loser")
	@ResponseBody
	public Iterable<Player> getPlayerLoser(@RequestBody Player newPlayer) {
		return playerRepository.findAll();
	}

	//#################################          10          ###############################
	@GetMapping(path = "/ranking/winner")
	@ResponseBody
	public Iterable<Player> getPlayerWinner(@RequestBody Player newPlayer) {
		return playerRepository.findAll();
	}

}
