package ru.netology.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataHelper {

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static TransferMoneyCard getFirstCardNumber() {

        return new TransferMoneyCard("5559 0000 0000 0001", "1");
    }

    public static TransferMoneyCard getSecondCardNumber() {

        return new TransferMoneyCard("5559 0000 0000 0002", "2");
    }

    @Value
    public static class TransferMoneyCard {
        private String cardNumber;
        private String id;
    }

}