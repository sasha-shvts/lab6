package model;

import java.io.Serializable;

/**
 * Модель клієнта мобільної компанії.
 * Підтримує серіалізацію для збереження/завантаження стану.
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phoneNumber;

    /**
     * Створює клієнта з іменем та номером телефону.
     *
     * @param name ім'я клієнта
     * @param phoneNumber номер телефону клієнта
     */
    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }


    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
