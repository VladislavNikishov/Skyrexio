package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        System.out.println("LoginTest.correct !!!!! in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        assertEquals(productsPage.checkTitleName(), PRODUCTS.getDisplayName(), "Не верный заголовок");
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

    @Test (dataProvider = "incorrectLoginData", description = "проверка авторизации пользователя", invocationCount = 1)
    public void incorrectLogin(String user, String password, String errorMSg) {
        System.out.println("LoginTest.incorrect !!!!! in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMSg,
                "Не верный текст сообщения об ошибке");
    }
}
