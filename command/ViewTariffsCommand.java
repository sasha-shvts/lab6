package command;

import menu.Menu;
import model.MobileCompany;
import model.Tariff;

public class ViewTariffsCommand implements Command {

    private final Menu menu;

    public ViewTariffsCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String description() {
        return "Переглянути тарифи";
    }

    @Override
    public int execute(String[] params) {
        MobileCompany company = menu.getCompany();

        if (company.getTariffs().isEmpty()) {
            System.out.println("Список тарифів порожній.");
        } else {
            System.out.println("Тарифи мобільної компанії:");
            for (Tariff t : company.getTariffs()) {
                System.out.println(t);
            }
        }
        return 0;
    }
}
