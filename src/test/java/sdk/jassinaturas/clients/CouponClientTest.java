package sdk.jassinaturas.clients;

import org.junit.Test;
import br.com.moip.jassinaturas.Assinaturas;
import br.com.moip.jassinaturas.clients.attributes.Authentication;
import br.com.moip.jassinaturas.clients.attributes.Coupon;
import br.com.moip.jassinaturas.clients.attributes.CouponStatus;
import br.com.moip.jassinaturas.clients.attributes.Discount;
import br.com.moip.jassinaturas.clients.attributes.DiscountType;
import br.com.moip.jassinaturas.clients.attributes.Duration;
import br.com.moip.jassinaturas.clients.attributes.DurationType;
import br.com.moip.jassinaturas.clients.attributes.ExpirationDate;
import br.com.moip.jassinaturas.clients.attributes.Month;
import br.com.moip.jassinaturas.communicators.SandboxCommunicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CouponClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Test
    public void shouldActivateCoupon() {
        Coupon coupon = assinaturas.coupons().activate("jassinaturas_coupon_01");

        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
    }

    @Test
    public void shouldInactivateCoupon() {
        Coupon coupon = assinaturas.coupons().inactivate("jassinaturas_coupon_01");

        assertEquals(CouponStatus.INACTIVE, coupon.getStatus());
    }

    @Test
    public void createCoupon() {
        Coupon toBeCreated = new Coupon();

        toBeCreated.withCode("jassinaturas_coupon_" + System.currentTimeMillis())
                .withName("JAssinaturas")
                .withDescription("Coupon for test control")
                .withDiscount(new Discount()
                        .withValue(1000)
                        .withType(DiscountType.PERCENT))
                .withStatus(CouponStatus.ACTIVE)
                .withDuration(new Duration()
                        .withType(DurationType.REPEATING)
                        .withOccurrences(1))
                .withExpirationDate(new ExpirationDate()
                        .withDay(10)
                        .withMonth(Month.OCTOBER)
                        .withYear(2020))
                .withMaxRedemptions(1000);

        Coupon coupon = assinaturas.coupons().create(toBeCreated);

        assertEquals("JAssinaturas", coupon.getName());
        assertEquals("Coupon for test control", coupon.getDescription());
        assertEquals(1000, coupon.getDiscount().getValue());
        assertEquals(DiscountType.PERCENT, coupon.getDiscount().getType());
        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
        assertEquals(DurationType.REPEATING, coupon.getDuration().getType());
        assertEquals(1, coupon.getDuration().getOccurrences());
        assertEquals(Month.OCTOBER, coupon.getExpirationDate().getMonth());
        assertEquals(2020, coupon.getExpirationDate().getYear());
        assertEquals(1000, coupon.getMaxRedemptions().intValue());
        assertFalse(coupon.inUse());
    }

}
