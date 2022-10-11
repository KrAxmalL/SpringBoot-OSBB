package com.example.project.repository;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.domain.Advertisement;
import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository {

  List<Advertisement> getAllAdvertisement();

  Optional<Advertisement> getAdvertisementById(Integer advertisementId);

  Advertisement addAdvertisement(AddAdvertisementDTO addAdvertisementDTO);
}
