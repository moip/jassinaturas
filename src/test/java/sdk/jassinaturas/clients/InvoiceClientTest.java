package sdk.jassinaturas.clients;

import org.junit.Test;
import sdk.jassinaturas.Assinaturas;
import sdk.jassinaturas.clients.attributes.Authentication;
import sdk.jassinaturas.clients.attributes.Invoice;
import sdk.jassinaturas.clients.attributes.Month;
import sdk.jassinaturas.clients.attributes.Payment;
import sdk.jassinaturas.communicators.SandboxCommunicator;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InvoiceClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Test
    public void shouldGetPaymentsFromAInvoice() {
        List<Payment> payments = assinaturas.invoices().payments(1269186);
        Payment payment = payments.get(0);

        assertEquals(9866590, payment.getId());
        assertEquals(867364, payment.getMoipId());

        assertEquals("Cancelado", payment.getStatus().getDescription());
        assertEquals(5, payment.getStatus().getCode());

        assertEquals(16, payment.getCreationDate().getMinute());
        assertEquals(26, payment.getCreationDate().getSecond());
        assertEquals(Month.JANUARY, payment.getCreationDate().getMonth());
        assertEquals(2017, payment.getCreationDate().getYear());
        assertEquals(16, payment.getCreationDate().getHour());
        assertEquals(10, payment.getCreationDate().getDay());

        assertEquals("Cartão de Crédito", payment.getPaymentMethod().getDescription());
        assertEquals(1, payment.getPaymentMethod().getCode());
        assertEquals("Nome Completo", payment.getPaymentMethod().getCreditCard().getHolderName());
        assertEquals("411111", payment.getPaymentMethod().getCreditCard().getFirstSixDigits());
        assertEquals("1111", payment.getPaymentMethod().getCreditCard().getLastFourDigits());
        assertEquals("04", payment.getPaymentMethod().getCreditCard().getExpirationMonth());
        assertEquals("18", payment.getPaymentMethod().getCreditCard().getExpirationYear());
        assertEquals("VISA", payment.getPaymentMethod().getCreditCard().getBrand());
    }

    @Test
    public void shouldShowAnInvoice() {
        Invoice invoice = assinaturas.invoices().show(1269186);

        assertEquals(10, invoice.getCreationDate().getMinute());
        assertEquals(14, invoice.getCreationDate().getSecond());
        assertEquals(Month.JANUARY, invoice.getCreationDate().getMonth());
        assertEquals(2017, invoice.getCreationDate().getYear());
        assertEquals(16, invoice.getCreationDate().getHour());
        assertEquals(10, invoice.getCreationDate().getDay());

        assertEquals(3694, invoice.getAmount());
        assertEquals("Teste_1484071813", invoice.getSubscriptionCode());
        assertEquals(1, invoice.getOccurrence());

        assertEquals("Não pago", invoice.getStatus().getDescription());
        assertEquals(4, invoice.getStatus().getCode());

        assertEquals("Plano Especial", invoice.getPlan().getName());
        assertEquals("plan003", invoice.getPlan().getCode());

        assertEquals("Valor da assinatura", invoice.getItems().get(0).getType());
        assertEquals(990, invoice.getItems().get(0).getAmount());
        assertEquals("Taxa de contratação", invoice.getItems().get(1).getType());
        assertEquals(500, invoice.getItems().get(1).getAmount());

        assertEquals("Jose silva", invoice.getCustomer().getFullname());
        assertEquals("1484071813", invoice.getCustomer().getCode());
    }

    @Test
    public void shouldGetResultFromToString() {
        String invoice = assinaturas.invoices().show(1269186).toString();

        assertEquals(
                "Invoice [amount=3694, creationDate=CreationDate [day=10, hour=16, minute=10, month=1, second=14, year=2017], customer=Customer [address=null, billingInfo=null, birthdate=null, code=1484071813, cpf=null, customers=null, email=null, fullname=Jose silva, message=null, phoneAreaCode=null, phoneNumber=null, birthdateDay=0, birthdateMonth=0, birthdateYear=0], id=1269186, items=[Item [amount=990, type=Valor da assinatura], Item [amount=500, type=Taxa de contratação]], occurrence=1, payments=null, plan=Plan [alerts=null, amount=0, billingCycles=0, code=plan003, description=null, interval=null, maxQty=0, message=null, name=Plano Especial, plans=null, setupFee=0, status=null, trial=null], status=InvoiceStatus [code=4, description=Não pago], subscriptionCode=Teste_1484071813]",
                invoice);

    }
}
