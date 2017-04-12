package sdk.jassinaturas.clients.attributes;

import java.util.List;

public class Invoice {

    public int amount;
    private CreationDate creationDate;
    private Customer customer;
    public int id;
    private List<Item> items;
    private List<Payment> payments;
    private Plan plan;
    public InvoiceStatus status;
    private String subscriptionCode;
    private int occurrence;
    private CreationDate dueDate;
    private InvoiceLinks _links;

    public int getAmount() {
        return amount;
    }

    public CreationDate getCreationDate() {
        return creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public Plan getPlan() {
        return plan;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public String getSubscriptionCode() {
        return subscriptionCode;
    }

    public CreationDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("amount=").append(amount);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", customer=").append(customer);
        sb.append(", id=").append(id);
        sb.append(", items=").append(items);
        sb.append(", payments=").append(payments);
        sb.append(", plan=").append(plan);
        sb.append(", status=").append(status);
        sb.append(", subscriptionCode='").append(subscriptionCode).append('\'');
        sb.append(", occurrence=").append(occurrence);
        sb.append(", dueDate=").append(dueDate);
        sb.append(", _links=").append(_links);
        sb.append('}');
        return sb.toString();
    }


    public InvoiceLinks get_links() {
        return _links;
    }
}