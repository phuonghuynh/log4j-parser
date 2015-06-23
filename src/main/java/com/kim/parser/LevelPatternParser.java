package com.kim.parser;

import com.kim.parser.model.LogEvent;
import com.kim.parser.model.LogEventItem.LogEventItemBuilder;
import org.apache.log4j.Level;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LevelPatternParser implements Log4JParser {

  private static final Log4JParser instance = new LevelPatternParser();

  public String parse(String logLine, LogEvent logEvent) {
    Level level = null;
    if (logLine.startsWith(Level.ERROR.toString())) {
      logLine = logLine.substring(Level.ERROR.toString().length());
      level = Level.ERROR;
    }
    else if (logLine.startsWith(Level.DEBUG.toString())) {
      logLine = logLine.substring(Level.DEBUG.toString().length());
      level = Level.DEBUG;
    }
    else if (logLine.startsWith(Level.INFO.toString())) {
      logLine = logLine.substring(Level.INFO.toString().length());
      level = Level.INFO;
    }
    else if (logLine.startsWith(Level.TRACE.toString())) {
      logLine = logLine.substring(Level.TRACE.toString().length());
      level = Level.TRACE;
    }
    else if (logLine.startsWith(Level.WARN.toString())) {
      logLine = logLine.substring(Level.WARN.toString().length());
      level = Level.WARN;
    }

    if (level != null) {
      logEvent.getItems().add(LogEventItemBuilder.logEventItem()
        .withTypeName("Level")
        .withInstance(level).build());
    }
    return logLine;
  }

  public static Log4JParser get() {
    return instance;
  }
}
