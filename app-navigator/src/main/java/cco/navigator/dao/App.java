package cco.navigator.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class App implements Serializable {

  @Id
  private int id;
  private String name;
  private String iua;

  public App() {
  }

  public App(int id, String name, String iua) {
    this.id = id;
    this.name = name;
    this.iua = iua;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIua() {
    return iua;
  }

  public void setIua(String iua) {
    this.iua = iua;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
