package com.airiea.entity.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntityDTO {
    @JsonProperty("entity_id")
    String entityId;

    @JsonProperty("agent_name")
    String agentName;

    @JsonProperty("task_count")
    Integer taskCount;

    @JsonProperty("created_date")
    Date createdDate;

    @JsonProperty("updated_date")
    Date updatedDate;
}
