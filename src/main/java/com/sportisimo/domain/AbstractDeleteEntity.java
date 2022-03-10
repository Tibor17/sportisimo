package com.sportisimo.domain;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
public class AbstractDeleteEntity extends AbstractEntity {
    @Column(name = "DELETE_DATE")
    @Temporal(TIMESTAMP)
    private Date deletionDate;

    @Column(name = "LAST_CHANGE_DATE")
    @Temporal(TIMESTAMP)
    private Date lastChangeDate;

    @Transient
    public boolean delete() {
        if (!isDeleted()) {
            setDeletionDate(new Date());
            return true;
        }
        return false;
    }

    /**
     * Restores deleted entity.
     *
     * @return true if is restored otherwise false
     */
    public boolean restore() {
        if (isDeleted()) {
            setDeletionDate(null);
            return true;
        }
        return false;
    }

    @Transient
    public boolean isDeleted() {
        return deletionDate != null;
    }

    public Date getDeletionDate() {
        return deletionDate;
    }

    protected void setDeletionDate(Date deletionDate) {
        this.deletionDate = deletionDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    @PrePersist
    void onCreate() {
        lastChangeDate = new Date();
    }

    @PreUpdate
    void onChange() {
        if (!isDeleted()) {
            lastChangeDate = new Date();
        }
    }
}