package sdk.jassinaturas.clients.attributes;

public class Trial {
    private int days;
    private boolean enabled;

    private TrialStart start;
    private TrialEnd end;

    public TrialStart getStart() {
        return start;
    }

    public void setStart(TrialStart start) {
        this.start = start;
    }

    public TrialEnd getEnd() {
        return end;
    }

    public void setEnd(TrialEnd end) {
        this.end = end;
    }

    public Trial withDays(final int days) {
        this.days = days;
        return this;
    }

    public Trial enabled() {
        this.enabled = true;
        return this;
    }

    public Trial disabled() {
        this.enabled = false;
        return this;
    }

    public int getDays() {
        return days;
    }

    public void setDays(final int days) {
        this.days = days;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "Trial [days=" + days + ", enabled=" + enabled + "]";
    }

    public Trial withStart(int day, int month, int year) {
        this.start = new TrialStart(day, month, year);
        return this;
    }

    public Trial withEnd(int day, int month, int year) {
        this.end = new TrialEnd(day, month, year);
        return this;
    }
}
