package com.airiea.entity.common.factory;

import com.airiea.entity.model.dao.EntityDAO;
import com.airiea.entity.model.dto.EntityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityFactory {
    EntityFactory INSTANCE = Mappers.getMapper(EntityFactory.class);
    EntityDTO daoToDto(EntityDAO taskDAO);
    EntityDAO dtoToDao(EntityDTO taskDTO);

}
