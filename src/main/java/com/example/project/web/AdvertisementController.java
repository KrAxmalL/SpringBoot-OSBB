package com.example.project.web;

import com.example.project.domain.AddAdvertisementDTO;
import com.example.project.domain.Advertisement;
import com.example.project.domain.ErrorResponse;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.exception.EntityValidationException;
import com.example.project.service.AdvertisementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {

  @Autowired private AdvertisementService advertisementService;

  @GetMapping
  public List<Advertisement> getAllAdvertisements() {
    return advertisementService.getAllAdvertisements();
  }

  @GetMapping("/{advertisementId}")
  public Advertisement getAdvertisementById(@PathVariable Integer advertisementId) {
    return advertisementService.getAdvertisementById(advertisementId);
  }

  @PostMapping
  public Advertisement addAdvertisement(@RequestBody AddAdvertisementDTO addAdvertisementDTO) {
    return advertisementService.addAdvertisement(addAdvertisementDTO);
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
