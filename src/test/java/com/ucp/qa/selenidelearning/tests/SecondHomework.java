package com.ucp.qa.selenidelearning.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecondHomework {
    @BeforeEach
    private void movToURL() {
        open("https://github.com/");
    }

    @AfterEach
    private void closeWindow() {
        close();
    }

    @Test
    public void case1() {
        $$x("//button").find(Condition.text("Sign up for GitHub")).click();
        ElementsCollection error = $$x("//dl[dd[@class = 'error']]");
        assertAll(
                () -> assertEquals($x("//form[@id='signup-form']/div").shouldBe(Condition.visible).getText(), "There were problems creating your account.", "error message isn't visibl"),
                () -> assertEquals(3, error.filter(Condition.text("can't be blank")).filter(Condition.visible).size(), "Error tooltips aren't visible for all required fields")
        );

    }
}
