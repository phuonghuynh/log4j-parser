package com.kim.parser;

import org.apache.log4j.pattern.DatePatternConverter;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class DatePatternParser implements Log4JParser {

  private static final DatePatternParser instance = new DatePatternParser();

  private DateFormat dateFormat;

  public String parse(String logLine, LogEvent logEvent) {
    int end = 0;
    for (int i = 1; i < logLine.length(); i++) {
      String dateStr = logLine.substring(0, i);
      Date date = null;
      try {
        date = dateFormat.parse(dateStr);
      }
      catch (Exception e) {
      }
      if (date != null) {
        end = i;
        break;
      }
    }
    if (end > 0) {
      logLine = logLine.substring(0, end);

    }
    return null;
  }

  public static DatePatternParser get(DateFormat dateFormat) {
    instance.dateFormat = dateFormat;
    return instance;
  }
}
