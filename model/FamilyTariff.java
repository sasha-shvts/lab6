package model;

/**
 * Тариф для сімейних клієнтів, який підтримує декілька додаткових номерів.
 * Річна вартість рахується як 12 місячних платежів без додаткових знижок.
 */
public class FamilyTariff extends Tariff {

    private int additionalNumbers;

    /**
     * Створює сімейний тариф із вказаними параметрами та кількістю додаткових номерів.
     *
     * @param name назва тарифу
     * @param monthlyFee місячна абонентська плата
     * @param minutes кількість включених хвилин
     * @param megabytes кількість включених мегабайт трафіку
     * @param sms кількість включених SMS
     * @param clients кількість клієнтів на цьому тарифі
     * @param additionalNumbers кількість додаткових номерів у межах тарифу
     */
    public FamilyTariff(String name, double monthlyFee, int minutes, int megabytes,
                        int sms, int clients, int additionalNumbers) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.additionalNumbers = additionalNumbers;
    }

    /**
     * Повертає кількість додаткових номерів, підключених до тарифу.
     */
    public int getAdditionalNumbers() {
        return additionalNumbers;
    }

    /**
     * Обчислює річну вартість сімейного тарифу (12 місячних платежів).
     */
    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12;
    }

    /**
     * Повертає текстове представлення тарифу з інформацією про додаткові номери.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", extraNumbers=%d", additionalNumbers);
    }
}
