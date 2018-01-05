package br.com.moip.jassinaturas.clients;

import br.com.moip.jassinaturas.clients.attributes.Payment;
import br.com.moip.jassinaturas.clients.attributes.Invoice;
import br.com.moip.jassinaturas.communicators.InvoiceCommunicator;

import java.util.List;

public class InvoiceClient {

    private final InvoiceCommunicator invoiceCommunicator;

    public InvoiceClient(final InvoiceCommunicator invoiceCommunicator) {
        this.invoiceCommunicator = invoiceCommunicator;
    }

    public List<Payment> payments(final int id) {
        Invoice invoice = invoiceCommunicator.payments(id);
        return invoice.getPayments();
    }

    public Invoice show(final int id) {
        Invoice invoice = invoiceCommunicator.show(id);
        return invoice;
    }

    public Invoice retry(final int id) {
        Invoice invoice = invoiceCommunicator.retry(id);
        return invoice;
    }
}
