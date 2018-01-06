package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Plan;

import feign.Param;
import feign.RequestLine;

public interface PlanCommunicator {

    @RequestLine("PUT /plans/{code}/activate")
    Plan activate(@Param("code") String code);

    @RequestLine("POST /plans")
    Plan create(Plan plan);

    @RequestLine("PUT /plans/{code}/inactivate")
    Plan inactivate(@Param("code") String code);

    @RequestLine("GET /plans")
    Plan list();

    @RequestLine("GET /plans/{code}")
    Plan show(@Param("code") String code);

    @RequestLine("PUT /plans/{code}")
    Plan update(@Param("code") String code, Plan plan);
}
