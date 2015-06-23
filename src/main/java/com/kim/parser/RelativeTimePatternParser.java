package com.kim.parser;

import com.kim.parser.model.LogEvent;
import com.kim.parser.model.LogEventItem;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by phuonghqh on 6/22/15.
 */
public class RelativeTimePatternParser implements Log4JParser {

  private static final RelativeTimePatternParser instance = new RelativeTimePatternParser();

  public String parse(String logLine, LogEvent logEvent) {
    int end = 0;
    Long value = null;
    for (int i = 1; i < logLine.length(); i++) {
      String str = logLine.substring(0, i);
      try {
        value = Long.parseLong(str);
      }
      catch (Exception e) {
      }
      if (value == null) {
        end = i - 1;
        break;
      }
    }
    if (end > 0) {
      logLine = logLine.substring(end);
      logEvent.getItems().add(LogEventItem.LogEventItemBuilder.logEventItem()
        .withTypeName("RelativeTime").withInstance(value).build());
    }
    return logLine;
  }

  public static RelativeTimePatternParser get() {
    return instance;
  }
}
