package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    final String goodsName = "Sauce Labs Onesie";

    @Test
    public void checkGoodsAdded() {
        System.out.println("CartTest.incorrect !!!!! in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.checkTitleName(), "Products");

        productsPage.addGoodsToCart(goodsName);
        productsPage.switchToCart();

        assertEquals(cartPage.checkTitleName(), "Your Cart");
        assertEquals(cartPage.getProductsNames().size(), 1);
        assertFalse(cartPage.getProductsNames().isEmpty());
        assertTrue(cartPage.getProductsNames().contains(goodsName));
    }
}
