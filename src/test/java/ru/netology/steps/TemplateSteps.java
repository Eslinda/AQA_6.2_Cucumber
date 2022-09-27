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

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferMoneyToFirstCard(int amount, String cardNumber, int id) {
        id = id - 1;
        dashboardPage.transferMoneyCard(id).transferMoney(DataHelper.getSecondCardNumber(), amount);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей.")
    public void extractBalance(int id, int balance) {
        id = id - 1;
        int balanceFirstCard = dashboardPage.getCardBalance(id);
        Assertions.assertEquals(balance, balanceFirstCard);

    }
}
