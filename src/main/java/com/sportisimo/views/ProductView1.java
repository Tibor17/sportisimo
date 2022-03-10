package com.sportisimo.views;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.*;

@Entity
@SecondaryTables({
        @SecondaryTable(name = "P_DETAIL",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID")),
        @SecondaryTable(name = "P_SEARCH",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "ID"))
})
@Table(name = "VIEW1")
public class ProductView1 /*extends AbstractEntity*/ {
    @Column(table = "Product", name = "size")
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
