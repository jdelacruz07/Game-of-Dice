package com.game;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.game.repository.PlayerRepository;

@Controller
public class WebController implements WebMvcConfigurer {
	
//	@Autowired
//	PlayerRepository playerRepository;
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/results").setViewName("results");
//    }
//
//    @GetMapping("/")
//    public String showForm(PersonForm personForm) {
//        return "form";
//    }
//
//    @PostMapping("/")
//    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "form";
//        }
//        Player player = new Player();
//		player.setName(personForm.getName());
//		playerRepository.save(player);
//        return "redirect:/results";
//    }

}
