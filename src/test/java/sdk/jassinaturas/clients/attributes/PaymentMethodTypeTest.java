package sdk.jassinaturas.clients.attributes;

import org.junit.Test;
import br.com.moip.jassinaturas.clients.attributes.PaymentMethodType;

import static org.junit.Assert.assertTrue;


public class PaymentMethodTypeTest {

    @Test
    public void shouldBeCreditCard() {
        PaymentMethodType paymentMethod = PaymentMethodType.CREDIT_CARD;

        assertTrue(paymentMethod.isCreditCard());
    }

    @Test
    public void shouldBeBoleto() {
        PaymentMethodType paymentMethod = PaymentMethodType.BOLETO;

        assertTrue(paymentMethod.isBoleto());
    }

    @Test
    public void shouldBeAll() {
        PaymentMethodType paymentMethod = PaymentMethodType.ALL;

        assertTrue(paymentMethod.isAll());
    }

    @Test
    public void shouldBeUnknown() {
        PaymentMethodType paymentMethod = PaymentMethodType.UNKNOWN;

        assertTrue(paymentMethod.isUnknown());
    }
}
