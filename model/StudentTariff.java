package model;

/**
 * Тариф для студентів з додатковими безкоштовними нічними хвилинами.
 * Річна вартість рахується як 12 місячних платежів без знижок.
 */
public class StudentTariff extends Tariff {

    private int freeNightMinutes;

    /**
     * Створює студентський тариф із вказаними параметрами та нічними хвилинами.
     *
     * @param name назва тарифу
     * @param monthlyFee місячна абонентська плата
     * @param minutes кількість включених хвилин
     * @param megabytes кількість включених мегабайт трафіку
     * @param sms кількість включених SMS
     * @param clients кількість клієнтів на цьому тарифі
     * @param freeNightMinutes кількість безкоштовних нічних хвилин
     */
    public StudentTariff(String name, double monthlyFee, int minutes, int megabytes,
                         int sms, int clients, int freeNightMinutes) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.freeNightMinutes = freeNightMinutes;
    }

    /**
     * Повертає кількість безкоштовних нічних хвилин у тарифі.
     */
    public int getFreeNightMinutes() {
        return freeNightMinutes;
    }

    /**
     * Обчислює річну вартість студентського тарифу (12 місячних платежів).
     */
    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12;
    }

    /**
     * Повертає текстове представлення тарифу з інформацією про нічні хвилини.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", nightMinutes=%d", freeNightMinutes);
    }
}
