package ru.netology.web.test;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {
    DashboardPage dashboardPage;
    String numberFirstCard = "5559 0000 0000 0001";
    String numberSecondCard = "5559 0000 0000 0002";

    @BeforeEach
    void setUpAll() {
        val loginPage = open("http://localhost:9999",LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferZeroAmountOnFirstCard() {
        val dataCardSecond = DataHelper.getSecondCard();
        val transferPage = dashboardPage.selectCard(numberFirstCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,0);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(10000,balanceAfterTransferFirstCard);
        assertEquals(10000,balanceAfterTransferSecondCard);
    }

    @Test
    void shouldTransferZeroAmountOnSecondCard() {
        val dataCardSecond = DataHelper.getFirstCard();
        val transferPage = dashboardPage.selectCard(numberSecondCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,0);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(10000,balanceAfterTransferFirstCard);
        assertEquals(10000,balanceAfterTransferSecondCard);
    }

    @Test
    void shouldTransferMiddleAmountOnFirstCard() {
        val dataCardSecond = DataHelper.getSecondCard();
        val transferPage = dashboardPage.selectCard(numberFirstCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,5000);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(15000,balanceAfterTransferFirstCard);
        assertEquals(5000,balanceAfterTransferSecondCard);
    }

    @Test
    void shouldTransferMiddleAmountOnSecondCard() {
        val dataCardSecond = DataHelper.getFirstCard();
        val transferPage = dashboardPage.selectCard(numberSecondCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,5000);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(10000,balanceAfterTransferFirstCard);
        assertEquals(10000,balanceAfterTransferSecondCard);
    }

    @Test
    void shouldTransferMaximumAmountOnFirstCard() {
        val dataCardSecond = DataHelper.getSecondCard();
        val transferPage = dashboardPage.selectCard(numberFirstCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,10000);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(20000,balanceAfterTransferFirstCard);
        assertEquals(0,balanceAfterTransferSecondCard);
    }

    @Test
    void shouldTransferMaximumAmountOnSecondCard() {
        val dataCardSecond = DataHelper.getFirstCard();
        val transferPage = dashboardPage.selectCard(numberSecondCard);
        transferPage.transferMoneyFromCardToAnotherCard(dataCardSecond,20000);
        $(withText("Ваши карты")).shouldBe(visible);
        int balanceAfterTransferFirstCard=dashboardPage.getBalanceCard(numberFirstCard);
        int balanceAfterTransferSecondCard=dashboardPage.getBalanceCard(numberSecondCard);
        assertEquals(0,balanceAfterTransferFirstCard);
        assertEquals(20000,balanceAfterTransferSecondCard);
    }



}








