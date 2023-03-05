package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class CardReplenishment {
    private SelenideElement replenishment = $("[data-test-id=action-deposit]");
    private SelenideElement balance = $("[data-test-id=amount]");
    private SelenideElement card = $("[data-test-id=from]");
    private SelenideElement transfer = $("[data-test-id=action-transfer]");

    public DashboardPage validUser (DataHelper.AuthInfo info) {
        replenishment.click();
        balance.setValue(info.getBalance());
        card.setValue(info.getNumber());
        transfer.click();
        return new DashboardPage();
    }

}


