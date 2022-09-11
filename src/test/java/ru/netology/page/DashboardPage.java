package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private ElementsCollection cards = $$x("//li[@class='list__item']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection button = $$x("//button[@data-test-id='action-deposit']");

    public TransferMoneyPage transferMoneyCard(int id) {
        button.get(id).click();
        return new TransferMoneyPage();
    }

    public int getCardBalance(String id) {
        int idN = Integer.valueOf(id);
        idN = idN - 1;
        String idS = String.valueOf(idN);
        var text = cards.get(Integer.parseInt(idS)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }
}
