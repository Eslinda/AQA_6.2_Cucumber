package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.Before;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferMoneyPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;


public class TemplateSteps {

    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;

    private static TransferMoneyPage transferMoneyPage;

    @Before
    public void setUp() {
        open("http://localhost:9999");
    }

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
        String s = amount.replaceAll("\\s+","");
        int amountInt = Integer.parseInt(s);
       // int id = DataHelper.TransferMoneyCard(cardNumber);
        dashboardPage.transferMoneyCard(0).transferMoney(DataHelper.getSecondCardNumber(), amountInt);
    }


    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей.")
    public void extractBalance(String id, String balance) {
        String b = balance.replaceAll("\\s+","");
        Integer balanceInt = Integer.parseInt(b);
        int balanceFirstCard = dashboardPage.getCardBalance(id);
        Assertions.assertEquals(balanceInt, balanceFirstCard);

    }
}
