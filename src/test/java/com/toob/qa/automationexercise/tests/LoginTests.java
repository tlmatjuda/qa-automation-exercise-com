package com.toob.qa.automationexercise.tests;

import com.toob.qa.automationexercise.base.BaseUiTest;
import com.toob.qa.automationexercise.flows.AuthFlow;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Authentication")
@RequiredArgsConstructor
public class LoginTests extends BaseUiTest {

    private final AuthFlow auth;

    @Test
    @DisplayName("TC2: Login with correct email/password")
    void shouldLoginSuccessfully() {
        auth.loginOk(loginDetails);
    }

    @Test @DisplayName("TC3: Login with incorrect password")
    void shouldFailLogin() {
        auth.loginBadPassword(loginDetails.email(), "wrong-pass-123");
    }
}