package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Authentication;
import br.com.moip.jassinaturas.feign.BasicAuthRequestInterceptor;
import br.com.moip.jassinaturas.feign.FixedHeadersInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class SandboxCommunicator implements Communicator {

    public <T> T build(final Class<T> clazz, final Authentication authentication) {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        return Feign
                .builder()
                .decoder(new GsonDecoder(gson))
                .encoder(new GsonEncoder(gson))
                .errorDecoder(new ErrorHandler())
                .requestInterceptor(
                        new BasicAuthRequestInterceptor(authentication.getToken(), authentication.getSecret()))
                .requestInterceptor(new FixedHeadersInterceptor())
                .target(clazz, "https://sandbox.moip.com.br/assinaturas/v1");
    }
}
