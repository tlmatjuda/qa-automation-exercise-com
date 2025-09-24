package com.toob.qa.automationexercise.base;

import com.toob.qa.automationexercise.flows.AuthFlow;
import com.toob.qa.automationexercise.flows.CartFlow;
import com.toob.qa.automationexercise.pages.*;

/** Formal test context for manual wiring (no DI, no driver setup). */
public final class TestContext {

    // Pages
    public final HeaderBar headerBar;
    public final ProductsPage productsPage;
    public final ProductDetailPage productDetailPage;
    public final CartPage cartPage;
    public final LoginPage loginPage;

    // Flows
    public final CartFlow cartFlow;
    public final AuthFlow authFlow;

    private TestContext() {
        // Parameterless page objects (Selenide handles lifecycle via @QaWebUiTest)
        this.headerBar = new HeaderBar();
        this.productsPage = new ProductsPage();
        this.productDetailPage = new ProductDetailPage();
        this.cartPage = new CartPage();
        this.loginPage = new LoginPage();

        // Flows
        this.cartFlow = new CartFlow(this);
        this.authFlow = new AuthFlow(this);
    }

    public static TestContext init() {
        return new TestContext();
    }
}