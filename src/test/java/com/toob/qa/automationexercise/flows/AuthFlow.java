package com.toob.qa.automationexercise.flows;

import com.toob.qa.automationexercise.config.LoginDetails;
import com.toob.qa.automationexercise.pages.HeaderBar;
import com.toob.qa.automationexercise.pages.LoginPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFlow {

    private final HeaderBar header;
    private final LoginPage login;

    @Step("Login OK as {creds.name}")
    public void loginOk(LoginDetails creds) {
        header.openLogin();
        login.fillEmail(creds.email())
                .fillPassword(creds.password())
                .submit();
        header.shouldShowLoggedInUser(creds.name());
    }

    @Step("Login BAD password")
    public void loginBadPassword(String email, String badPass) {
        header.openLogin();
        login.fillEmail(email).fillPassword(badPass).submit()
                .shouldRejectBadCredentials();
    }
}
