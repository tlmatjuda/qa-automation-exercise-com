package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;

import static com.toob.qabase.webui.dsl.UI.*;

public class HeaderBar {

    @Step("Open 'Signup / Login'")
    public HeaderBar openLogin() {
        clickCss("a[href='/login']"); return this;
    }

    @Step("Open 'Products'")
    public HeaderBar openProducts() {
        clickCss("a[href='/products']"); return this;
    }

    @Step("Open 'Cart'")
    public HeaderBar openCart() {
        clickCss("a[href='/view_cart']"); return this;
    }

    @Step("Verify logged in as {username}")
    public HeaderBar shouldShowLoggedInUser(String username) {
        shouldSeeTextIgnoreCase("Logged in as " + username); return this;
    }
}