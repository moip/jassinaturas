package sdk.jassinaturas.clients.attributes;

public enum PaymentMethodType {

    //@formatter:off
    UNKNOWN(0, "Desconhecido"),
    CREDIT_CARD(1, "Cartão de Crédito"),
    BOLETO(2, "Boleto"),
    ALL(3, "Boleto e Cartão de Crédito");
    //@formatter:on

    private int code;
    private String description;

    PaymentMethodType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    PaymentMethodType() {
    }

    public boolean isCreditCard() {
        return CREDIT_CARD.equals(this);
    }

    public boolean isBoleto(){
        return BOLETO.equals(this);
    }

    public boolean isUnknown(){
        return UNKNOWN.equals(this);
    }

    public boolean isAll(){
        return ALL.equals(this);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
