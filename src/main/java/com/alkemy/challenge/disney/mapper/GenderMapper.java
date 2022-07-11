package com.alkemy.challenge.disney.mapper;

import com.alkemy.challenge.disney.dto.GenderDTO;
import com.alkemy.challenge.disney.entity.GenderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMapper {

    public GenderEntity genderDTO2Entity(GenderDTO dto) {
        GenderEntity genderEntity = new GenderEntity();
        genderEntity.setName(dto.getName());
        genderEntity.setImage(dto.getImage());
        return genderEntity;
    }

    public GenderDTO genderEntity2DTO(GenderEntity entity) {
        GenderDTO genderDTO = new GenderDTO();
        genderDTO.setId(entity.getId());
        genderDTO.setName(entity.getName());
        genderDTO.setImage(entity.getImage());
        return genderDTO;
    }

    public List<GenderDTO> genderEntityList2DTOList(List<GenderEntity> entities) {
        List <GenderDTO> dtos = new ArrayList<>();
        for (GenderEntity entity : entities) {
            dtos.add(this.genderEntity2DTO(entity));
        }
        return dtos;
    }

}
