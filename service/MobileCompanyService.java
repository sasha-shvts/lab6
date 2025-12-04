package service;

import model.MobileCompany;
import model.Tariff;

import java.io.IOException;
import java.util.List;

/**
 * Сервісний шар для роботи з об'єктом {@link MobileCompany}.
 * Інкапсулює операції над компанією та надає зручний API для команд меню.
 */
public class MobileCompanyService {

    private MobileCompany company;

    public MobileCompanyService(MobileCompany company) {
        this.company = company;
    }

    public void setCompany(MobileCompany company) {
        this.company = company;
    }

    public MobileCompany getCompany() {
        return company;
    }

    public void addTariff(Tariff tariff) {
        company.addTariff(tariff);
    }

    public boolean removeTariff(String name) {
        return company.removeTariff(name);
    }

    public List<Tariff> getTariffs() {
        return company.getTariffs();
    }

    /**
     * Знаходить тарифи з місячною платою в заданому діапазоні.
     *
     * @param from нижня межа місячної плати (включно)
     * @param to верхня межа місячної плати (включно)
     * @return список тарифів, що підпадають під умову
     */
    public List<Tariff> findTariffsByMonthlyFeeRange(double from, double to) {
        return company.findTariffsByMonthlyFeeRange(from, to);
    }

    public void sortTariffsByMonthlyFee() {
        company.sortTariffsByMonthlyFee();
    }

    public void sortTariffsByClients() {
        company.sortTariffsByClients();
    }

    public int getTotalClients() {
        return company.getTotalClients();
    }

    public void saveToFile(String fileName) throws IOException {
        company.saveToFile(fileName);
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        MobileCompany loaded = MobileCompany.loadFromFile(fileName);
        this.company = loaded;
    }
}
