package com.example.pokemon.repository;

import com.example.pokemon.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PokemonRepository {

  public List<Pokemon> getAll() {

    ArrayList<Pokemon> pokemons = new ArrayList<>();

    try{
      Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/pokedex","pokedex_user","Vestergaard3660");
      PreparedStatement psts = conn.prepareStatement("SELECT * from pokemon");
      ResultSet resultSet = psts.executeQuery();


      while(resultSet.next()){
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        int speed = resultSet.getInt(3);
        int specialDefence = resultSet.getInt(4);
        int specialAttack = resultSet.getInt(5);
        int defence = resultSet.getInt(6);
        int attack = resultSet.getInt(7);
        int hp = resultSet.getInt(8);
        String primType = resultSet.getString(9);
        String secType = resultSet.getString(10);

        pokemons.add(new Pokemon(id, name, speed, specialDefence, specialAttack, defence,
            attack, hp, primType, secType));
      }

    } catch(SQLException e){
      System.out.println("Cannot connect to database");
      e.printStackTrace();
    }
    return pokemons;
  }

  public void create(Pokemon newPokemon) {

    try{
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/pokedex", "pokedex_user", "Vestergaard3660");

      String CREATE_QUERY = "INSERT INTO pokemon (name, speed, special_defence, special_attack," +
          " defence, attack, hp, primary_type, secondary_type) VALUES (?,?,?,?,?,?,?,?,?)";

      PreparedStatement psUpdateRow = connection.prepareStatement(CREATE_QUERY);

      //TODO: Pokedex number is nr. 1. Auto-increment?
      psUpdateRow.setString(1, newPokemon.getName());
      psUpdateRow.setInt(2, newPokemon.getSpeed());
      psUpdateRow.setInt(3, newPokemon.getSpecialDefence());
      psUpdateRow.setInt(4, newPokemon.getSpecialAttack());
      psUpdateRow.setInt(5, newPokemon.getDefence());
      psUpdateRow.setInt(6, newPokemon.getAttack());
      psUpdateRow.setInt(7, newPokemon.getHp());
      psUpdateRow.setString(8, newPokemon.getPrimType());
      psUpdateRow.setString(9, newPokemon.getSecType());

      psUpdateRow.executeUpdate();

    }catch (SQLException e){
      System.out.println("Systemet klarede ikke at oprette en ny pokemon");
      e.getStackTrace();
    }

  }

/*
//TODO: How to write BLOB-image file from database?
  public void connectImageFromDatabase(int pokedexNr){

    try{
        //get connection
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/pokedex", "pokedex_user", "Vestergaard3660");

      String query = "SELECT * from images WHERE pokedex_number = pokedex_number";
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      //execute query
      ResultSet resultSet = preparedStatement.executeQuery();


      while(resultSet.next()){
        Blob clob = resultSet.getBlob(3);
        byte[] byteArr = clob.getBytes(1,(int)clob.length());

       // FileOutputStream fileOutputStream = new FileOutputStream("F:\\savedImage.jpg");
        //fileOutputStream.write(byteArr);

        System.out.println("Image retrieved successfully.");

        //close connection
        //fileOutputStream.close();
        preparedStatement.close();
        connection.close();
      }


      }catch(Exception e){
        e.printStackTrace();
      }
    }

 */

//TODO: bruge til search funktion?
  public Pokemon findPokemonById(int id) {
    Pokemon pokemon = new Pokemon();
    pokemon.setId(id);

    //ArrayList<Pokemon> pokemons = new ArrayList<>();


    try {
      Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/pokedex", "pokedex_user", "Vestergaard3660");

      String query = "SELECT * FROM pokemon WHERE pokedex_number=?";
      PreparedStatement psts = conn.prepareStatement(query);
      psts.setInt(1, id);

      //execute query
      ResultSet resultSet = psts.executeQuery();

      resultSet.next();
        String name = resultSet.getString(2);
        int speed = resultSet.getInt(3);
        int specialDefence = resultSet.getInt(4);
        int specialAttack = resultSet.getInt(5);
        int defence = resultSet.getInt(6);
        int attack = resultSet.getInt(7);
        int hp = resultSet.getInt(8);
        String primType = resultSet.getString(9);
        String secType = resultSet.getString(10);

        //pokemons.add(new Pokemon(id, name, speed, specialDefence, specialAttack, defence, attack, hp,
         //   primType, secType));

        pokemon.setName(name);
        pokemon.setSpeed(speed);
        pokemon.setSpecialDefence(specialDefence);
        pokemon.setSpecialAttack(specialAttack);
        pokemon.setDefence(defence);
        pokemon.setAttack(attack);
        pokemon.setHp(hp);
        pokemon.setPrimType(primType);
        pokemon.setSecType(secType);

        System.out.println(pokemon);


    } catch (SQLException e){
      System.out.println("Cannot connect to database");
      e.printStackTrace();
    }
    return pokemon;
  }

  public void updateById(Pokemon pokemon) {

    String update_query = "UPDATE pokemon SET name=?, speed=?, special_defence=?, special_attack=?, defence=?, attack=?," +
        "hp=?, primary_type=?, secondary_type=? WHERE pokedex_number=?";


    try {
      Connection conn = DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/pokedex", "pokedex_user", "Vestergaard3660");

    PreparedStatement psUpdateRow = conn.prepareStatement(update_query);

    psUpdateRow.setInt(1, pokemon.getId());
    psUpdateRow.setString(2, pokemon.getName());
    psUpdateRow.setInt(3, pokemon.getSpeed());
    psUpdateRow.setInt(4, pokemon.getSpecialDefence());
    psUpdateRow.setInt(5, pokemon.getSpecialAttack());
    psUpdateRow.setInt(6, pokemon.getDefence());
    psUpdateRow.setInt(7, pokemon.getAttack());
    psUpdateRow.setInt(8, pokemon.getHp());
    psUpdateRow.setString(9, pokemon.getPrimType());
    psUpdateRow.setString(10, pokemon.getSecType());

    System.out.println("Row updated");

      psUpdateRow.executeUpdate();

    } catch (SQLException e) {
      System.out.println("Could not update");
      e.printStackTrace();
    }
  }
}
