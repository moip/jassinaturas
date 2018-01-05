package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Alerts;
import br.com.moip.jassinaturas.clients.attributes.Errors;

import java.util.List;

public class ApiResponseError {

    private List<Alerts> alerts;
    private List<Errors> errors;
    private String message;

    public List<Alerts> getAlerts() {
        return alerts;
    }

    public List<Errors> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
