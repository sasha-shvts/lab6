package command;

import service.MobileCompanyService;

/**
 * Команда для підрахунку загальної кількості клієнтів.
 * Використовує сервіс мобільної компанії та виводить результат у консоль.
 */
public class CountClientsCommand implements Command {

    private final MobileCompanyService service;

    public CountClientsCommand(MobileCompanyService service) {
        this.service = service;
    }

    @Override
    public String description() {
        return "Підрахувати клієнтів";
    }

    @Override
    public int execute() {
        int total = service.getTotalClients();
        System.out.println("Загальна кількість клієнтів: " + total);
        return 0;
    }
}
