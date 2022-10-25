package com.example.osbb.domain.dto.advertisement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAdvertisementDTO {

  private String title;
  private String content;
  private Integer authorId;
}
