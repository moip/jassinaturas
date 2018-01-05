package br.com.moip.jassinaturas.clients.attributes;

public class BestInvoiceDate {

    private Integer dayOfMonth;
    private Integer monthOfYear;

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public BestInvoiceDate withDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public BestInvoiceDate setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public Integer getMonthOfYear() {
        return monthOfYear;
    }

    public BestInvoiceDate withMonthOfYear(Integer monthOfYear) {
        this.monthOfYear = monthOfYear;
        return this;
    }

    @Override
    public String toString() {
        return "BestInvoiceDate{" +
                "dayOfMonth=" + dayOfMonth +
                ", monthOfYear=" + monthOfYear +
                '}';
    }
}
