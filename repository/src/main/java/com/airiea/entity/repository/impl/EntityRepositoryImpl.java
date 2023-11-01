package com.airiea.entity.repository.impl;

import com.airiea.entity.common.factory.EntityFactory;
import com.airiea.entity.model.dao.EntityDAO;
import com.airiea.entity.model.dto.EntityDTO;
import com.airiea.entity.repository.EntityRepository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityRepositoryImpl implements EntityRepository {
    private final DynamoDBMapper dynamoDBMapper;
    private final EntityFactory entityFactory;

    /**
     * Constructor for EntityDaoImpl.
     *
     * @param dynamoDBMapper The DynamoDBMapper to interact with the database.
     */
    public EntityRepositoryImpl(final DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = Objects.requireNonNull(dynamoDBMapper, "Mapper cannot be null");
        this.entityFactory = EntityFactory.INSTANCE;
    }

    /**
     * Retrieve a Entity by its ID.
     *
     * @param entityId The ID of the entity.
     * @return The Entity object.
     */
    @Override
    public List<EntityDTO> getEntityDTOsById(String entityId) {
        final List<EntityDAO> entityDAOList = this.getEntityDAOsById(entityId);

        return entityDAOList.stream()
                .map(entityFactory::daoToDto)
                .collect(Collectors.toList());
    }



    @Override
    public EntityDAO getEntityDAOByIdAgentName(String entityId, String agentName) {
        EntityDAO hashKeys = new EntityDAO();
        hashKeys.setEntityId(entityId);
        hashKeys.setAgentName(agentName);

        return dynamoDBMapper.load(hashKeys);
    }

    @Override
    public EntityDAO createEntityDAO(EntityDAO entityDAO) {
        if (!Objects.isNull(dynamoDBMapper.load(entityDAO))) {
            throw new IllegalArgumentException("Failed to create entityDAO " + entityDAO + ", item already exists.");
        }

        dynamoDBMapper.save(entityDAO);
        return entityDAO;
    }

    @Override
    public EntityDAO updateEntityDAO(EntityDAO entityDAO) {
        if (Objects.isNull(dynamoDBMapper.load(entityDAO))) {
            throw new IllegalArgumentException("Failed to update entityDAO " + entityDAO + ", item not found.");
        }

        dynamoDBMapper.save(entityDAO);
        return entityDAO;
    }

    @Override
    public List<EntityDAO> getEntityDAOsById(String entityId) {
        EntityDAO hashKeys = new EntityDAO();
        hashKeys.setAgentName(entityId);

        DynamoDBQueryExpression<EntityDAO> queryExpression = new DynamoDBQueryExpression<EntityDAO>()
                .withHashKeyValues(hashKeys)
                .withConsistentRead(false);

        return dynamoDBMapper.query(EntityDAO.class, queryExpression);
    }

}
