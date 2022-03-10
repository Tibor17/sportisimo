package com.sportisimo.tenants.domain;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TENANT", uniqueConstraints = {
        @UniqueConstraint(name = "UQE_TENANT_CODE", columnNames = "CODE")
})
public class Tenant extends AbstractEntity {

    @NotNull
    @Column(name = "CODE", nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    public String getCode() {
        return code;
    }

    public void setCode(@NotNull String code) {
        this.code = code;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}