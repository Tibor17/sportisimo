package com.sportisimo.products.domain;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_TYPE")
public class ProductType extends AbstractEntity {
    @ManyToMany(mappedBy = "productTypes")
    @NotNull
    private Set<@NotNull Product> products;
}
