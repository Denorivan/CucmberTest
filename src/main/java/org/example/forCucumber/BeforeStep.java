package org.example.forCucumber;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;

public class BeforeStep {
    @Given("Open amazon {string}")
    public void openAmazon(String url) {
        Selenide.open(url);
    }
}
