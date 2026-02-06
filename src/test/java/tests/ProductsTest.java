package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Sauce Labs Onesie", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)" ));

    @Test
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.incorrect !!!!! in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.checkTitleName(), "Products");
        assertTrue(productsPage.isTitleIsDisplayed());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

//        for (int i=0; i < goodsList.size(); i++) {
//            productsPage.addGoodsToCart(goodsList.get(i));
//        }

        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
