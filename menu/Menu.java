package menu;

import command.*;
import model.MobileCompany;

import java.util.*;

public class Menu {

    private final Map<String, Command> commandsByKey = new LinkedHashMap<>();
    private final Map<Integer, Command> commandsByNumber = new LinkedHashMap<>();
    private MobileCompany company;
    private final Scanner scanner;
    private LoadCommand loadCommand;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.company = new MobileCompany("MyMobile");
        initCommands();
    }

    public MobileCompany getCompany() {
        return company;
    }

    public void setCompany(MobileCompany company) {
        this.company = company;
    }

    private void initCommands() {
        int number = 3;


        registerCommand(number++, "add",    new AddTariffCommand(this, scanner));
        registerCommand(number++, "remove", new RemoveTariffCommand(this, scanner));
        registerCommand(number++, "find",   new FindTariffCommand(this, scanner));
        registerCommand(number++, "count",  new CountClientsCommand(this));
        registerCommand(number++, "sort",   new SortTariffsCommand(this, scanner));
        registerCommand(number++, "save",   new SaveCommand(this, scanner));

        loadCommand = new LoadCommand(this, scanner);
        registerCommand(number++, "load",   loadCommand);

        registerCommand(number++, "edit",   new EditTariffCommand(this, scanner));
        registerCommand(number++, "view",   new ViewTariffsCommand(this));
    }

    private void registerCommand(int number, String key, Command command) {
        commandsByKey.put(key, command);
        commandsByNumber.put(number, command);
    }

    private void printCommandList() {
        System.out.println("1. help — Список команд");
        System.out.println("2. exit — Вийти з програми");
        for (Map.Entry<Integer, Command> entry : commandsByNumber.entrySet()) {
            int num = entry.getKey();
            Command cmd = entry.getValue();
            String key = commandsByKey.entrySet().stream()
                    .filter(e -> e.getValue() == cmd)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse("?");
            System.out.printf("%d. %s — %s%n", num, key, cmd.description());
        }
    }

    public void run() {
        System.out.println("Мобільна компанія \"" + company.getName() + "\".");
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
            if (input.isEmpty()) continue;

            try {
                int num = Integer.parseInt(input);
                Command cmd = commandsByNumber.get(num);
                if (cmd != null) {
                    cmd.execute(new String[0]);
                } else {
                    System.out.println("Невірний номер команди! Введіть 'help' для списку.");
                }
            } catch (NumberFormatException e) {
                Command cmd = commandsByKey.get(input.toLowerCase());
                if (cmd != null) {
                    cmd.execute(new String[0]);
                } else {
                    System.out.println("Невірна команда! Введіть 'help' для списку.");
                }
            }
        }
    }
}
