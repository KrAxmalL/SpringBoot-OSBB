package com.example.osbb.domain.dto.advertisement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAdvertisementDTO {

  @NotBlank(message = "Title must not be empty")
  private String title;

  @NotBlank(message = "Content must not be empty")
  private String content;

  @NotNull(message = "Author id must not be empty")
  private Integer authorId;
}
