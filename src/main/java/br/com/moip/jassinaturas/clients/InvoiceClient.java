package br.com.moip.jassinaturas.clients;

import java.util.List;

import br.com.moip.jassinaturas.clients.attributes.CreationDate;
import br.com.moip.jassinaturas.clients.attributes.Invoice;
import br.com.moip.jassinaturas.clients.attributes.Payment;
import br.com.moip.jassinaturas.communicators.InvoiceCommunicator;

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
    
    public Invoice generateNewInvoice (final String id, final CreationDate dueDate) {
    	Invoice invoice = invoiceCommunicator.generateNewOne(id, dueDate);
    	return invoice;
    }
}
