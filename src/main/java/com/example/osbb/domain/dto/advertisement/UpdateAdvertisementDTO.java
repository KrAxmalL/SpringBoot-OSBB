package com.example.osbb.domain.dto.advertisement;

import com.example.osbb.validation.annotation.NullOrNotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAdvertisementDTO {

  @NullOrNotBlank(message = "Title must be null or not blank")
  private String title;

  @NullOrNotBlank(message = "Content must be null or not blank")
  private String content;
}
