package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.toob.qabase.webui.dsl.UI.*;

@Component
public class ProductDetailPage {

    @Step("Verify product detail visible")
    public ProductDetailPage verifyVisible() {
        shouldBeVisible(".product-information"); return this;
    }

    @Step("Set quantity={qty}")
    public ProductDetailPage setQuantity(int qty) {
        clear("#quantity");
        typeInto("#quantity", String.valueOf(qty));
        return this;
    }

    @Step("Add to cart and go to cart")
    public ProductDetailPage addToCart() {
        // Click Add to cart
        clickCss("button.cart");
        // Wait for the modal that confirms the add-to-cart
        shouldBeVisible("#cartModal .modal-content");
        shouldSeeText("Added!");
        // Click the View Cart link inside the modal (avoids overlay intercepts)
        clickCss("#cartModal a[href='/view_cart']");
        return this;
    }
}