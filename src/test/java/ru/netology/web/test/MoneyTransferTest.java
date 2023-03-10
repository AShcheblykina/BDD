package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.ReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.*;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardNumber = getFirstCardNumber();
        var secondCardNumber = getSecondCardNumber();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardNumber);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardNumber);
        var amount = generateValidAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var replenishmentPage = dashboardPage.selectCardToReplenishment(secondCardNumber);
        dashboardPage = replenishmentPage.shouldTransfer(String.valueOf(amount), firstCardNumber);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardNumber);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardNumber);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }
}





