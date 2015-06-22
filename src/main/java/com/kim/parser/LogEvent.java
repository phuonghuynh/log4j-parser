package com.kim.parser;

import org.apache.log4j.Level;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LogEvent {

  private Level level;

  private String literal;

  public String getLiteral() {
    return literal;
  }

  public void setLiteral(String literal) {
    this.literal = literal;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public String toString() {
    return "LogEvent{" +
      "level=" + level +
      ", literal='" + literal + '\'' +
      '}';
  }
}
