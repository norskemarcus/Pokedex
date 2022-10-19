package com.example.pokemon.repository;

import com.example.pokemon.model.Image;
import com.example.pokemon.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

@Repository
public class PokemonRepository {

  private final String databaseURL = "jdbc:mysql://localhost:3306/pokedex";
  private final String user = "pokedex_user";
  private final String password =  "Vestergaard3660";


  public List<Pokemon> getAll() {

    ArrayList<Pokemon> pokemons = new ArrayList<>();

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement psts = conn.prepareStatement("SELECT * from pokemon");
      ResultSet resultSet = psts.executeQuery();


      while (resultSet.next()) {
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

    } catch (SQLException e) {
      System.out.println("Cannot connect to database");
      e.printStackTrace();
    }
    return pokemons;
  }




  public void create(Pokemon newPokemon) {

    try {
      Connection connection = DriverManager.getConnection(databaseURL, user, password);

      String CREATE_QUERY = "INSERT INTO pokemon (name, speed, special_defence, special_attack," +
          " defence, attack, hp, primary_type, secondary_type) VALUES (?,?,?,?,?,?,?,?,?)";

      PreparedStatement psUpdateRow = connection.prepareStatement(CREATE_QUERY);

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

    } catch (SQLException e) {
      System.out.println("Systemet klarede ikke at oprette en ny pokemon");
      e.getStackTrace();
    }
  }



  public Image getImageFromDatabase(int id) {

    Image image = null;

    String query = "SELECT * from images WHERE pokedex_number=?";

    try {Connection connection = DriverManager.getConnection(databaseURL, user, password);

      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet rs = preparedStatement.executeQuery();

      if (rs.next()) {
        image = new Image();
        String name = rs.getString("name");
        Blob blob = rs.getBlob("image");

        InputStream inputStream = blob.getBinaryStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        inputStream.close();
        outputStream.close();
        image.setName(name);
        image.setBase64Image(base64Image);
      }

    } catch (SQLException | IOException ex) {
      System.out.println("Could not connect to image database");
      ex.printStackTrace();
    }
    return image;
  }





  public Pokemon findPokemonById(int id) {

    Pokemon pokemon = new Pokemon();
    pokemon.setId(id);

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);

      String query = "SELECT * FROM pokemon WHERE pokedex_number=?";
      PreparedStatement psts = conn.prepareStatement(query);

      // pokedex_number bliver sat med id i 1.kolonne
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

      pokemon.setName(name);
      pokemon.setSpeed(speed);
      pokemon.setSpecialDefence(specialDefence);
      pokemon.setSpecialAttack(specialAttack);
      pokemon.setDefence(defence);
      pokemon.setAttack(attack);
      pokemon.setHp(hp);
      pokemon.setPrimType(primType);
      pokemon.setSecType(secType);


    } catch (SQLException e) {
      System.out.println("Cannot connect to database");
      e.printStackTrace();
    }
    return pokemon;
  }

  public void updateById(Pokemon pokemon) {

    String update_query = "UPDATE pokemon SET name=?, speed=?, special_defence=?, special_attack=?, defence=?, attack=?," +
        "hp=?, primary_type=?, secondary_type=? WHERE pokedex_number=?";


    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);

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

  public void deleteById(int id) {

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);

      String query = "DELETE FROM pokemon WHERE pokedex_number=?";
      PreparedStatement psts = conn.prepareStatement(query);
      psts.setInt(1, id);
      psts.executeUpdate();

    } catch (SQLException e) {
      System.out.println("Cannot connect to database");
      e.printStackTrace();
    }
  }

  public Pokemon getRandomPokemon() {
    Random random = new Random();
    int randomId = random.nextInt(1, 150);

    return findPokemonById(randomId);
  }
}
