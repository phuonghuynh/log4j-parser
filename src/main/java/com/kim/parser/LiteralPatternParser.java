package com.kim.parser;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.log4j.Level;
import org.apache.log4j.pattern.LiteralPatternConverter;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class LiteralPatternParser implements Log4JParser {

  private static final LiteralPatternParser instance = new LiteralPatternParser();

  private String literal;

  public String parse(String logLine, LogEvent logEvent) {
    if (logLine.startsWith(literal)) {
      logLine = logLine.substring(literal.length());
      logEvent.setLiteral(literal);
    }
    return logLine;
  }

  public static Log4JParser get(String literal) {
    instance.literal = literal;
    return instance;
  }
}
