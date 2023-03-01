package ru.spbu.apcyb.svp.tasks.task1;

import java.util.logging.LogRecord;



class MyFormatter extends java.util.logging.Formatter {
  @Override
  public String format(LogRecord logRecord) {
    return formatMessage(logRecord).concat(System.lineSeparator());
  }
}
