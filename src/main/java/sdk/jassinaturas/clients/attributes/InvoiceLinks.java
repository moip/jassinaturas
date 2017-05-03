package sdk.jassinaturas.clients.attributes;

/**
 * Created by caueferreira on 12/04/17.
 */
public class InvoiceLinks {

    private LinkBoleto boleto;

    public LinkBoleto getBoleto() {
        return boleto;
    }

    public class LinkBoleto {
        private String redirectHref;

        public String getRedirectHref() {
            return redirectHref;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("LinkBoleto{");
            sb.append("redirectHref='").append(redirectHref).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvoiceLinks{");
        sb.append("boleto=").append(boleto);
        sb.append('}');
        return sb.toString();
    }
}
