package com.example.osbb.domain.dto.advertisement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertisementDetailsDTO {

  private Integer id;
  private String title;
  private String content;
  private Long createdAt;
  private Author author;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Author {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
  }
}
