package sdk.jassinaturas.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FixedHeadersInterceptor implements RequestInterceptor {

    public void apply(final RequestTemplate template) {
        template.header("Content-Type", "application/json; charset=utf-8");
        template.header("Accept", "application/json");
        template.header("Accept-Encoding", "utf-8");
        template.header("Accept-Charset", "utf-8");
    }

}
