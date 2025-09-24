package com.toob.qa.automationexercise.flows;

import com.toob.qa.automationexercise.base.TestContext;
import com.toob.qa.automationexercise.config.LoginDetails;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthFlow {

    private final TestContext ctx;

    @Step("Login OK as {creds.name}")
    public void loginOk(LoginDetails creds) {
        ctx.headerBar.openLogin();
        ctx.loginPage.fillEmail(creds.email())
                .fillPassword(creds.password())
                .submit();
        ctx.headerBar.shouldShowLoggedInUser(creds.name());
    }

    @Step("Login BAD password")
    public void loginBadPassword(String email, String badPass) {
        ctx.headerBar.openLogin();
        ctx.loginPage.fillEmail(email).fillPassword(badPass).submit()
                .shouldRejectBadCredentials();
    }
}
