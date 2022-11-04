package com.example.osbb.service;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.domain.dto.advertisement.UpdateAdvertisementDTO;
import com.example.osbb.domain.mapper.AdvertisementMapper;
import com.example.osbb.domain.model.Advertisement;
import com.example.osbb.domain.model.User;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.repository.AdvertisementRepository;
import com.example.osbb.repository.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

  private final UserRepository userRepository;
  private final AdvertisementRepository advertisementRepository;
  private final AdvertisementMapper advertisementMapper;

  @Override
  public Iterable<AdvertisementDetailsDTO> getAllAdvertisements() {
    final List<AdvertisementDetailsDTO> advertisements = new ArrayList<>();
    advertisementRepository
        .findAll()
        .forEach(
            advertisement ->
                advertisements.add(advertisementMapper.toAdvertisementDetailsDTO(advertisement)));
    return advertisements;
  }

  @Override
  public AdvertisementDetailsDTO getAdvertisementById(Integer advertisementId) {
    return advertisementRepository
        .findById(advertisementId)
        .map(advertisementMapper::toAdvertisementDetailsDTO)
        .orElseThrow(
            () -> new EntityNotFoundException("Advertisement with given id doesn't exist"));
  }

  @Override
  public void addAdvertisement(AddAdvertisementDTO addAdvertisementDTO) {
    final User author =
        userRepository
            .findById(addAdvertisementDTO.getAuthorId())
            .orElseThrow(
                () -> new EntityValidationException("Author with given id doesn't exists"));

    final Advertisement advertisement = advertisementMapper.toAdvertisement(addAdvertisementDTO);
    advertisement.setCreatedAt(Instant.now().toEpochMilli());
    advertisement.setAuthor(author);
    advertisementRepository.save(advertisement);
  }

  @Override
  public void updateAdvertisement(
      Integer advertisementId, UpdateAdvertisementDTO updateAdvertisementDTO) {
    Optional<Advertisement> advertisementOpt = advertisementRepository.findById(advertisementId);
    if (advertisementOpt.isEmpty()) {
      throw new EntityNotFoundException("Advertisement with given id doesn't exist");
    }

    Advertisement advertisement = advertisementOpt.get();

    if (Objects.nonNull(updateAdvertisementDTO.getTitle())) {
      advertisement.setTitle(updateAdvertisementDTO.getTitle());
    }

    if (Objects.nonNull(updateAdvertisementDTO.getContent())) {
      advertisement.setContent(updateAdvertisementDTO.getContent());
    }

    advertisementRepository.save(advertisement);
  }

  @Override
  public void deleteAdvertisement(Integer advertisementId) {
    if (!advertisementRepository.existsById(advertisementId)) {
      throw new EntityNotFoundException("Advertisement with given id doesn't exist");
    }
    advertisementRepository.deleteById(advertisementId);
  }
}
