package com.sportisimo.products.domain;

import com.sportisimo.domain.AbstractDeleteEntity;
import com.sportisimo.tenants.domain.Supplier;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;


@Entity
@Table(name = "PRODUCT")
@Inheritance(strategy = JOINED)
public class Product extends AbstractDeleteEntity {
    @ManyToMany
    @JoinTable(name = "PRODUCT_AND_TYPE",
            joinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_TYPE_ID", referencedColumnName = "ID")
    )
    @NotNull
    private final Set<@NotNull ProductType> productTypes = new LinkedHashSet<>();

    @OneToMany
    @JoinTable(name = "PRODUCT_AND_CATEGORY",
            joinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_CATEGORY_ID", referencedColumnName = "ID")
    )
    @NotNull
    private final Set<ProductCategory> productCategories = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "SUPPLIER_ID", nullable = false, updatable = false)
    @NotNull
    private Supplier supplier;

    @Column(nullable = false)
    @NotNull
    private String size;

    @Column(nullable = false)
    @NotNull
    private int length;

    public @NotNull Set<@NotNull ProductType> getProductTypes() {
        return productTypes;
    }

    public @NotNull Set<@NotNull ProductCategory> getProductCategories() {
        return productCategories;
    }

    @NotNull
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(@NotNull Supplier supplier) {
        this.supplier = supplier;
    }

    @NotNull
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
