package com.sportisimo.products.domain;

import com.sportisimo.domain.AbstractEntity;
import com.sportisimo.tenants.domain.Tenant;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "WAREHOUSE")
public class WareHouse extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "TENANT_ID", nullable = false, updatable = false)
    @NotNull
    private Tenant tenant;

    @Column(name = "ADDRESS", nullable = false)
    @NotNull
    private String address;

    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    @NotNull
    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(@NotNull Tenant tenant) {
        this.tenant = tenant;
    }
}
