package tests;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.getTitle(), "Products", "Не верный заголовок");
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Не верный текст сообщения об ошибке");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username is required",
                "Не верный текст сообщения об ошибке");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Password is required",
                "Не верный текст сообщения об ошибке");
    }

    @Test
    public void incorrectPassword() {
        loginPage.open();
        loginPage.login("standard_user", "Password");

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(),
                "Epic sadface: Username and password do not match any user in this service",
                "Не верный текст сообщения об ошибке");
    }
}
