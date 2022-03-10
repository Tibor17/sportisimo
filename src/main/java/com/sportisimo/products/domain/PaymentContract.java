package com.sportisimo.products.domain;

import com.sportisimo.customer.domain.Customer;
import com.sportisimo.domain.AbstractDeleteEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.DATE;

@Entity
@Table(name = "PAYMENT_CONTRACT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CONTRACT_NUMBER", name = "UNQ_CONTRACT_NUMB")
})
@AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "PAYMENT_CONTRACT_ID")))
public class PaymentContract extends AbstractDeleteEntity {

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "INVOICE_RECIPIENT_ID", nullable = false)
    private InvoiceRecipient invoiceRecipient;

    @Column(nullable = false)
    @NotNull
    private String contractNumber;

    @Temporal(DATE)
    private Date dateValidationStart;

    @Column
    private Date dateValidationEnd;

    @Column(name = "CONTRACTTYPE", length = 50)
    @Enumerated(STRING)
    private ContractType contractType = ContractType.SEASON_PARKER;

    @AttributeOverride(name = "value", column = @Column(name = "CURRENCY", length = 5))
    @Embedded
    private Currency currency;
}
