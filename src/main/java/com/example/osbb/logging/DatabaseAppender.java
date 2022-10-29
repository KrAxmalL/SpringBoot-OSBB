package com.example.osbb.logging;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(
    name = "DatabaseAppender",
    category = Core.CATEGORY_NAME,
    elementType = Appender.ELEMENT_TYPE)
public class DatabaseAppender extends AbstractAppender {

  private static LogRepository logRepository;
  private final LogEntityMapper logEntityMapper;

  private DatabaseAppender(String name, Filter filter) {
    super(name, filter, null, true, new Property[0]);
    this.logEntityMapper = new LogEntityMapper();
  }

  @PluginFactory
  public static DatabaseAppender createAppender(
      @PluginAttribute("name") String name, @PluginElement("Filter") Filter filter) {
    return new DatabaseAppender(name, filter);
  }

  @Override
  public void append(LogEvent event) {
    if (logRepository != null) {
      final LogEntity logEntity = logEntityMapper.toLogEntity(event);
      logRepository.save(logEntity);
    }
  }

  public void setLogRepository(LogRepository logRepository) {
    DatabaseAppender.logRepository = logRepository;
  }
}
