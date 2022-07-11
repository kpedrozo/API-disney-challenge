package com.alkemy.challenge.disney.service;

import com.alkemy.challenge.disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {

    List<GenderDTO> getAllGenders();

    GenderDTO save(GenderDTO dto);
}
