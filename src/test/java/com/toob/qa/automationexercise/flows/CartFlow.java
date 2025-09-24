package com.toob.qa.automationexercise.flows;

import com.toob.qa.automationexercise.base.TestContext;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CartFlow {

    private final TestContext ctx;

    @Step("Add product id={id} qty={qty} and verify cart")
    public void addAndVerify(int id, int qty) {
        ctx.headerBar.openProducts();
        ctx.productsPage.openProductById(id);
        ctx.productDetailPage.verifyVisible().setQuantity(qty).addToCart();
        ctx.cartPage.verifyVisible()
                .shouldContainProduct(id)
                .shouldHaveQuantity(id, qty);
    }
}