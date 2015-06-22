package com.kim.parser;

/**
 * Created by phuonghqh on 6/22/15.
 */
public interface Log4JParser {
  String parse(String logLine, LogEvent logEvent);
}
