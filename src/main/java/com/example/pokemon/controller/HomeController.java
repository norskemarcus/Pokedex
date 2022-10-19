package com.example.pokemon.controller;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

  PokemonRepository pokemonRepository;

  public HomeController(PokemonRepository p) {
    pokemonRepository = p;
  }


  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("pokemons", pokemonRepository.getAll());
    return "index";
  }



  @GetMapping("/create")
  public String showCreatePokemon(Model model) {
    Pokemon pokemon = new Pokemon();
    model.addAttribute("pokemon", pokemon);
    return "create";
  }


  @RequestMapping(value="/create", method= RequestMethod.POST)
  @PostMapping("/create")
  public String createPokemon(@ModelAttribute("pokemon") Pokemon pokemon){

    Pokemon newPokemon = new Pokemon();

    // id skal sættes automatisk
    newPokemon.setName(pokemon.getName());
    newPokemon.setSpeed(pokemon.getSpeed());
    newPokemon.setSpecialDefence(pokemon.getSpecialDefence());
    newPokemon.setSpecialAttack(pokemon.getSpecialAttack());
    newPokemon.setDefence(pokemon.getDefence());
    newPokemon.setAttack(pokemon.getAttack());
    newPokemon.setHp(pokemon.getHp());
    newPokemon.setPrimType(pokemon.getPrimType());
    newPokemon.setSecType(pokemon.getSecType());

    pokemonRepository.create(newPokemon);

    return "redirect:/";
  }



  @GetMapping("/update/{id}")
  public String showUpdatePokemon(@PathVariable("id") int updateId, Model model){
    model.addAttribute("pokemon",pokemonRepository.findPokemonById(updateId));
    return "update";
  }


  @PostMapping("/update/")
  public String updatePokemon(@ModelAttribute Pokemon pokemon){
    pokemonRepository.updateById(pokemon);
    return "redirect:/";
  }

  /* IndexTest page for experimenting with css and responsive design for menu bar*/
  @GetMapping("/indexTest")
  public String indexTest(Model model) {
    int id = 1;
    model.addAttribute("images", pokemonRepository.getImageFromDatabase(id));
    return "indexTest";
  }




  @GetMapping("/delete/{id}")
  public String showDeletePokemon(@PathVariable("id") int sletId) {
    pokemonRepository.deleteById(sletId);
    return "redirect:/";
  }


  @GetMapping("/random")
  public String showRandomPokemon(Model model){
    model.addAttribute("pokemon", pokemonRepository.getRandomPokemon());
    return "random";
  }




  // search
  /*
  @GetMapping("/search")
  public String showSearchPokemon() {
    return "search";
  }


  @RequestMapping(value="id", method= RequestMethod.POST)
  @PostMapping("/search")
  public String searchPokemon(@RequestParam( value="id") int id, Model model) {
    model.addAttribute("id", pokemonRepository.findPokemonById(id));
    return "redirect:/search";
  }

   */




}

