package com.ucp.qa.selenidelearning.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class SecondLectionTest {

    @AfterEach
    public void dispouse() {
        close();
    }

    @Test
    public void test() {
        open("https://github.com/");
        $x("//h1").shouldBe(visible).shouldHave(Condition.text("Built for developers"));
    }

    @Test
    public void chekTable() {
        open("https://github.com/");
        $x("//input[@name='q']").setValue("selenide").pressEnter();

        $$x("//ul[@class='repo-list']/li")
                .shouldHaveSize(10)
                .first()
                .shouldHave(Condition.text("Concise UI Tests with Java!"));
        assertAll(
                () -> assertTrue("QWERTY".equals("ASDFG"), "упало 1"),
                () -> assertTrue("QWERTY".equals("ASDFG"), "упало 2"),
                () -> assertEquals("QWERTY", "ASDFG", "упало 3")
        );

    }

}
