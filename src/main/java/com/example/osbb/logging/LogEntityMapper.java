package com.example.osbb.logging;

import java.util.Optional;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;

public class LogEntityMapper {

  private static final String REQUEST_ID_KEY = "requestId";

  public LogEntity toLogEntity(LogEvent logEvent) {
    final Optional<StackTraceElement> stackTraceElement = Optional.ofNullable(logEvent.getSource());
    return LogEntity.builder()
        .level(logEvent.getLevel().name())
        .loggerName(logEvent.getLoggerName())
        .marker(Optional.ofNullable(logEvent.getMarker()).map(Marker::getName).orElse(null))
        .className(stackTraceElement.map(StackTraceElement::getClassName).orElse(null))
        .methodName(stackTraceElement.map(StackTraceElement::getMethodName).orElse(null))
        .message(logEvent.getMessage().getFormattedMessage())
        .requestId(logEvent.getContextData().getValue(REQUEST_ID_KEY))
        .time(logEvent.getTimeMillis())
        .build();
  }
}
