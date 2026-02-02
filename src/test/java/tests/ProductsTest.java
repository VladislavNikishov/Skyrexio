package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)" ));

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        assertEquals(productsPage.checkTitleName(), "Products");
        assertTrue(productsPage.isTitleIsDisplayed());
        for (int i=0; i < goodsList.size(); i++) {
            productsPage.addGoodsToCart(goodsList.get(i));
        }

        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
