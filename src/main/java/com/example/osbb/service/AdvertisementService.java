package com.example.osbb.service;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.domain.dto.advertisement.UpdateAdvertisementDTO;

public interface AdvertisementService {

  Iterable<AdvertisementDetailsDTO> getAllAdvertisements();

  AdvertisementDetailsDTO getAdvertisementById(Integer advertisementId);

  void addAdvertisement(AddAdvertisementDTO addAdvertisementDTO);

  void updateAdvertisement(Integer advertisementId, UpdateAdvertisementDTO updateAdvertisementDTO);

  void deleteAdvertisement(Integer advertisementId);
}
