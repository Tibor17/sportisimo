package com.sportisimo.payments;

import com.sportisimo.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")
public class PaymentOrder extends AbstractEntity {
}
