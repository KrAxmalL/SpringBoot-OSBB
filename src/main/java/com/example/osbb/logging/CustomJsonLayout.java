package com.example.osbb.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

@Plugin(
    name = "CustomJsonLayout",
    category = Core.CATEGORY_NAME,
    elementType = Layout.ELEMENT_TYPE,
    printObject = true)
public class CustomJsonLayout extends AbstractStringLayout {

  private final LogEntityMapper logEntityMapper;
  private final ObjectMapper objectMapper;

  protected CustomJsonLayout(Charset charset) {
    super(charset);
    this.objectMapper = new ObjectMapper();
    this.logEntityMapper = new LogEntityMapper();
  }

  @PluginFactory
  public static CustomJsonLayout createLayout(
      @PluginAttribute(value = "charset", defaultString = "UTF-8") Charset charset) {
    return new CustomJsonLayout(charset);
  }

  @Override
  public String toSerializable(LogEvent event) {
    LogEntity logEntity = logEntityMapper.toLogEntity(event);
    try {
      String logJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(logEntity);
      return logJson + '\n';
    } catch (JsonProcessingException e) {
      return "{}";
    }
  }
}
