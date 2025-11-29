package model;

public class StudentTariff extends Tariff {

    private int freeNightMinutes;

    public StudentTariff(String name, double monthlyFee, int minutes, int megabytes,
                         int sms, int clients, int freeNightMinutes) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.freeNightMinutes = freeNightMinutes;
    }

    public int getFreeNightMinutes() {
        return freeNightMinutes;
    }

    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", nightMinutes=%d", freeNightMinutes);
    }
}
