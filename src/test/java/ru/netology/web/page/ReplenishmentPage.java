package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ReplenishmentPage {
    private SelenideElement transfer = $("[data-test-id=action-transfer]");
    private SelenideElement amount = $x("//span[contains(text(),'Сумма')]");
    private SelenideElement card = $("[data-test-id=from]");
    private SelenideElement replenishment = $x("//h1");
    private SelenideElement message = $("[data-test-id=error-message]");

    public ReplenishmentPage() {
        replenishment.shouldBe(visible);
    }

    public DashboardPage shouldTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        makeReplenishment(amountToTransfer, cardInfo);
        return new DashboardPage();
    }

    public void makeReplenishment(String amountToTransfer, DataHelper.CardInfo cardInfo) {
        amount.setValue(amountToTransfer);
        card.setValue(cardInfo.getCardNumber());
        transfer.click();
    }

}


