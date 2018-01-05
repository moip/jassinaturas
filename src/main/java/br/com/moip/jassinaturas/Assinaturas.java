package br.com.moip.jassinaturas;

import br.com.moip.jassinaturas.clients.CouponClient;
import br.com.moip.jassinaturas.clients.CustomerClient;
import br.com.moip.jassinaturas.clients.InvoiceClient;
import br.com.moip.jassinaturas.clients.PlanClient;
import br.com.moip.jassinaturas.clients.SubscriptionClient;
import br.com.moip.jassinaturas.clients.attributes.Authentication;
import br.com.moip.jassinaturas.communicators.Communicator;
import br.com.moip.jassinaturas.communicators.CouponCommunicator;
import br.com.moip.jassinaturas.communicators.CustomerCommunicator;
import br.com.moip.jassinaturas.communicators.InvoiceCommunicator;
import br.com.moip.jassinaturas.communicators.PlanCommunicator;
import br.com.moip.jassinaturas.communicators.SubscriptionCommunicator;

public class Assinaturas {

    private final Authentication authentication;
    private final Communicator communicator;

    public Assinaturas(final Authentication authentication, final Communicator communicator) {
        this.authentication = authentication;
        this.communicator = communicator;
    }

    public CustomerClient customers() {
        CustomerCommunicator customerCommunicator = communicator.build(CustomerCommunicator.class, authentication);
        return new CustomerClient(customerCommunicator);
    }

    public InvoiceClient invoices() {
        InvoiceCommunicator invoiceCommunicator = communicator.build(InvoiceCommunicator.class, authentication);
        return new InvoiceClient(invoiceCommunicator);
    }

    public PlanClient plans() {
        PlanCommunicator planCommunicator = communicator.build(PlanCommunicator.class, authentication);
        return new PlanClient(planCommunicator);
    }

    public SubscriptionClient subscriptions() {
        SubscriptionCommunicator subscriptionCommunicator = communicator.build(SubscriptionCommunicator.class,
                authentication);
        return new SubscriptionClient(subscriptionCommunicator);
    }

    public CouponClient coupons() {
        CouponCommunicator couponCommunicator = communicator.build(CouponCommunicator.class, authentication);
        return new CouponClient(couponCommunicator);
    }
}
