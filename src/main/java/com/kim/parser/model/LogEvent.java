package com.kim.parser.model;

import java.util.ArrayList;
import java.util.List;

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

  public String toString() {
    return items.toString();
  }
}
