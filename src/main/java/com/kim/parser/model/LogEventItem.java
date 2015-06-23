package com.kim.parser.model;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LogEventItem {

  private String typeName;

  private Object instance;

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Object getInstance() {
    return instance;
  }

  public void setInstance(Object instance) {
    this.instance = instance;
  }

  public String toString() {
    return "LogEventItem{" +
      "typeName='" + typeName + '\'' +
      ", instance=" + instance +
      '}';
  }

  public static class LogEventItemBuilder {
    private LogEventItem logEventItem;

    private LogEventItemBuilder() {
      logEventItem = new LogEventItem();
    }

    public LogEventItemBuilder withTypeName(String typeName) {
      logEventItem.typeName = typeName;
      return this;
    }

    public LogEventItemBuilder withInstance(Object instance) {
      logEventItem.instance = instance;
      return this;
    }

    public static LogEventItemBuilder logEventItem() {
      return new LogEventItemBuilder();
    }

    public LogEventItem build() {
      return logEventItem;
    }
  }
}
