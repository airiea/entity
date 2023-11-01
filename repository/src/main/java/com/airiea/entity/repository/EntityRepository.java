package com.airiea.entity.repository;

import com.airiea.entity.model.dao.EntityDAO;
import com.airiea.entity.model.dto.EntityDTO;

import java.util.List;

/**
 * Data Access Object (DAO) interface for tasks.
 * This provides methods to perform operations on tasks.
 */
public interface EntityRepository {
    List<EntityDTO> getEntityDTOsById(String entityId);

    EntityDAO getEntityDAOByIdAgentName(String entityId, String agentName);
    EntityDAO createEntityDAO(EntityDAO entityDAO);
    EntityDAO updateEntityDAO(EntityDAO entityDAO);
    List<EntityDAO> getEntityDAOsById(String entityId);
}
