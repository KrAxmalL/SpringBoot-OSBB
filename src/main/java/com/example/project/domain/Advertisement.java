package com.example.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Advertisement {

  private Integer id;
  private String title;
  private String content;
  private Employee author;
}
