package com.example.project.service;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.domain.Advertisement;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.exception.EntityValidationException;
import com.example.project.repository.AdvertisementRepository;
import com.example.project.validation.AdvertisementValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

  private final AdvertisementRepository advertisementRepository;
  private final AdvertisementValidator advertisementValidator;

  @Autowired
  public AdvertisementServiceImpl(
      AdvertisementRepository advertisementRepository,
      AdvertisementValidator advertisementValidator) {
    this.advertisementRepository = advertisementRepository;
    this.advertisementValidator = advertisementValidator;
  }

  @Override
  public List<Advertisement> getAllAdvertisements() {
    return advertisementRepository.getAllAdvertisement();
  }

  @Override
  public Advertisement getAdvertisementById(Integer advertisementId) {
    return advertisementRepository
        .getAdvertisementById(advertisementId)
        .orElseThrow(
            () -> new EntityNotFoundException("Advertisement with given id doesn't exist"));
  }

  @Override
  public Advertisement addAdvertisement(AddAdvertisementDTO addAdvertisementDTO) {
    if (advertisementValidator.isValid(addAdvertisementDTO)) {
      return advertisementRepository.addAdvertisement(addAdvertisementDTO);
    } else {
      throw new EntityValidationException("Advertisement is not valid");
    }
  }
}
