package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");

        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        //assertEquals(productsPage.getTitle(), "Products", "Не верный заголовок");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "Password",
                        "Epic sadface: Username and password do not match any user in this service" }
        };
    }

    @Test (dataProvider = "incorrectLoginData", description = "проверка вторизации пользователя", invocationCount = 1)
    public void incorrectLogin(String user, String password, String errorMSg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMSg,
                "Не верный текст сообщения об ошибке");
    }
}