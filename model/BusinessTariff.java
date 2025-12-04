package model;

/**
 * Тариф для корпоративних клієнтів з підтримкою корпоративної знижки.
 * Розраховує річну вартість з урахуванням знижки.
 */
public class BusinessTariff extends Tariff {

    private double corporateDiscount;

    /**
     * Створює бізнес‑тариф з указаними параметрами та корпоративною знижкою.
     *
     * @param name назва тарифу
     * @param monthlyFee місячна абонентська плата без урахування знижки
     * @param minutes кількість включених хвилин
     * @param megabytes кількість включених мегабайт трафіку
     * @param sms кількість включених SMS
     * @param clients кількість клієнтів на цьому тарифі
     * @param corporateDiscount частка знижки (від 0.0 до 1.0), де 0.2 означає 20% знижки
     */
    public BusinessTariff(String name, double monthlyFee, int minutes, int megabytes,
                          int sms, int clients, double corporateDiscount) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.corporateDiscount = corporateDiscount;
    }

    /**
     * Повертає величину корпоративної знижки як частку.
     */
    public double getCorporateDiscount() {
        return corporateDiscount;
    }

    /**
     * Обчислює річну вартість тарифу з урахуванням корпоративної знижки.
     */
    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12 * (1 - corporateDiscount);
    }

    /**
     * Повертає текстове представлення тарифу з інформацією про знижку.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", discount=%.2f", corporateDiscount);
    }
}
