package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Invoice;

import feign.Param;
import feign.RequestLine;

public interface InvoiceCommunicator {

    @RequestLine("GET /invoices/{id}/payments")
    Invoice payments(@Param("code") int id);

    @RequestLine("GET /invoices/{id}")
    Invoice show(@Param("code") int id);

    @RequestLine("POST /invoices/{id}/retry")
    Invoice retry(@Param("code") int id);
}
