package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Coupon;

import feign.Param;
import feign.RequestLine;

public interface CouponCommunicator {

    @RequestLine("GET /coupons/{code}")
    Coupon show(@Param("code") String code);

    @RequestLine("POST /coupons")
    Coupon create(Coupon coupon);

    @RequestLine("PUT /coupons/{code}/active")
    Coupon activate(@Param("code") String code);

    @RequestLine("PUT /coupons/{code}/inactive")
    Coupon inactivate(@Param("code") String code);
}
