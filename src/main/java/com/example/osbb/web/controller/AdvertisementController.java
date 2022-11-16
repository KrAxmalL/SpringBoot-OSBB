package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.domain.dto.advertisement.UpdateAdvertisementDTO;
import com.example.osbb.service.AdvertisementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Advertisements", description = "CRUD management of advertisement")
@RestController
@RequestMapping("/api/advertisements")
@RequiredArgsConstructor
public class AdvertisementController {

  private final AdvertisementService advertisementService;

  @Operation(summary = "Get all available advertisement with their authors")
  @GetMapping
  public Iterable<AdvertisementDetailsDTO> getAllAdvertisements() {
    return advertisementService.getAllAdvertisements();
  }

  @Operation(summary = "Get advertisement by it's id")
  @GetMapping("/{advertisementId}")
  public AdvertisementDetailsDTO getAdvertisementById(@PathVariable Integer advertisementId) {
    return advertisementService.getAdvertisementById(advertisementId);
  }

  @Operation(summary = "Add new advertisement. Should provide valid title, content and authorId")
  @PostMapping
  public ResponseEntity<?> addAdvertisement(
      @RequestBody @Valid AddAdvertisementDTO addAdvertisementDTO) {
    advertisementService.addAdvertisement(addAdvertisementDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(
      summary =
          "Update advertisement. Should provide only fields which values you want to change. Values must be valid")
  @PutMapping("/{advertisementId}")
  public ResponseEntity<?> updateAdvertisement(
      @PathVariable Integer advertisementId,
      @RequestBody @Valid UpdateAdvertisementDTO updateAdvertisementDTO) {
    advertisementService.updateAdvertisement(advertisementId, updateAdvertisementDTO);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Delete advertisement with provided id. Advertisement with provided id must exists")
  @DeleteMapping("/{advertisementId}")
  public ResponseEntity<?> deleteAdvertisement(@PathVariable Integer advertisementId) {
    advertisementService.deleteAdvertisement(advertisementId);
    return ResponseEntity.noContent().build();
  }
}
