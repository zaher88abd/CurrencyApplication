package ca.zaher.m.lab6;

/**
 * Created by zaher on 2018-03-06.
 */

public class CurrencyItem {
    private String base;
    private double value;

    public CurrencyItem(String base, double value) {
        this.base = base;
        this.value = value;
    }

    public String getBase() {
        return base;
    }

    public double getValue() {
        return value;
    }
}
