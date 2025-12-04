package menu;

import command.*;
import model.MobileCompany;
import service.MobileCompanyService;

import java.util.*;

/**
 * Консольне меню для керування мобільною компанією.
 * Дозволяє викликати різні команди за номером або текстовим ключем.
 */
public class Menu {

    private final Map<String, Command> commandsByKey = new LinkedHashMap<>();

    private final Map<Integer, Command> commandsByNumber = new LinkedHashMap<>();

    private final Scanner scanner;

    private final MobileCompanyService service;

    /**
     * Створює меню з попередньо налаштованою мобільною компанією "MyMobile"
     * та реєструє доступні команди.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.service = new MobileCompanyService(new MobileCompany("MyMobile"));
        initCommands();
    }

    /**
     * Ініціалізує та реєструє всі доступні команди в меню.
     * Прив'язує кожну команду як до числового пункту, так і до текстового ключа.
     */
    private void initCommands() {
        int number = 3;

        registerCommand(number++, "add",    new AddTariffCommand(service, scanner));
        registerCommand(number++, "remove", new RemoveTariffCommand(service, scanner));
        registerCommand(number++, "find",   new FindTariffCommand(service, scanner));
        registerCommand(number++, "count",  new CountClientsCommand(service));
        registerCommand(number++, "sort",   new SortTariffsCommand(service, scanner));
        registerCommand(number++, "save",   new SaveCommand(service, scanner));
        registerCommand(number++, "load",   new LoadCommand(service, scanner));
        registerCommand(number++, "edit",   new EditTariffCommand(service, scanner));
        registerCommand(number++, "view",   new ViewTariffsCommand(service));
    }

    /**
     * Реєструє команду в обох картах: за числовим пунктом меню та текстовим ключем.
     *
     * @param number номер команди в меню
     * @param key текстовий ключ (наприклад, "add", "remove")
     * @param command об'єкт команди, що буде виконуватися
     */
    private void registerCommand(int number, String key, Command command) {
        commandsByKey.put(key, command);
        commandsByNumber.put(number, command);
    }

    /**
     * Друкує список доступних команд із номерами, ключами та описами.
     * Спочатку виводить зарезервовані пункти help та exit.
     */
    private void printCommandList() {
        System.out.println("1. help — Список команд");
        System.out.println("2. exit — Вийти з програми");
        for (Map.Entry<Integer, Command> entry : commandsByNumber.entrySet()) {
            int num = entry.getKey();
            Command cmd = entry.getValue();
            String key = "?";
            for (Map.Entry<String, Command> e : commandsByKey.entrySet()) {
                if (e.getValue().equals(cmd)) {
                    key = e.getKey();
                    break;
                }
            }
            System.out.printf("%d. %s — %s%n", num, key, cmd.description());
        }
    }

    /**
     * Запускає головний цикл роботи меню.
     * Обробляє введення користувача, викликаючи команди за номером або ключовим словом,
     * а також підтримує команди help та exit.
     */
    public void run() {
        System.out.println("Мобільна компанія \"MyMobile\".");
        System.out.println("1 — help (Cписок команд), 2 — exit (Вихід з програми)");
        System.out.println("Введіть номер або ключ команди:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("help") || input.equals("1")) {
                printCommandList();
                continue;
            }
            if (input.equalsIgnoreCase("exit") || input.equals("2")) {
                System.out.println("Завершення роботи.");
                break;
            }
            if (input.isEmpty()) {
                continue;
            }

            try {
                int num = Integer.parseInt(input);
                Command cmd = commandsByNumber.get(num);
                if (cmd != null) {
                    cmd.execute();
                } else {
                    System.out.println("Невірний номер команди! Введіть 'help' для списку.");
                }
            } catch (NumberFormatException e) {
                Command cmd = commandsByKey.get(input.toLowerCase());
                if (cmd != null) {
                    cmd.execute();
                } else {
                    System.out.println("Невірна команда! Введіть 'help' для списку.");
                }
            }
        }
    }
}
