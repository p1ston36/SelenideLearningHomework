package com.ucp.qa.selenidelearning.tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
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
        $x("//h1").shouldBe(visible).shouldHave(text("Built for developers"));
    }

    @Test
    public void chekTable() {
        open("https://github.com/");
        $x("//input[@name='q']").setValue("selenide").pressEnter();

        $$x("//ul[@class='repo-list']/li")
                .shouldHaveSize(10)
                .first()
                .shouldHave(text("Concise UI Tests with Java!"));
        assertAll(
                () -> assertTrue("QWERTY".equals("ASDFG"), "упало 1"),
                () -> assertTrue("QWERTY".equals("ASDFG"), "упало 2"),
                () -> assertEquals("QWERTY", "ASDFG", "упало 3")
        );

    }

    @Test
    public void getElementFromElement(){
        open("https://github.com/");
        String table = "//form[@action= '/join']";
        SelenideElement selenideTable = $x("//form[@action= '/join']");
        System.out.println($x("//form[@action= '/join']//label[@for= 'user[login]']").getText());
        System.out.println($x(table + "//label[@for= 'user[login]']").getText());
        System.out.println(selenideTable.$x(".//label[@for= 'user[login]']").getText());
    }

}
