package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.toob.qabase.webui.dsl.UI.shouldBeVisible;
import static com.toob.qabase.webui.dsl.UI.shouldSeeText;
import static com.toob.qabase.webui.dsl.UI.shouldSee;

@Component
public class CartPage {

    @Step("Verify cart visible")
    public CartPage verifyVisible() {
        shouldSeeText("Shopping Cart"); return this;
    }

    @Step("Cart should contain product id={productId}")
    public CartPage shouldContainProduct(int productId) {
        shouldBeVisible("tr#product-" + productId); return this;
    }

    @Step("Product id={productId} quantity == {qty}")
    public CartPage shouldHaveQuantity(int productId, int qty) {
        shouldSee("tr#product-" + productId + " .cart_quantity", String.valueOf(qty));
        return this;
    }
}
