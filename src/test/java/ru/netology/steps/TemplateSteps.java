package ru.netology.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

public class TemplateSteps {

    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        loginPage = new LoginPage();
        verificationPage = loginPage.validLogin(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы")
    public void transferMoneyToFirstCard(String amount, String cardNumber, String id) {
        amount = amount.replaceAll("\\s+", "");
        int amountInt = Integer.parseInt(amount);
        id = id.replaceAll("\\s+", "");
        int idInt = Integer.valueOf(id);
        idInt = idInt - 1;
        dashboardPage.transferMoneyCard(idInt).transferMoney(DataHelper.getSecondCardNumber(), amountInt);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей.")
    public void extractBalance(String id, String balance) {
        balance = balance.replaceAll("\\s+", "");
        Integer balanceInt = Integer.parseInt(balance);
        int balanceFirstCard = dashboardPage.getCardBalance(id);
        Assertions.assertEquals(balanceInt, balanceFirstCard);

    }
}
