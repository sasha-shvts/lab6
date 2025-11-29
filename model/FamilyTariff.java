package model;

public class FamilyTariff extends Tariff {

    private int additionalNumbers;

    public FamilyTariff(String name, double monthlyFee, int minutes, int megabytes,
                        int sms, int clients, int additionalNumbers) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.additionalNumbers = additionalNumbers;
    }

    public int getAdditionalNumbers() {
        return additionalNumbers;
    }

    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", extraNumbers=%d", additionalNumbers);
    }
}
