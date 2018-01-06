package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.BillingInfo;
import br.com.moip.jassinaturas.clients.attributes.Customer;

import feign.Param;
import feign.RequestLine;

public interface CustomerCommunicator {

    @RequestLine("POST /customers?new_vault=true")
    Customer createWithCreditCard(Customer customer);

    @RequestLine("POST /customers?new_vault=false")
    Customer createWithoutCreditCard(Customer customer);

    @RequestLine("GET /customers")
    Customer list();

    @RequestLine("GET /customers/{code}")
    Customer show(@Param("code") String code);

    @RequestLine("PUT /customers/{code}")
    Customer update(@Param("code") String code, Customer customer);

    @RequestLine("PUT /customers/{code}/billing_infos")
    Customer updateCreditCard(@Param("code") String code, BillingInfo billingInfo);
}
