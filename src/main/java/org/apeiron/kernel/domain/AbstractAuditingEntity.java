package org.apeiron.kernel.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified, created by,
 * last modified by attributes.
 * 
 * @param <T> The type of entity object
 */
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
public abstract class AbstractAuditingEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract T getId();

    @Field("created_by")
    private String createdBy;

    @CreatedDate
    @Field("created_date")
    private Instant createdDate = Instant.now();

    @Field("last_modified_by")
    private String lastModifiedBy;

    @LastModifiedDate
    @Field("last_modified_date")
    private Instant lastModifiedDate = Instant.now();

    /**
     * Getter for the createdBy property
     * 
     * @return String the createdby
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for the createdBy property
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for the createdDate property
     * 
     * @return Instant the createdDate
     */
    public Instant getCreatedDate() {
        return createdDate;
    }

    /**
     * Setter for the createdDate property
     */
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Getter for the lastModifiedBy property
     * 
     * @return String the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Setter for the lastModifiedBy property
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Getter for the lastModifiedDate property
     * 
     * @return Instant the lastModifiedDate
     */
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Setter for the lastModifiedDate property
     */
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
