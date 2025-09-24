package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;

import static com.toob.qabase.webui.dsl.UI.*;

public class LoginPage {

    @Step("Fill email {email}")
    public LoginPage fillEmail(String email) {
        typeInto("input[data-qa='login-email']", email); return this;
    }

    @Step("Fill password ***")
    public LoginPage fillPassword(String password) {
        typeInto("input[data-qa='login-password']", password); return this;
    }

    @Step("Submit login")
    public LoginPage submit() {
        clickCss("button[data-qa='login-button']"); return this;
    }

    @Step("Expect incorrect creds error")
    public LoginPage shouldRejectBadCredentials() {
        shouldSeeText("Your email or password is incorrect!"); return this;
    }
}