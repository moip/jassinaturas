package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Invoice;
import feign.RequestLine;

import javax.inject.Named;

public interface InvoiceCommunicator {

    @RequestLine("GET /invoices/{id}/payments")
    Invoice payments(@Named("id") int id);

    @RequestLine("GET /invoices/{id}")
    Invoice show(@Named("id") int id);

    @RequestLine("POST /invoices/{id}/retry")
    Invoice retry(@Named("id") int id);
}
