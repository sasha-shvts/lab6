package model;

import java.io.Serializable;

public abstract class Tariff implements Serializable {

    private String name;
    private double monthlyFee;
    private int minutes;
    private int megabytes;
    private int sms;
    private int clients;

    public Tariff(String name, double monthlyFee, int minutes, int megabytes, int sms, int clients) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.minutes = minutes;
        this.megabytes = megabytes;
        this.sms = sms;
        this.clients = clients;
    }

    public String getName() { return name; }
    public double getMonthlyFee() { return monthlyFee; }
    public int getMinutes() { return minutes; }
    public int getMegabytes() { return megabytes; }
    public int getSms() { return sms; }
    public int getClients() { return clients; }

    public void setMonthlyFee(double monthlyFee) { this.monthlyFee = monthlyFee; }
    public void setMinutes(int minutes) { this.minutes = minutes; }
    public void setMegabytes(int megabytes) { this.megabytes = megabytes; }
    public void setSms(int sms) { this.sms = sms; }
    public void setClients(int clients) { this.clients = clients; }

    public abstract double getAnnualCost();

    @Override
    public String toString() {
        return String.format(
                "%s: fee=%.2f, minutes=%d, MB=%d, SMS=%d, clients=%d, annual=%.2f",
                name, monthlyFee, minutes, megabytes, sms, clients, getAnnualCost()
        );
    }
}
