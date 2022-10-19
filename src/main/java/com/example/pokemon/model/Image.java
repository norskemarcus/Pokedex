package com.example.pokemon.model;

import javax.persistence.Column;
import java.sql.Blob;

public class Image {

  private int id;
  private String name;
  private String base64Image;
  private byte[] image;

  public Image() { }

  //TODO: Er dette korrekt constructor???
  public Image(int id, String name, String base64Image) {
    this.id = id;
    this.name = name;
    this.base64Image = base64Image;
  }


  @Column(name="image")
  public byte[] getImage(){
    return this.image;
  }


  /* The field’s getter getBase64Image() will be called by a JSTL tag in the JSP page in
  order to show the image. */
  public String getBase64Image(){
    return base64Image;
  }

/*The field’s setter setBase64Image() will be called by a DAO class that retrieves the image
binary data and converts it to a base64 string. */
  public void setBase64Image(String base64Image){
    this.base64Image = base64Image;
  }

  //TODO: FIXE DENNE
  public void setImageFile(Blob imageFile) {

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public byte[] getImageFile() {
    return image;
  }


}
