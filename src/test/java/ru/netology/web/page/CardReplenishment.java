package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardReplenishment {
    private SelenideElement replenishment = $("[data-test-id=action-deposit]");
    private SelenideElement sum =  $x("//span[contains(text(),'Сумма')]");
    private SelenideElement card = $("[data-test-id=from]");
    private SelenideElement transfer = $("[data-test-id=action-transfer]");

    public DashboardPage validUser (DataHelper.AuthInfo info) {
        replenishment.click();
        sum.setValue("500");
        card.setValue(info.getNumber());
        transfer.click();
        return new DashboardPage();
    }

}


