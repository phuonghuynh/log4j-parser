package com.kim;

import com.kim.parser.LevelPatternParser;
import com.kim.parser.LiteralPatternParser;
import com.kim.parser.LogEvent;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.pattern.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

  private static Log LOG = LogFactory.getLog(App.class);

  private static String patternLayout = "%p %d{ISO8601} %r %c [%t] %m%n";//%r [%t] %p %c %x - %m%n

  public static void sampleLogEntries() {
    for (int i = 0; i < 10; i++) {
      LOG.error("example " + i);
      LOG.info("example " + i);
      LOG.debug("example " + i);
      LOG.warn("example " + i);
    }
  }

  private static String getFormatFromConverters(List converters) {
    StringBuffer buffer = new StringBuffer();
    for (Iterator iter = converters.iterator(); iter.hasNext(); ) {
      LoggingEventPatternConverter converter = (LoggingEventPatternConverter) iter.next();
      if (converter instanceof DatePatternConverter) {
        buffer.append("TIMESTAMP");
      }
      else if (converter instanceof MessagePatternConverter) {
        buffer.append("MESSAGE");
      }
      else if (converter instanceof LoggerPatternConverter) {
        buffer.append("LOGGER");
      }
      else if (converter instanceof ClassNamePatternConverter) {
        buffer.append("CLASS");
      }
      else if (converter instanceof RelativeTimePatternConverter) {
        buffer.append("PROP(RELATIVETIME)");
      }
      else if (converter instanceof ThreadPatternConverter) {
        buffer.append("THREAD");
      }
      else if (converter instanceof NDCPatternConverter) {
        buffer.append("NDC");
      }
      else if (converter instanceof LiteralPatternConverter) {
        LiteralPatternConverter literal = (LiteralPatternConverter) converter;
        //format shouldn't normally take a null, but we're getting a literal, so passing in the buffer will work
        literal.format(null, buffer);
      }
      else if (converter instanceof SequenceNumberPatternConverter) {
        buffer.append("PROP(log4jid)");
      }
      else if (converter instanceof LevelPatternConverter) {
        buffer.append("LEVEL");
      }
      else if (converter instanceof MethodLocationPatternConverter) {
        buffer.append("METHOD");
      }
      else if (converter instanceof FullLocationPatternConverter) {
        buffer.append("PROP(locationInfo)");
      }
      else if (converter instanceof LineLocationPatternConverter) {
        buffer.append("LINE");
      }
      else if (converter instanceof FileLocationPatternConverter) {
        buffer.append("FILE");
      }
      else if (converter instanceof PropertiesPatternConverter) {
//                PropertiesPatternConverter propertiesConverter = (PropertiesPatternConverter) converter;
//                String option = propertiesConverter.getOption();
//                if (option != null && option.length() > 0) {
//                    buffer.append("PROP(" + option + ")");
//                } else {
        buffer.append("PROP(PROPERTIES)");
//                }
      }
      else if (converter instanceof LineSeparatorPatternConverter) {
        //done
      }
    }
    return buffer.toString();
  }

  public static void main(String[] args) throws IOException, IllegalAccessException {
//    Main.main(args);
//    sampleLogEntries();
    String input = OptionConverter.convertSpecialChars(patternLayout);
    List converters = new ArrayList();
    List fields = new ArrayList();
    Map converterRegistry = null;
    PatternParser.parse(input, converters, fields, converterRegistry, PatternParser.getPatternLayoutRules());


    for (Object c : converters) {
//      LoggingEventPatternConverter converter = (LoggingEventPatternConverter) c;
//      System.out.println(converter);
      /*org.apache.log4j.pattern.LevelPatternConverter@6d5380c2
org.apache.log4j.pattern.LiteralPatternConverter@45ff54e6
org.apache.log4j.pattern.DatePatternConverter@2328c243
org.apache.log4j.pattern.LiteralPatternConverter@bebdb06
org.apache.log4j.pattern.RelativeTimePatternConverter@7a4f0f29
org.apache.log4j.pattern.LiteralPatternConverter@45283ce2
org.apache.log4j.pattern.LoggerPatternConverter@2077d4de
org.apache.log4j.pattern.LiteralPatternConverter@7591083d
org.apache.log4j.pattern.ThreadPatternConverter@77a567e1
org.apache.log4j.pattern.LiteralPatternConverter@736e9adb
org.apache.log4j.pattern.MessagePatternConverter@6d21714c
org.apache.log4j.pattern.LineSeparatorPatternConverter@108c4c35*/
    }

    Files.lines(Paths.get("log4j-parser.log")).forEach(logLine -> {

      try {
        String line = logLine;
        com.kim.parser.LogEvent logEvent = new LogEvent();
        while (line.length() > 0) {
          for (Object converter : converters) {
            if (line.length() == 0) {
              break;
            }

            if (converter instanceof LevelPatternConverter) {
              line = LevelPatternParser.get().parse(line, logEvent);
            }
            else if (converter instanceof LiteralPatternConverter) {
              String literal = (String) FieldUtils.getField(LiteralPatternConverter.class, "literal", true).get(converter);
              line = LiteralPatternParser.get(literal).parse(line, logEvent);
            }
            else {
              line = "";
            }
          }

        }

        System.out.println(logEvent);
      }
      catch (Exception e) {
        e.printStackTrace();
      }

//      for (Object converter : converters) {
//
//      }
    });

//    LogFileParser logFileParser = new LogFileParser(new java.io.File("/Users/phuonghqh/Documents/working/log4j-parser/log4j-parser.log"));
//    LogBrokerMonitor logBrokerMonitor = new LogBrokerMonitor(Log);
//
//    logFileParser.parse();

//    converters.forEach(converter -> {
//      converter.
//    });
//    String formatter = getFormatFromConverters(converters);
//    System.out.println(formatter);

//    String existingLogEntry = "2014-01-20 14:48:00,000 [thread-0] DEBUG (MyClass.java) - Some message";
//    String logPattern = "<substitute this with Log4j pattern that matches the above log entry>";
//
//    LogEntry entry = Parser.parse(logPattern, existingLogEntry);
//    System.out.println("Date: " + entry.getDate());
//    System.out.println("Time: " + entry.getTime());
//    System.out.println("Thread: " + entry.getThread());
//    System.out.println("Log level: " + entry.getLogLevel());
//    System.out.println("Class: " + entry.getClassName());
//    System.out.println("Message: " + entry.getMessage());
  }
}
