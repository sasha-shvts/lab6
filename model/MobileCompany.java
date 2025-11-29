package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MobileCompany implements Serializable {

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


    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    public int getTotalClients() {
        int byCustomers = customers.size();
        int byTariffs = tariffs.stream().mapToInt(Tariff::getClients).sum();
        return Math.max(byCustomers, byTariffs);
    }

    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        }
    }

    public static MobileCompany loadFromFile(String fileName)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(fileName))) {
            return (MobileCompany) ois.readObject();
        }
    }
}
