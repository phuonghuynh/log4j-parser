package com.kim.parser;

import com.kim.parser.model.LogEvent;
import com.kim.parser.model.LogEventItem;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.Date;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class DatePatternParser implements Log4JParser {

  private static final DatePatternParser instance = new DatePatternParser();

  private DateFormat dateFormat;

  public String parse(String logLine, LogEvent logEvent) {
    int lastParseIndex = -1;
    Date date = null;
    for (int i = 1; i < logLine.length(); i++) {
      String dateStr = logLine.substring(0, i);
      try {
        ParsePosition position = new ParsePosition(0);
        date = dateFormat.parse(dateStr, position);
        if (position.getErrorIndex() == -1) {
          if (lastParseIndex != position.getIndex()) {
            lastParseIndex = position.getIndex();
          }
          else {
            break;
          }
        }
      }
      catch (Exception e) {
      }
    }
    if (lastParseIndex > 0) {
      logLine = logLine.substring(lastParseIndex);
      logEvent.getItems().add(LogEventItem.LogEventItemBuilder.logEventItem()
        .withTypeName("Date").withInstance(date).build());
    }
    return logLine;
  }

  public static DatePatternParser get(DateFormat dateFormat) {
    instance.dateFormat = dateFormat;
    return instance;
  }
}
