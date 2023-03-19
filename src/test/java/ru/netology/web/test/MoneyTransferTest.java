package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.ReplenishmentPage;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
        var amount = generateAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expectedBalanceSecondCard = secondCardBalance + amount;
        var replenishmentPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = replenishmentPage.shouldTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
     @Test
    void shouldTransferAboveMaximumBalance() {
         open("http://localhost:9999");
         var loginPage = new LoginPage();
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         var dashboardPage = verificationPage.validVerify(verificationCode);
         var firstCardInfo = getFirstCardInfo();
         var secondCardInfo = getSecondCardInfo();
         var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
         var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
         var amount = generateAmountAboveMax(firstCardBalance);
         var replenishmentPage = dashboardPage.selectCardToTransfer(firstCardInfo);
         replenishmentPage.makeReplenishment(String.valueOf(amount),secondCardInfo);
         replenishmentPage.findErrorMessage("Не хватает денег для перевода");
         var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
         var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
         assertEquals(firstCardBalance, actualBalanceFirstCard);
         assertEquals(secondCardBalance, actualBalanceSecondCard);
     }

    }






