package ru.netology.steps;

import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.*;

public class Hooks {

    @Before
    public void setUp() {
        open("http://localhost:9999");
    }

}
