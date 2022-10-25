package com.example.osbb.web;

import com.example.osbb.domain.dto.ErrorResponse;
import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.domain.dto.advertisement.UpdateAdvertisementDTO;
import com.example.osbb.exception.EntityNotFoundException;
import com.example.osbb.exception.EntityValidationException;
import com.example.osbb.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

  private final AdvertisementService advertisementService;

  @GetMapping
  public Iterable<AdvertisementDetailsDTO> getAllAdvertisements() {
    return advertisementService.getAllAdvertisements();
  }

  @GetMapping("/{advertisementId}")
  public AdvertisementDetailsDTO getAdvertisementById(@PathVariable Integer advertisementId) {
    return advertisementService.getAdvertisementById(advertisementId);
  }

  @PostMapping
  public ResponseEntity<?> addAdvertisement(@RequestBody AddAdvertisementDTO addAdvertisementDTO) {
    advertisementService.addAdvertisement(addAdvertisementDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{advertisementId}")
  public ResponseEntity<?> updateAdvertisement(
      @PathVariable Integer advertisementId,
      @RequestBody UpdateAdvertisementDTO updateAdvertisementDTO) {
    advertisementService.updateAdvertisement(advertisementId, updateAdvertisementDTO);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{advertisementId}")
  public ResponseEntity<?> deleteAdvertisement(@PathVariable Integer advertisementId) {
    advertisementService.deleteAdvertisement(advertisementId);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
    final int status = HttpStatus.NOT_FOUND.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }

  @ExceptionHandler(EntityValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleEntityValidationException(EntityValidationException ex) {
    final int status = HttpStatus.BAD_REQUEST.value();
    return ErrorResponse.builder().status(status).message(ex.getMessage()).build();
  }
}
