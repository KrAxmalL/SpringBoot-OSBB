package com.example.project.service;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.domain.Advertisement;
import java.util.List;

public interface AdvertisementService {

  List<Advertisement> getAllAdvertisements();

  Advertisement getAdvertisementById(Integer advertisementId);

  Advertisement addAdvertisement(AddAdvertisementDTO addAdvertisementDTO);
}
