package sdk.jassinaturas.clients;

import com.rodrigosaito.mockwebserver.player.Play;
import com.rodrigosaito.mockwebserver.player.Player;
import org.junit.Rule;
import org.junit.Test;
import sdk.jassinaturas.Assinaturas;
import sdk.jassinaturas.clients.attributes.Authentication;
import sdk.jassinaturas.clients.attributes.Coupon;
import sdk.jassinaturas.clients.attributes.CouponStatus;
import sdk.jassinaturas.clients.attributes.Discount;
import sdk.jassinaturas.clients.attributes.DiscountType;
import sdk.jassinaturas.clients.attributes.Duration;
import sdk.jassinaturas.clients.attributes.DurationType;
import sdk.jassinaturas.clients.attributes.ExpirationDate;
import sdk.jassinaturas.clients.attributes.Month;
import sdk.jassinaturas.communicators.SandboxCommunicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CouponClientTest {

    private final Assinaturas assinaturas = new Assinaturas(new Authentication("JOSOAPZJ4JI3IQTRUUTIGWQEPRPMDW58",
            "Q1MSGUKMXXQTKO4W7OHHINJNFYSOCT4FJLJKYXKH"), new SandboxCommunicator());

    @Rule
    public Player player = new Player();

    public CouponClientTest() {
        player.setPort(9000);
    }

    @Play("ACTIVATE_COUPON")
    @Test
    public void shouldActivateCoupon() {
        Coupon coupon = assinaturas.coupons().activate("jassinaturas_coupon_01");

        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
    }

    @Play("INACTIVATE_COUPON")
    @Test
    public void shouldInactivateCoupon() {
        Coupon coupon = assinaturas.coupons().activate("jassinaturas_coupon_01");

        assertEquals(CouponStatus.INACTIVE, coupon.getStatus());
    }

    @Play("CREATE_COUPON")
    @Test
    public void createCoupon() {
        Coupon toBeCreated = new Coupon();

        toBeCreated.withCode("jassinaturas_coupon_01")
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

        assertEquals("jassinaturas_coupon_01", coupon.getCode());
        assertEquals("JAssinaturas", coupon.getName());
        assertEquals("Coupon for test control", coupon.getDescription());
        assertEquals(1000, coupon.getDiscount().getValue());
        assertEquals(DiscountType.PERCENT, coupon.getDiscount().getType());
        assertEquals(CouponStatus.ACTIVE, coupon.getStatus());
        assertEquals(DurationType.REPEATING, coupon.getDuration().getType());
        assertEquals(1, coupon.getDuration().getOccurrences());
        assertEquals(10, coupon.getExpirationDate().getDay());
        assertEquals(Month.OCTOBER, coupon.getExpirationDate().getMonth());
        assertEquals(2020, coupon.getExpirationDate().getYear());
        assertEquals(1000, coupon.getMaxRedemptions().intValue());
        assertFalse(coupon.inUse());
        assertEquals(21, coupon.getCreationDate().getDay());
        assertEquals(Month.JUNE, coupon.getCreationDate().getMonth());
        assertEquals(2015, coupon.getCreationDate().getYear());
        assertEquals(21, coupon.getCreationDate().getHour());
        assertEquals(51, coupon.getCreationDate().getMinute());
        assertEquals(43, coupon.getCreationDate().getSecond());
    }
}
