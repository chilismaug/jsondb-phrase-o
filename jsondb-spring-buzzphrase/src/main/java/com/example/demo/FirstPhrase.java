package com.example.demo;

import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;
import io.jsondb.annotation.Secret;

/**
 * A test Pojo representing a buzz phrase.
 * @version 0.1.0 3-Jan-2019
 */
@Document(collection = "firstphrases", schemaVersion= "1.0")
public class FirstPhrase {
  //This field will be used as a primary key, every POJO should have one
  @Id
  private String id;
  private String phrase;

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getPhrase() { return phrase; }
  public void setPhrase(String phrase) { this.phrase = phrase; }

}
