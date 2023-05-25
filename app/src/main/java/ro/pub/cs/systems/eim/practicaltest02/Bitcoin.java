package ro.pub.cs.systems.eim.practicaltest02;

import androidx.annotation.NonNull;

public class Bitcoin {
    private String value;
    private String currency;

    public Bitcoin(String value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bitcoin: " + this.currency + "-" + this.value;
    }

}
