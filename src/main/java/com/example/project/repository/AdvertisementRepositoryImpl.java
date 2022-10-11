package com.example.project.repository;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.domain.Advertisement;
import com.example.project.domain.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisementRepositoryImpl implements AdvertisementRepository {

  private static final List<Advertisement> advertisements =
      new ArrayList<>(List.of(
          Advertisement.builder()
              .id(1)
              .title("Some title")
              .content("Some content")
              .author(Employee.builder().id(1).firstName("John").lastName("Doe").build())
              .build(),
          Advertisement.builder()
              .id(2)
              .title("New title")
              .content("New content")
              .author(Employee.builder().id(2).firstName("Tom").lastName("Roberts").build())
              .build()));

  private int nextId = 3;

  @Override
  public List<Advertisement> getAllAdvertisement() {
    return advertisements;
  }

  @Override
  public Optional<Advertisement> getAdvertisementById(Integer advertisementId) {
    return advertisements.stream()
        .filter(advertisement -> advertisement.getId().compareTo(advertisementId) == 0)
        .findFirst();
  }

  @Override
  public Advertisement addAdvertisement(AddAdvertisementDTO addAdvertisementDTO) {
    final Advertisement advertisementToAdd =
        Advertisement.builder()
            .id(nextId++)
            .title(addAdvertisementDTO.getTitle())
            .content(addAdvertisementDTO.getContent())
            .author(
                advertisements.stream()
                    .map(Advertisement::getAuthor)
                    .filter(
                        author -> author.getId().compareTo(addAdvertisementDTO.getAuthorId()) == 0)
                    .findFirst()
                    .get())
            .build();
    advertisements.add(advertisementToAdd);
    return advertisementToAdd;
  }
}
