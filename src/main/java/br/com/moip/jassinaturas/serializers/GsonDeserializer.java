package br.com.moip.jassinaturas.serializers;

import com.google.gson.Gson;

public class GsonDeserializer {
    public <T> T deserialize(final String json, final Class<T> clazz) {
        Gson gson = new Gson();

        return gson.fromJson(json, clazz);
    }
}
