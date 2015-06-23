package com.kim.parser;

import com.kim.parser.model.LogEvent;
import com.kim.parser.model.LogEventItem;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LiteralPatternParser implements Log4JParser {

  private static final LiteralPatternParser instance = new LiteralPatternParser();

  private String literal;

  public String parse(String logLine, LogEvent logEvent) {
    if (logLine.startsWith(literal)) {
      logLine = logLine.substring(literal.length());
      logEvent.getItems().add(LogEventItem.LogEventItemBuilder.logEventItem().withTypeName("Literal").withInstance(literal).build());
    }
    return logLine;
  }

  public static Log4JParser get(String literal) {
    instance.literal = literal;
    return instance;
  }
}
