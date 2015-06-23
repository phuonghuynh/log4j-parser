package com.kim.parser;

import com.kim.parser.model.LogEvent;

/**
 * Created by phuonghqh on 6/22/15.
 */
public interface Log4JParser {
  String parse(String logLine, LogEvent logEvent);
}
