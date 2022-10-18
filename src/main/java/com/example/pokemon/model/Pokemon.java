package com.example.pokemon.model;

public class Pokemon {


  private int id;
  private String name;
  private int speed;
  private int specialDefence;
  private int specialAttack;
  private int defence;
  private int attack;
  private int hp;
  private String primType;
  private String secType;


  public Pokemon(){}


  public Pokemon(int id, String name, int speed, int specialDefence, int specialAttack,
                 int defence, int attack, int hp, String primType, String secType) {
    this.id = id;
    this.name = name;
    this.speed = speed;
    this.specialDefence = specialDefence;
    this.specialAttack = specialAttack;
    this.defence = defence;
    this.attack = attack;
    this.hp = hp;
    this.primType = primType;
    this.secType = secType;
  }

  @Override
  public String toString() {
    return "Pokemon" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", speed=" + speed +
        ", specialDefence=" + specialDefence +
        ", specialAttack=" + specialAttack +
        ", defence=" + defence +
        ", attack=" + attack +
        ", hp=" + hp +
        ", primType='" + primType + '\'' +
        ", secType='" + secType + '\'';
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

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getSpecialDefence() {
    return specialDefence;
  }

  public void setSpecialDefence(int specialDefence) {
    this.specialDefence = specialDefence;
  }

  public int getSpecialAttack() {
    return specialAttack;
  }

  public void setSpecialAttack(int specialAttack) {
    this.specialAttack = specialAttack;
  }

  public int getDefence() {
    return defence;
  }

  public void setDefence(int defence) {
    this.defence = defence;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public int getHp() {
    return hp;
  }

  public void setHp(int hp) {
    this.hp = hp;
  }

  public String getPrimType() {
    return primType;
  }

  public void setPrimType(String primType) {
    this.primType = primType;
  }

  public String getSecType() {
    return secType;
  }

  public void setSecType(String secType) {
    this.secType = secType;
  }
}

