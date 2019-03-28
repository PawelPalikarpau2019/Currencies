package my.projects.currencies.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Currencies {
    private boolean success;
    private int timestamp;
    private String base;
    private String date;
    private Map<String, Double> rates;

    public List<Currency> getCurrencyList(){
        final List<Currency> currencyList = new ArrayList<>();
        for(Map.Entry<String, Double> entry : getRates().entrySet()) {
            currencyList.add(new Currency(entry.getKey(), entry.getValue()));
        }
        return currencyList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
