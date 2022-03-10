package com.sportisimo.products.domain;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory extends AbstractEntity {
    @ManyToMany(mappedBy = "productCategories")
    @NotNull
    private Set<@NotNull Product> products;
}
