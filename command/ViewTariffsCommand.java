package command;

import model.Tariff;
import service.MobileCompanyService;

/**
 * Команда для перегляду всіх тарифів мобільної компанії.
 * Виводить список тарифів або повідомляє, що список порожній.
 */
public class ViewTariffsCommand implements Command {

    private final MobileCompanyService service;

    /**
     * Створює команду перегляду тарифів.
     *
     * @param service сервіс мобільної компанії для отримання списку тарифів
     */
    public ViewTariffsCommand(MobileCompanyService service) {
        this.service = service;
    }

    @Override
    public String description() {
        return "Переглянути тарифи";
    }

    /**
     * Виводить у консоль список доступних тарифів.
     * Якщо список порожній, показує відповідне повідомлення.
     *
     * @return код завершення команди (0 у разі успішного виконання)
     */
    @Override
    public int execute() {
        if (service.getTariffs().isEmpty()) {
            System.out.println("Список тарифів порожній.");
        } else {
            System.out.println("Тарифи мобільної компанії:");
            for (Tariff t : service.getTariffs()) {
                System.out.println(t);
            }
        }
        return 0;
    }
}
