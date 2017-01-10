package sdk.jassinaturas.clients;

import com.rodrigosaito.mockwebserver.player.Play;
import com.rodrigosaito.mockwebserver.player.Player;
import org.junit.Rule;
import org.junit.Test;
import sdk.jassinaturas.Assinaturas;
import sdk.jassinaturas.clients.attributes.Address;
import sdk.jassinaturas.clients.attributes.Authentication;
import sdk.jassinaturas.clients.attributes.BillingInfo;
import sdk.jassinaturas.clients.attributes.Birthdate;
import sdk.jassinaturas.clients.attributes.Country;
import sdk.jassinaturas.clients.attributes.Coupon;
import sdk.jassinaturas.clients.attributes.CreditCard;
import sdk.jassinaturas.clients.attributes.Customer;
import sdk.jassinaturas.clients.attributes.Invoice;
import sdk.jassinaturas.clients.attributes.Month;
import sdk.jassinaturas.clients.attributes.NextInvoiceDate;
import sdk.jassinaturas.clients.attributes.Plan;
import sdk.jassinaturas.clients.attributes.State;
import sdk.jassinaturas.clients.attributes.Subscription;
import sdk.jassinaturas.clients.attributes.SubscriptionStatus;
import sdk.jassinaturas.communicators.LocalCommunicator;
import sdk.jassinaturas.communicators.SandboxCommunicator;
import sdk.jassinaturas.exceptions.ApiResponseErrorException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static sdk.jassinaturas.clients.attributes.SubscriptionStatus.CANCELED;

public class SubscriptionClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Test
    public void shouldCreateANewSubscription() {
        Subscription toBeCreated = new Subscription();
        toBeCreated
                .withCode("subscription_with_new_customer_" + System.currentTimeMillis())
                .withAmount(100)
                .withPlan(new Plan().withCode("plan001"))
                .withCustomer(
                        new Customer()
                                .withCode("customer_created_with_subscription_" + System.currentTimeMillis())
                                .withBirthdate(new Birthdate().withDay(13).withMonth(Month.OCTOBER).withYear(1989))
                                .withCpf("12312312312")
                                .withEmail("teste@teste.com")
                                .withFullname("Danillo Souza")
                                .withPhoneAreaCode("11")
                                .withPhoneNumber("912341234")
                                .withAddress(
                                        new Address().withCity("São Paulo").withComplement("Apto")
                                                .withCountry(Country.BRA).withDistrict("Centro").withNumber("1000")
                                                .withState(State.SP).withStreet("9 de Julho").withZipcode("10012345"))
                                .withBillingInfo(
                                        new BillingInfo().withCreditCard(new CreditCard().withExpirationMonth("10")
                                                .withExpirationYear("18").withHolderName("Danillo Souza")
                                                .withNumber("4111111111111111"))));

        Subscription created = assinaturas.subscriptions().create(toBeCreated);

        assertEquals("Assinatura criada com sucesso", created.getMessage());

        assertEquals(created.getAmount(), 100);
        assertEquals(created.getPlan().getName(), "Plano de Teste Atualizado");
        assertEquals(created.getPlan().getCode(), "plan001");
        assertEquals(created.getStatus(), SubscriptionStatus.ACTIVE);
        assertEquals(created.getInvoice().getAmount(), 1100);
        assertEquals(created.getInvoice().getId(), 12872);
        assertEquals(created.getInvoice().getStatus().getDescription(), "Atrasada");
        assertEquals(created.getInvoice().getStatus().getCode(), 5);
        assertEquals(1, created.getNextInvoiceDate().getDay());
        assertEquals(Month.MAY, created.getNextInvoiceDate().getMonth());
        assertEquals(2014, created.getNextInvoiceDate().getYear());
        assertEquals(created.getCode(), "subscription_with_new_customer_00001");
        assertEquals(created.getCustomer().getEmail(), "teste@teste.com");
        assertEquals(created.getCustomer().getCode(), "customer_created_with_subscription_0001");
        assertEquals(created.getCustomer().getFullname(), "Danillo Souza");
    }

    @Test
    public void shouldCreateANewSubscriptionWithoutANewCustomer() {
        Subscription toBeCreated = new Subscription();
        toBeCreated.withCode("subscription0000_"+ System.currentTimeMillis())
                .withAmount(100)
                .withCustomer(new Customer().withCode("customer000000001"))
                .withPlan(new Plan().withCode("plan001"));

        Subscription created = assinaturas.subscriptions().create(toBeCreated);

        assertEquals("Assinatura criada com sucesso", created.getMessage());

        assertEquals(created.getAmount(), 100);
        assertEquals(created.getPlan().getName(), "Plano de Teste Atualizado");
        assertEquals(created.getPlan().getCode(), "plan001");
        assertEquals(created.getStatus(), SubscriptionStatus.ACTIVE);
        assertEquals(created.getInvoice().getAmount(), 1100);
        assertEquals(created.getInvoice().getId(), 12873);
        assertEquals(created.getInvoice().getStatus().getDescription(), "Atrasada");
        assertEquals(created.getInvoice().getStatus().getCode(), 5);
        assertEquals(1, created.getNextInvoiceDate().getDay());
        assertEquals(Month.MAY, created.getNextInvoiceDate().getMonth());
        assertEquals(2014, created.getNextInvoiceDate().getYear());
        assertEquals(created.getCode(), "subscription00001");
        assertEquals(created.getCustomer().getEmail(), "teste@teste.com");
        assertEquals(created.getCustomer().getCode(), "customer000000001");
        assertEquals(created.getCustomer().getFullname(), "Danillo Souza");
    }

    @Test
    public void shouldListAllSubscriptions() {

        List<Subscription> subscriptions = assinaturas.subscriptions().list();
        assertNotNull(subscriptions);
    }

    @Test
    public void shouldReturnErrors() {
        Subscription toBeCreated = new Subscription();
        toBeCreated.withCode("subscription00001").withAmount(100)
                .withCustomer(new Customer().withCode("customer000000001")).withPlan(new Plan().withCode("plan001"));

        try {
            Subscription created = assinaturas.subscriptions().create(toBeCreated);
            fail("Should return ApiResponseError");
        } catch (ApiResponseErrorException e) {
            assertEquals("Erro na requisição", e.getApiResponseError().getMessage());
            assertEquals("Código da assinatura já utilizado. Escolha outro código", e.getApiResponseError().getErrors()
                    .get(0).getDescription());
            assertEquals("MA92", e.getApiResponseError().getErrors().get(0).getCode());
        }
    }

    @Test
    public void shouldReturnIn1voicesFromSubscription() {
        List<Invoice> invoices = assinaturas.subscriptions().invoices("subscription00001");
        Invoice invoice = invoices.get(0);

        assertEquals(10, invoice.getCreationDate().getMinute());
        assertEquals(39, invoice.getCreationDate().getSecond());
        assertEquals(Month.JANUARY, invoice.getCreationDate().getMonth());
        assertEquals(2017, invoice.getCreationDate().getYear());
        assertEquals(10, invoice.getCreationDate().getHour());
        assertEquals(10, invoice.getCreationDate().getDay());

        assertEquals(600, invoice.getAmount());
        assertEquals("subscription00001", invoice.getSubscriptionCode());
        assertEquals(1, invoice.getOccurrence());
        assertEquals(1269135, invoice.getId());

        assertEquals("Aguardando confirmação", invoice.getStatus().getDescription());
        assertEquals(2, invoice.getStatus().getCode());

    }

    @Test
    public void shouldShowASubscription() {

        Subscription subscription = assinaturas.subscriptions().show("Teste_1484071813");


        System.out.println(subscription);

        assertEquals(10, subscription.getCreationDate().getMinute());
        assertEquals(14, subscription.getCreationDate().getSecond());
        assertEquals(Month.JANUARY, subscription.getCreationDate().getMonth());
        assertEquals(2017, subscription.getCreationDate().getYear());
        assertEquals(16, subscription.getCreationDate().getHour());
        assertEquals(10, subscription.getCreationDate().getDay());

        assertEquals(990, subscription.getAmount());
        assertEquals(CANCELED, subscription.getStatus());
        assertEquals("Teste_1484071813", subscription.getCode());

        assertEquals("1484071813@exemplo.com.br", subscription.getCustomer().getEmail());
        assertEquals("1484071813", subscription.getCustomer().getCode());
        assertEquals("Jose silva", subscription.getCustomer().getFullname());

        assertEquals("Plano Especial", subscription.getPlan().getName());
        assertEquals("plan003", subscription.getPlan().getCode());

        assertEquals(Month.APRIL, subscription.getExpirationDate().getMonth());
        assertEquals(2020, subscription.getExpirationDate().getYear());
        assertEquals(24, subscription.getExpirationDate().getDay());
    }


    @Test
    public void shouldUpdateASubscription() {

        Subscription toUpdate = new Subscription();
        toUpdate.withCode("Teste_1484071813").withPlan(new Plan().withCode("plano01")).withAmount(990);

        Subscription subscription = assinaturas.subscriptions().update(toUpdate);

        // There isn't any response from Moip Assinaturas when subscription is
        // updated
        // So, I didn't do any assert

    }

    @Test
    public void shouldGetResultFromToString() {
        String subscription = assinaturas.subscriptions().show("Teste_1484071813").toString();

        assertEquals(
                "Subscription [amount=990, code=Teste_1484071813, creationDate=CreationDate [day=10, hour=16, minute=10, month=1, second=14, year=2017], customer=Customer [address=null, billingInfo=null, birthdate=null, code=1484071813, cpf=null, customers=null, email=1484071813@exemplo.com.br, fullname=Jose silva, message=null, phoneAreaCode=null, phoneNumber=null, birthdateDay=0, birthdateMonth=0, birthdateYear=0], expirationDate=ExpirationDate [day=24, month=APRIL, year=2020], invoice=null, invoices=null, message=null, nextInvoiceDate=NextInvoiceDate [day=20, month=4, year=2017], plan=Plan [alerts=null, amount=0, billingCycles=0, code=plan003, description=null, interval=null, maxQty=0, message=null, name=Plano Especial, plans=null, setupFee=0, status=null, trial=null], status=CANCELED, subscriptions=null, coupon=null]",
                subscription);
    }

    @Test
    public void addingCouponToASubscription() {
        Subscription toUpdate = new Subscription();
        toUpdate.withCode("xpto_00")
                .withPlan(new Plan().withCode("plan003"));

        Subscription subscription = assinaturas.subscriptions().update(toUpdate);

        // There isn't any response from Moip Assinaturas when subscription is
        // updated
        // So, I didn't do any assert
    }


}
