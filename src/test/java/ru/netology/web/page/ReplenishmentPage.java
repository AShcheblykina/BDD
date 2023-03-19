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
    private final SelenideElement transfer = $("[data-test-id='action-transfer']");
    private final SelenideElement amount = $("[data-test-id='amount'] input");
    private final SelenideElement card = $("[data-test-id='from'] input");
    private final SelenideElement replenishment = $x("//h1");
    private final SelenideElement errorMessage = $("[data-test-id='error-message']");

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
  public void findErrorMessage(String expectedText) {
        errorMessage.shouldBe(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
  }
}


