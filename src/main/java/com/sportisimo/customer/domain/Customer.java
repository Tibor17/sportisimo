package com.sportisimo.customer.domain;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity {
}