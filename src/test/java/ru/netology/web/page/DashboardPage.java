package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage selectCard(String numberCard) {
        if (numberCard == "5559 0000 0000 0001") {
            firstCard.$(".button").click();
        }
        if (numberCard == "5559 0000 0000 0002") {
           secondCard.$(".button").click();
        }
        return new TransferPage();
    }

    public int getBalanceCard(String numberCard) {
        String text="";
        if (numberCard == "5559 0000 0000 0001") {
            text=firstCard.getText();
        }
        if (numberCard == "5559 0000 0000 0002") {
            text=secondCard.getText();
        }
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}
