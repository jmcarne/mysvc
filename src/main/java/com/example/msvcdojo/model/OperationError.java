package com.example.msvcdojo.model;

/**
 * Created by Josep Maria on 06/07/2016.
 */
public class OperationError {

    String id;
    String entityType;
    String originSystem;
    String destinationSystem;
    String processType;

    public OperationError(String destinationSystem, String entityType, String id, String originSystem, String processType) {
        this.destinationSystem = destinationSystem;
        this.entityType = entityType;
        this.id = id;
        this.originSystem = originSystem;
        this.processType = processType;
    }

    public OperationError() {
    }

    public String getDestinationSystem() {
        return destinationSystem;
    }

    public void setDestinationSystem(String destinationSystem) {
        this.destinationSystem = destinationSystem;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginSystem() {
        return originSystem;
    }

    public void setOriginSystem(String originSystem) {
        this.originSystem = originSystem;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }
}
