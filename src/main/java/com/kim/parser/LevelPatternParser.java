package com.kim.parser;

import org.apache.log4j.Level;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LevelPatternParser implements Log4JParser {

  private static final Log4JParser instance = new LevelPatternParser();

  public String parse(String logLine, LogEvent logEvent) {
    if (logLine.startsWith(Level.ERROR.toString())) {
      logLine = logLine.substring(Level.ERROR.toString().length());
      logEvent.setLevel(Level.ERROR);
    }
    else if (logLine.startsWith(Level.DEBUG.toString())) {
      logLine = logLine.substring(Level.DEBUG.toString().length());
      logEvent.setLevel(Level.DEBUG);
    }
    else if (logLine.startsWith(Level.INFO.toString())) {
      logLine = logLine.substring(Level.INFO.toString().length());
      logEvent.setLevel(Level.INFO);
    }
    else if (logLine.startsWith(Level.TRACE.toString())) {
      logLine = logLine.substring(Level.TRACE.toString().length());
      logEvent.setLevel(Level.TRACE);
    }
    else if (logLine.startsWith(Level.WARN.toString())) {
      logLine = logLine.substring(Level.WARN.toString().length());
      logEvent.setLevel(Level.WARN);
    }
    return logLine;
  }

  public static Log4JParser get() {
    return instance;
  }
}
