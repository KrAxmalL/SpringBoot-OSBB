package com.example.osbb.web.controller;

import com.example.osbb.domain.dto.advertisement.AddAdvertisementDTO;
import com.example.osbb.domain.model.User;
import com.example.osbb.service.AdvertisementService;
import com.example.osbb.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdvertisementPageController {

  private final AdvertisementService advertisementService;
  private final UserService userService;

  @GetMapping("/pages/index")
  public String getMainPage() {
    return "index";
  }

  @GetMapping("/pages/advertisements")
  public String getAdvertisementsPage(Model model) {
    model.addAttribute("advertisements", advertisementService.getAllAdvertisements());
    return "advertisements";
  }

  @GetMapping("/pages/add-advertisement")
  public String getAddAdvertisementForm(Model model) {
    final User user = userService.getUserFromSecurityContext();
    final AddAdvertisementDTO addAdvertisementDTO =
        AddAdvertisementDTO.builder().authorId(user.getId()).build();
    model.addAttribute("addAdvertisementDTO", addAdvertisementDTO);
    return "add-advertisement";
  }

  @PostMapping("/pages/add-advertisement")
  public String addAdvertisementByForm(
      @Valid AddAdvertisementDTO addAdvertisementDTO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.warn("Errors from form: {}", bindingResult.getAllErrors());
      return "add-advertisement";
    }
    advertisementService.addAdvertisement(addAdvertisementDTO);
    return "redirect:/pages/advertisements";
  }
}
