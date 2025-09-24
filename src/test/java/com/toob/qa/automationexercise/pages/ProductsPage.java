package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;

import static com.toob.qabase.webui.dsl.UI.*;

public class ProductsPage {

    @Step("Verify 'All Products' header")
    public ProductsPage shouldShowAllProducts() {
        shouldSeeText("All Products"); return this;
    }

    @Step("Open product id={id}")
    public ProductsPage openProductById(int id) {
        String link = "a[href='/product_details/" + id + "']";
        scrollIntoView(link);       // centers the link away from the ad strip
        shouldBeVisible(link);      // optional, but nice for diagnostics
        clickCssJs(link);           // JS-backed click bypasses the overlay/iframe
        return this;
    }

    @Step("Verify at least {min} products visible")
    public ProductsPage shouldHaveAtLeast(int min) {
        shouldHaveCountAtLeast(".features_items .product-image-wrapper", min); return this;
    }
}