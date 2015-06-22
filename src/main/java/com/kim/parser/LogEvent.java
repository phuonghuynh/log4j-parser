package com.kim.parser;

import org.apache.log4j.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LogEvent {

  private List<LogEventItem> items = new ArrayList<>();

  public List<LogEventItem> getItems() {
    return items;
  }

  public void setItems(List<LogEventItem> items) {
    this.items = items;
  }

  @Override
  public String toString() {
    return items.toString();
  }
}
