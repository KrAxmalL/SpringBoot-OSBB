package com.example.osbb.logging;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "logs")
public class LogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;

  private String level;

  @Column(name = "logger_name", nullable = true)
  private String loggerName;

  @Basic(optional = true)
  private String marker;

  @Column(name = "class_name")
  private String className;

  @Column(name = "method_name")
  private String methodName;

  private String message;

  @Column(name = "request_id")
  private String requestId;

  private Long time;
}
