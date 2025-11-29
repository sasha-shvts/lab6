package model;

public class BusinessTariff extends Tariff {

    private double corporateDiscount;

    public BusinessTariff(String name, double monthlyFee, int minutes, int megabytes,
                          int sms, int clients, double corporateDiscount) {
        super(name, monthlyFee, minutes, megabytes, sms, clients);
        this.corporateDiscount = corporateDiscount;
    }

    public double getCorporateDiscount() {
        return corporateDiscount;
    }

    @Override
    public double getAnnualCost() {
        return getMonthlyFee() * 12 * (1 - corporateDiscount);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", discount=%.2f", corporateDiscount);
    }
}
