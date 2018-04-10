package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.CreationDate;
import br.com.moip.jassinaturas.clients.attributes.Invoice;
import feign.Param;
import feign.RequestLine;

public interface InvoiceCommunicator {

    @RequestLine("GET /invoices/{id}/payments")
    Invoice payments(@Param("id") int id);

    @RequestLine("GET /invoices/{id}")
    Invoice show(@Param("id") int id);

    @RequestLine("POST /invoices/{id}/retry")
    Invoice retry(@Param("id") int id);
    
    @RequestLine("POST /invoices/{id}/boletos")
    Invoice generateNewOne(@Param("id") String id, CreationDate dueDate);
}
