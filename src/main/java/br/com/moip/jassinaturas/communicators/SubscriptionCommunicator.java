package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Subscription;

import feign.Param;
import feign.RequestLine;

public interface SubscriptionCommunicator {

    @RequestLine("PUT /subscriptions/{code}/activate")
    Subscription activate(@Param("code") String code);

    @RequestLine("PUT /subscriptions/{code}/cancel")
    Subscription cancel(@Param("code") String code);

    @RequestLine("POST /subscriptions?new_customer=true")
    Subscription createWithCustomer(Subscription subscription);

    @RequestLine("POST /subscriptions?new_customer=false")
    Subscription createWithoutCustomer(Subscription subscription);

    @RequestLine("GET /subscriptions/{code}/invoices")
    Subscription invoices(@Param("code") String subscriptionCode);

    @RequestLine("GET /subscriptions")
    Subscription list();

    @RequestLine("GET /subscriptions/{code}")
    Subscription show(@Param("code") String code);

    @RequestLine("PUT /subscriptions/{code}/suspend")
    Subscription suspend(@Param("code") String code);

    @RequestLine("PUT /subscriptions/{code}")
    Subscription update(@Param("code") String code, Subscription subscription);

}
