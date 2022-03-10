package com.sportisimo.products.domain;

import com.sportisimo.customer.domain.Customer;
import com.sportisimo.domain.AbstractDeleteEntity;

import javax.persistence.Entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "PAYMENT_CONTRACT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "CONTRACT_NUMBER", name = "UNQ_CONTRACT_NUMB")
})
@AttributeOverrides(@AttributeOverride(name = "id", column = @Column(name = "PAYMENT_CONTRACT_ID")))
public class PaymentContract extends AbstractDeleteEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERYNOTERECIPIENTID", nullable = false, updatable = false)
    private DeliveryNoteRecipient recipient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "INVOICERECIPIENTID", nullable = false)
    private InvoiceRecipient invoiceRecipient;

    @AttributeOverride(name = "value", column = @Column(name = "CONTRACTNUMBER", length = 20, nullable = false))
    @Embedded
    private ContractNumber contractNumber;

    /** For season parker only date, for short term - date and time. */
    @AttributeOverride(name = "value", column = @Column(name = "VALSTARTDATE", nullable = false))
    @Embedded
    private DateValidFrom dateValidationStart;

    /** For season parker only date, for short term - date and time. */
    @AttributeOverride(name = "value", column = @Column(name = "VALENDDATE"))
    @Embedded
    private DateValidTo dateValidationEnd;

    @Column(name = "CONTRACTDELIVERYTYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractDeliveryType contractDeliveryType = ContractDeliveryType.NONE;

    // TODO - Group all billing params into one object?
    @Column(name = "BILLINGINTERVALTYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private BillingIntervalType billingIntervalType = BillingIntervalType.MONTHLY;

    @Column(name = "BILLINGINTERVAL", nullable = false, precision = 11, scale = 0)
    private int billingInterval;

    @Column(name = "BILLINGSTARTOFFSET", nullable = false, precision = 11, scale = 0)
    private int billingStartOffset;

    // TODO - Group all payment params into one object?
    @Column(name = "PAYMENTCONTRACTTYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentContractType paymentContractType = PaymentContractType.PRIVATE;

    @Column(name = "CONTRACTTYPE", length = 50)
    @Enumerated(EnumType.STRING)
    private ContractType contractType = ContractType.SEASON_PARKER;

    @AttributeOverride(name = "value", column = @Column(name = "CURRENCY", length = 5))
    @Embedded
    private Currency currency;

    @Column(name = "LIMITAMOUNT", nullable = false, precision = 11, scale = 0)
    private int limit;

    @AttributeOverride(name = "value", column = @Column(name = "DESCRIPTION", length = 250))
    @Embedded
    private Description description;

    // TODO column definition should be varchar
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.VALID;

    @AttributeOverride(name = "value", column = @Column(name = "EARLIESTDELETEDATE"))
    @Embedded
    @Temporal(TemporalType.TIMESTAMP)
    private EarliestDeleteDate earliestDeleteDate;

    @Column(name = "PAYMENTCONTRACTART", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentContractArt paymentContractArt = PaymentContractArt.EMAIL;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "BLOCKINGREFERENCEID")
    private BlockingReference blockingReference;

    @Column(name = "CRMCUSTOMERNO")
    private String crmCustomerNo;

    @Column(name = "CRMCONTRACTNO")
    @AuditableProperty(label = "audit_changes_paymentContractCrmContractNo", onPersist = true)
    private String crmContractNo;

    @Column(name = "PRIVILEGEMAILTIMESTAMP")
    @AuditableProperty(label = "audit_changes_paymentContractPrivilegeMailDate", onPersist = true)
    private Calendar privilegeMailDate;

    @Column(name = "PRIVILEGEMAILCOUNT")
    @AuditableProperty(label = "audit_changes_paymentContractPrivilegeMailCount", onPersist = true)
    private int privilegeMailCount;

    @Column(name = "CONTRACTNUMBEROLD")
    @AuditableProperty(label = "audit_changes_paymentContractContractNumberOld", onPersist = true)
    private String contractNumberOld;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CONFIRMATIONMAILTIMESTAMP")
    @AuditableProperty(label = "audit_changes_paymentContractConfirmationMailDate", onPersist = true)
    private Calendar confirmationMailDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "CANCELLABLEBYCUSTOMER")
    @AuditableProperty(label = "audit_changes_cancellableByCustomer", onPersist = true)
    private Date cancellableByCustomer;

    /**
     * old system value, read only when generating new LTI report (only the first one)
     */
    @Column(name = "LONGTERMINVOICENUMBER", length = 26, insertable = false, updatable = false)
    private String longTermInvoiceNumber;

    /**
     * old system value, read only when generating new LTI report (only the first one)
     */
    @Column(name = "LONGTERMINVOICENUMBERVALIDFROM")
    @Temporal(TemporalType.DATE)
    private Calendar longTermInvoiceNumberValid;

    @Column(name = "CONTRACTMINDURATION", precision = 6, scale = 0, nullable = false)
    private int productContractMinDuration = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "OFFEREXPIRYDATE")
    @AuditableProperty(label = "audit_changes_offerExpiryDate", onPersist = true)
    private Date offerExpiryDate;

    @AttributeOverride(name = "value", column = @Column(name = "PERIODOFNOTICE", precision = 11, scale = 0, nullable = false))
    @Embedded
    private PeriodOfNotice productPeriodOfNotice = new PeriodOfNotice(0L);

    @Column(name = "SPECIAL_OFFER_YN", nullable = false)
    private boolean specialOffer;

    /**
     * (Austria specific) contract tax
     */
    @AttributeOverride(name = "value", column = @Column(name = "CONTRACTTAX", precision = 19))
    @Embedded
    @AuditableProperty(label = "audit_changes_contractTax")
    private Price contractTax;

    @OneToMany(mappedBy = "paymentContract")
    private List<TenantRelation> tenantRelations;

    @OneToMany(mappedBy = "paymentContract")
    private List<OrderedProduct> orderedProducts;

    @OneToMany(mappedBy = "paymentContract")
    private List<Consumer> consumers;

    @OneToMany(mappedBy = "contract")
    private List<ContractDocument> contractDocuments;

    @OneToMany(mappedBy = "paymentContract")
    private List<PaymentOrder> paymentOrders;

    public void setRecipient(DeliveryNoteRecipient recipient) {
        this.recipient = recipient;
    }

    public DeliveryNoteRecipient getRecipient() {
        return recipient;
    }

    public InvoiceRecipient getInvoiceRecipient() {
        return invoiceRecipient;
    }

    public void setInvoiceRecipient(InvoiceRecipient invoiceRecipient) {
        this.invoiceRecipient = invoiceRecipient;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ContractNumber getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(ContractNumber contractNumber) {
        this.contractNumber = contractNumber;
    }

    public DateValidFrom getDateValidationStart() {
        return dateValidationStart;
    }

    public Calendar getValidationStart() {
        if (dateValidationStart != null) {
            return new Calendar.Builder().setInstant(dateValidationStart.getDate()).build();
        } else {
            return null;
        }
    }

    public void setDateValidationStart(DateValidFrom dateValidationStart) {
        this.dateValidationStart = dateValidationStart;
    }

    public DateValidTo getDateValidationEnd() {
        return dateValidationEnd;
    }

    public void setDateValidationEnd(DateValidTo dateValidationEnd) {
        this.dateValidationEnd = dateValidationEnd;
    }

    public ContractDeliveryType getContractDeliveryType() {
        return contractDeliveryType;
    }

    public void setContractDeliveryType(ContractDeliveryType contractDeliveryType) {
        this.contractDeliveryType = contractDeliveryType;
    }

    public BillingIntervalType getBillingIntervalType() {
        return billingIntervalType;
    }

    public void setBillingIntervalType(BillingIntervalType billingIntervalType) {
        this.billingIntervalType = billingIntervalType;
    }

    public int getBillingInterval() {
        return billingInterval;
    }

    public void setBillingInterval(int billingInterval) {
        this.billingInterval = billingInterval;
    }

    public int getBillingStartOffset() {
        return billingStartOffset;
    }

    public void setBillingStartOffset(int billingStartOffset) {
        this.billingStartOffset = billingStartOffset;
    }

    public PaymentContractType getPaymentContractType() {
        return paymentContractType;
    }

    public void setPaymentContractType(PaymentContractType paymentContractType) {
        this.paymentContractType = paymentContractType;
    }

    public ContractType getContractType() { return contractType; }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public PaymentContractArt getPaymentContractArt() {
        return paymentContractArt;
    }

    public void setPaymentContractArt(PaymentContractArt paymentContractArt) {
        this.paymentContractArt = paymentContractArt;
    }

    @Override
    public BlockingReference getBlockingReference() {
        return blockingReference;
    }

    @Override
    public void setBlockingReference(BlockingReference blockingReference) {
        this.blockingReference = blockingReference;
    }

    public String getCrmCustomerNo() {
        return crmCustomerNo;
    }

    public void setCrmCustomerNo(String crmCustomerNo) {
        this.crmCustomerNo = crmCustomerNo;
    }

    public String getCrmContractNo() {
        return crmContractNo;
    }

    public void setCrmContractNo(String crmContractNo) {
        this.crmContractNo = crmContractNo;
    }

    public Calendar getPrivilegeMailDate() {
        return privilegeMailDate;
    }

    public void setPrivilegeMailDate(Calendar privilegeMailDate) {
        this.privilegeMailDate = privilegeMailDate;
    }

    public int getPrivilegeMailCount() {
        return privilegeMailCount;
    }

    public void setPrivilegeMailCount(int privilegeMailCount) {
        this.privilegeMailCount = privilegeMailCount;
    }

    @Override
    public PaymentContractId id() {
        return new PaymentContractId(super.getId());
    }

    public String getContractNumberOld() {
        return contractNumberOld;
    }

    public void setContractNumberOld(String contractNumberOld) {
        this.contractNumberOld = contractNumberOld;
    }

    public Calendar getConfirmationMailDate() {
        return confirmationMailDate;
    }

    public void setConfirmationMailDate(Calendar confirmationMailDate) {
        this.confirmationMailDate = confirmationMailDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Date getCancellableByCustomer() {
        return cancellableByCustomer;
    }

    public void setCancellableByCustomer(Date cancellableByCustomer) {
        this.cancellableByCustomer = cancellableByCustomer;
    }

    public PeriodOfNotice getProductPeriodOfNotice() {
        return productPeriodOfNotice;
    }

    public void setProductPeriodOfNotice(
            PeriodOfNotice productPeriodOfNotice) {
        this.productPeriodOfNotice = productPeriodOfNotice;
    }

    public int getProductContractMinDuration() {
        return productContractMinDuration;
    }

    public void setProductContractMinDuration(int productContractMinDuration) {
        this.productContractMinDuration = productContractMinDuration;
    }

    public Date getOfferExpiryDate() {
        return offerExpiryDate;
    }

    public void setOfferExpiryDate(Date offerExpiryDate) {
        this.offerExpiryDate = offerExpiryDate;
    }

    public String getLongTermInvoiceNumber() {
        return longTermInvoiceNumber;
    }

    public Calendar getLongTermInvoiceNumberValid() {
        return longTermInvoiceNumberValid;
    }

    public boolean isSpecialOffer() {
        return specialOffer;
    }

    public void setSpecialOffer(boolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    public List<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public List<TenantRelation> getTenantRelations() {
        return tenantRelations;
    }

    public void setTenantRelations(List<TenantRelation> tenantRelations) {
        this.tenantRelations = tenantRelations;
    }

    public List<ContractDocument> getContractDocuments() {
        return contractDocuments;
    }

    public void setContractDocuments(List<ContractDocument> contractDocuments) {
        this.contractDocuments = contractDocuments;
    }

    public List<PaymentOrder> getPaymentOrders() {
        return paymentOrders;
    }

    public Price getContractTax() {
        return contractTax;
    }

    public void setContractTax(Price contractTax) {
        this.contractTax = contractTax;
    }
}
