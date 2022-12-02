package com.example.osbb.web.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.osbb.config.DisabledSecurityTestConfig;
import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.service.AdvertisementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(
    controllers = AdvertisementController.class,
    properties = "spring.main.allow-bean-definition-overriding=true")
@Import(DisabledSecurityTestConfig.class)
public class AdvertisementControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AdvertisementService advertisementService;

  @Test
  public void shouldReturnAllAdvertisements() throws Exception {
    AdvertisementDetailsDTO expected =
        AdvertisementDetailsDTO.builder()
            .id(1)
            .title("test title")
            .content("test content")
            .createdAt(1L)
            .author(
                AdvertisementDetailsDTO.Author.builder()
                    .id(1)
                    .firstName("test first name")
                    .lastName("test last name")
                    .patronymic("test patronymic")
                    .build())
            .build();
    when(advertisementService.getAllAdvertisements()).thenReturn(List.of(expected));

    MvcResult result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/advertisements"))
            .andExpect(status().isOk())
            .andReturn();

    String jsonResponse = result.getResponse().getContentAsString();
    AdvertisementDetailsDTO[] resultArray =
        new ObjectMapper().readValue(jsonResponse, AdvertisementDetailsDTO[].class);

    Assertions.assertEquals(resultArray.length, 1);
    Assertions.assertEquals(expected, resultArray[0]);
  }

  @Test
  public void shouldReturnAllAdvertisementsByIdIfExists() throws Exception {
    AdvertisementDetailsDTO expected =
        AdvertisementDetailsDTO.builder()
            .id(1)
            .title("test title")
            .content("test content")
            .createdAt(1L)
            .author(
                AdvertisementDetailsDTO.Author.builder()
                    .id(1)
                    .firstName("test first name")
                    .lastName("test last name")
                    .patronymic("test patronymic")
                    .build())
            .build();
    when(advertisementService.getAdvertisementById(1)).thenReturn(expected);

    MvcResult result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/advertisements/1"))
            .andExpect(status().isOk())
            .andReturn();

    String jsonResponse = result.getResponse().getContentAsString();
    AdvertisementDetailsDTO actual =
        new ObjectMapper().readValue(jsonResponse, AdvertisementDetailsDTO.class);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void shouldReturnNotFoundAndErrorResponseWhenRequestedAdvertisementByIdNotExists()
      throws Exception {
    when(advertisementService.getAdvertisementById(anyInt()))
        .thenThrow(new EntityNotFoundException("error occurred"));

    MvcResult result =
        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/advertisements/1"))
            .andExpect(status().isNotFound())
            .andReturn();
    String jsonResponse = result.getResponse().getContentAsString();
    ErrorResponse actual = new ObjectMapper().readValue(jsonResponse, ErrorResponse.class);

    Assertions.assertEquals(actual.getStatus(), 404);
    Assertions.assertEquals(actual.getMessage(), "error occurred");
  }
}
