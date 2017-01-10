package sdk.jassinaturas.communicators;

import sdk.jassinaturas.clients.attributes.Alerts;
import sdk.jassinaturas.clients.attributes.Errors;

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
