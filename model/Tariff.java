package model;

import java.io.Serializable;

/**
 * Абстрактний базовий клас для тарифів мобільної компанії.
 * Містить спільні поля (назва, абонплата, ліміти послуг, кількість клієнтів)
 * та вимагає реалізації методу розрахунку річної вартості.
 */
public abstract class Tariff implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double monthlyFee;
    private int minutes;
    private int megabytes;
    private int sms;
    private int clients;

    /**
     * Створює тариф із базовими параметрами.
     *
     * @param name назва тарифу
     * @param monthlyFee місячна абонентська плата
     * @param minutes кількість включених хвилин
     * @param megabytes кількість включених мегабайт трафіку
     * @param sms кількість включених SMS
     * @param clients кількість клієнтів на цьому тарифі
     */
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

    /**
     * Обчислює річну вартість тарифу.
     * Реалізація залежить від конкретного типу тарифу (бізнес, сімейний, студентський тощо).
     *
     * @return річна вартість тарифу
     */
    public abstract double getAnnualCost();

    /**
     * Повертає текстове представлення тарифу з основними параметрами та річною вартістю.
     */
    @Override
    public String toString() {
        return String.format(
                "%s: fee=%.2f, minutes=%d, MB=%d, SMS=%d, clients=%d, annual=%.2f",
                name, monthlyFee, minutes, megabytes, sms, clients, getAnnualCost()
        );
    }
}
