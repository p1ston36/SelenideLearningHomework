package com.ucp.qa.selenidelearning.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.*;

public class SecondHomework {
    @BeforeEach
    private void movToURL() {
        open("https://github.com/");
    }

//    @AfterEach
//    private void closeWindow() {
//        close();
//    }

    @Test
    public void case1() {
        $$x("//button").find(text("Sign up for GitHub")).click();
        ElementsCollection error = $$x("//dd[@class = 'error']");
        assertAll(
                () -> assertTrue(url().equals("https://github.com/join"), "User wasn't navigated to https://github.com/join"),
                () -> assertEquals($x("//form[@id='signup-form']/div").shouldBe(visible).getText(), "There were problems creating your account.", "error message isn't visible"),
                () -> assertEquals(3, error.filter(text("can't be blank")).filter(visible).size(), "Error tooltips aren't visible for all required fields")
        );
    }

    @Test
    public void case2() {
        $x("//input[@name='user[login]']").setValue("selenide");
        SelenideElement s = $x("//dl[//input[@name='user[login]']]");
        assertAll(
                () -> assertTrue(s.shouldHave(attribute("class", "form-group mt-0 errored")).isDisplayed(), "Username isn't red"),
                () -> assertTrue(s.$x(".//input").shouldHave(attribute("class", "form-control form-control-lg input-block is-autocheck-errored")).isDisplayed(), "Error img isn't exist"),
                () -> assertTrue(s.$x("./dd[@class='error']").shouldBe(visible).has(text("Username selenide is not available.")), "error masage isn't visible")
        );

    }

    @Test
    public void case3() {
        $x("//input[@name='q']").setValue("selenide").pressEnter();
        ElementsCollection lenguageList = $$x("//ul[@class='repo-list']//span[@itemprop='programmingLanguage']");
        ElementsCollection infoList = $$x("//ul[@class='repo-list']//div[@class = 'd-flex flex-wrap']");
        assertAll(
                () -> assertTrue($x("//div[ul[@class='repo-list']]//h3").has(text("847")), "result of search isn't equals 847"),
                () -> assertEquals(7, lenguageList.filterBy(text("Java")).size(), "Java repositories aren't equals 7"),
                () -> assertEquals(1, lenguageList.filterBy(text("C#")).size(), "C# repositories aren't equals 1"),
                () -> assertEquals(2, infoList.first(2).filterBy(text("MIT license")).size(), "First two repositories aren't licensed under MIT license")
        );
    }

    @Test
    public void case4() {
        Selenide.actions().moveToElement($x("//summary")).perform();
        $$x("//ul[@class='list-style-none f5 pb-3']/li").find(text("Code review")).click();
        $$x("//button").find(text("See what’s possible")).click();
        assertAll(
                () -> assertTrue(url().equals("https://github.com/features/code-review/"), "Bad URL"),
                () -> assertTrue($x("//h1").isDisplayed(), "Write better code isn't displeed"),
                () -> assertTrue($x("//iframe").shouldHave(attribute("src", "https://www.youtube.com/embed/w3jLJU7DT5E?autoplay=1&rel=0")).isDisplayed(), "Embedded youtube video block isn't visible")
        );


    }
}
