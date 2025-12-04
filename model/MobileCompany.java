package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель мобільної компанії, що містить тарифи та клієнтів.
 * Підтримує пошук, сортування, підрахунок клієнтів і серіалізацію у файл.
 */
public class MobileCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private List<Tariff> tariffs = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();


    public MobileCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTariff(Tariff tariff) {
        tariffs.add(tariff);
    }

    public boolean removeTariff(String name) {
        return tariffs.removeIf(t -> t.getName().equalsIgnoreCase(name));
    }

    public List<Tariff> getTariffs() {
        return new ArrayList<>(tariffs);
    }

    /**
     * Знаходить усі тарифи з місячною платою в заданому діапазоні.
     *
     * @param from мінімальна місячна плата (включно)
     * @param to максимальна місячна плата (включно)
     * @return список тарифів, що задовольняють умову
     */
    public List<Tariff> findTariffsByMonthlyFeeRange(double from, double to) {
        return tariffs.stream()
                .filter(t -> t.getMonthlyFee() >= from && t.getMonthlyFee() <= to)
                .collect(Collectors.toList());
    }

    public void sortTariffsByMonthlyFee() {
        tariffs.sort(Comparator.comparingDouble(Tariff::getMonthlyFee));
    }

    public void sortTariffsByClients() {
        tariffs.sort(Comparator.comparingInt(Tariff::getClients));
    }

    /**
     * Оцінює загальну кількість клієнтів компанії.
     * Береться максимум між кількістю об'єктів Customer та сумарною кількістю clients у тарифах.
     *
     * @return оцінка загальної кількості клієнтів
     */
    public int getTotalClients() {
        int byCustomers = customers.size();
        int byTariffs = tariffs.stream().mapToInt(Tariff::getClients).sum();
        return Math.max(byCustomers, byTariffs);
    }

    /**
     * Зберігає поточний стан компанії у файл за допомогою об'єктної серіалізації.
     *
     * @param fileName ім'я файлу або шлях, куди буде виконано запис
     * @throws IOException якщо сталася помилка введення/виведення під час запису
     */
    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        }
    }

    /**
     * Завантажує об'єкт MobileCompany з указаного файлу.
     *
     * @param fileName ім'я файлу або шлях, з якого потрібно прочитати дані
     * @return відновлений екземпляр MobileCompany
     * @throws IOException якщо сталася помилка введення/виведення під час читання
     * @throws ClassNotFoundException якщо клас MobileCompany або пов'язані класи не знайдено під час десеріалізації
     */
    public static MobileCompany loadFromFile(String fileName)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            return (MobileCompany) ois.readObject();
        }
    }
}
