package br.com.moip.jassinaturas.communicators;

import br.com.moip.jassinaturas.clients.attributes.Authentication;

public interface Communicator {

    public <T> T build(final Class<T> clazz, final Authentication authentication);

}
