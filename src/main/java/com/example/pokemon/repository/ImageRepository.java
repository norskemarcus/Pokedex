package com.example.pokemon.repository;

import com.example.pokemon.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;

// is the same as BookDAO in tutorial
public class ImageRepository {

  private final String databaseURL = "jdbc:mysql://localhost:3306/pokedex";
  private final String user = "pokedex_user";
  private final String password =  "Vestergaard3660";



  public Image getImageFromDatabase(int id) {

    Image image = null;

    try {
      Connection connection = DriverManager.getConnection(databaseURL, user, password);

      String query = "SELECT * from images WHERE pokedex_number=?";

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
        // id is set in line 30,   preparedStatement.setInt(1, id);
        image.setName(name);
        image.setBase64Image(base64Image);
      }

    } catch (SQLException | IOException ex) {
      System.out.println("Could not connect to image database");
      ex.printStackTrace();
    }
    return image;
  }


}
