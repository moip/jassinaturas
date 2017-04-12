package sdk.jassinaturas.clients.attributes;

import java.util.List;

public class Subscription {
    private List<Alerts> alerts;
    private int amount;
    private String code;
    private CreationDate creationDate;
    private Customer customer;
    private ExpirationDate expirationDate;
    private Invoice invoice;
    private List<Invoice> invoices;
    private String message;
    private NextInvoiceDate nextInvoiceDate;
    private Plan plan;
    private SubscriptionStatus status;
    private List<Subscription> subscriptions;
    private Coupon coupon;
    private Boolean proRata;
    private BestInvoiceDate bestInvoiceDate;
    private Trial trial;
    private BillingInfo billingInfo;

    public List<Alerts> getAlerts() {
        return alerts;
    }

    public int getAmount() {
        return amount;
    }

    public String getCode() {
        return code;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public String getMessage() {
        return message;
    }

    public NextInvoiceDate getNextInvoiceDate() {
        return nextInvoiceDate;
    }

    public Plan getPlan() {
        return plan;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public List<Subscription> getSubscriptions() {

        return this.subscriptions;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public Subscription withAmount(final int amount) {
        this.amount = amount;
        return this;
    }

    public Subscription withCode(final String code) {
        this.code = code;
        return this;
    }

    public Subscription withCustomer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public Subscription withNextInvoiceDate(final NextInvoiceDate nextInvoiceDate) {
        this.nextInvoiceDate = nextInvoiceDate;
        return this;
    }

    public Subscription withPlan(final Plan plan) {
        this.plan = plan;
        return this;
    }

    public Subscription withCoupon(final Coupon coupon) {
        this.coupon = coupon;
        return this;
    }

    public Boolean isProRata() {
        return proRata;
    }

    public Subscription withProRata(Boolean proRata) {
        this.proRata = proRata;
        return this;
    }

    public Subscription withBestInvoiceDate(BestInvoiceDate bestInvoiceDate) {
        this.bestInvoiceDate = bestInvoiceDate;
        return this;
    }

    public BestInvoiceDate getBestInvoiceDate() {
        return bestInvoiceDate;
    }

    public Trial getTrial() {
        return trial;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("alerts=").append(alerts);
        sb.append(", amount=").append(amount);
        sb.append(", code='").append(code).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", customer=").append(customer);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", invoice=").append(invoice);
        sb.append(", invoices=").append(invoices);
        sb.append(", message='").append(message).append('\'');
        sb.append(", nextInvoiceDate=").append(nextInvoiceDate);
        sb.append(", plan=").append(plan);
        sb.append(", status=").append(status);
        sb.append(", subscriptions=").append(subscriptions);
        sb.append(", coupon=").append(coupon);
        sb.append(", proRata=").append(proRata);
        sb.append(", bestInvoiceDate=").append(bestInvoiceDate);
        sb.append(", trial=").append(trial);
        sb.append(", billingInfo=").append(billingInfo);
        sb.append('}');
        return sb.toString();
    }
}
