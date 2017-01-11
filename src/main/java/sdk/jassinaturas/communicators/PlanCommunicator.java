package sdk.jassinaturas.communicators;

import feign.RequestLine;
import sdk.jassinaturas.clients.attributes.Plan;

import javax.inject.Named;

public interface PlanCommunicator {

    @RequestLine("PUT /plans/{code}/activate")
    Plan activate(@Named("code") String code);

    @RequestLine("POST /plans")
    Plan create(Plan plan);

    @RequestLine("PUT /plans/{code}/inactivate")
    Plan inactivate(@Named("code") String code);

    @RequestLine("GET /plans")
    Plan list();

    @RequestLine("GET /plans/{code}")
    Plan show(@Named("code") String code);

    @RequestLine("PUT /plans/{code}")
    Plan update(@Named("code") String code, Plan plan);
}
