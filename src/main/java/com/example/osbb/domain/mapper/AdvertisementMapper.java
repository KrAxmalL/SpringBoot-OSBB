package com.example.osbb.domain.mapper;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.dto.advertisement.AdvertisementDetailsDTO;
import com.example.osbb.domain.model.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

  AddAdvertisementDTO toAdvertisementDTO(Advertisement advertisement);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  @Mapping(target = "createdAt", source = "createdAt")
  @Mapping(target = "author.id", source = "author.id")
  @Mapping(target = "author.firstName", source = "author.firstName")
  @Mapping(target = "author.lastName", source = "author.lastName")
  @Mapping(target = "author.patronymic", source = "author.patronymic")
  AdvertisementDetailsDTO toAdvertisementDetailsDTO(Advertisement advertisement);

  @Mapping(target = "title", source = "title")
  @Mapping(target = "content", source = "content")
  Advertisement toAdvertisement(AddAdvertisementDTO addAdvertisementDTO);
}
