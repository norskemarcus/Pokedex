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

/*pokedex_number, `name`, speed, special_defence, special_attack, defence, attack, hp, primary_type,
 secondary_type)*/
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

  @GetMapping("/update")
  public String showUpdatePokemon(Model model){
    // Hent pokemon id fra repository og læg i model
    Pokemon pokemon = new Pokemon();
    model.addAttribute("pokemon", pokemon);
   // model.addAttribute("pokemon", pokemonRepository.findPokemonById(id));
    return "update";
  }

  @RequestMapping(value="/update", method= RequestMethod.POST)
  @PostMapping("/update")
  public String updatePokemon(@ModelAttribute("pokemon") Pokemon pokemon){

    Pokemon updatedPokemon = new Pokemon();

    updatedPokemon.setId(pokemon.getId());
    updatedPokemon.setName(pokemon.getName());
    updatedPokemon.setSpeed(pokemon.getSpeed());
    updatedPokemon.setSpecialDefence(pokemon.getSpecialDefence());
    updatedPokemon.setSpecialAttack(pokemon.getSpecialAttack());
    updatedPokemon.setDefence(pokemon.getDefence());
    updatedPokemon.setAttack(pokemon.getAttack());
    updatedPokemon.setHp(pokemon.getHp());
    updatedPokemon.setPrimType(pokemon.getPrimType());
    updatedPokemon.setSecType(pokemon.getSecType());

    pokemonRepository.updateById(updatedPokemon);

    return "redirect:/";
  }



  @GetMapping("/search")
  public String showSearchPokemon(Model model, int id) {
    model.addAttribute("pokemon", pokemonRepository.findPokemonById(id));
    return "search";
  }


  // Hvordan hente id eller navn fra input og bruge den infoen til at hente den pokemon
  @PostMapping("/search")
  public String searchPokemon(@RequestParam("id") int id) {

    System.out.println(id);
    return "redirect:/update";
  }
}