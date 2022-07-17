package com.alkemy.challenge.disney.service.impl;

import com.alkemy.challenge.disney.dto.GenderDTO;
import com.alkemy.challenge.disney.entity.GenderEntity;
import com.alkemy.challenge.disney.mapper.GenderMapper;
import com.alkemy.challenge.disney.repository.GenderRepository;
import com.alkemy.challenge.disney.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderMapper genderMapper;

    @Autowired
    private GenderRepository genderRepository;

    public GenderDTO save(GenderDTO dto) {
        GenderEntity entity = genderMapper.genderDTO2Entity(dto);
        GenderEntity entitySaved = genderRepository.save(entity);
        GenderDTO result = genderMapper.genderEntity2DTO(entitySaved);
        return result;
    }

    public GenderDTO getById(Long id) {
        Optional<GenderEntity> oPentity = genderRepository.findById(id);
        GenderEntity entity = oPentity.get();
        GenderDTO result = genderMapper.genderEntity2DTO(entity);
        return result;
    }

    public List<GenderDTO> getAllGenders() {
        List<GenderEntity> entities = genderRepository.findAll();
        List<GenderDTO> result = genderMapper.genderEntityList2DTOList(entities);
        return result;
    }



}
