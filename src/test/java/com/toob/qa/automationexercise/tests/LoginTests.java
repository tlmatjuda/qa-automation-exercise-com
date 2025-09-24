package com.toob.qa.automationexercise.tests;

import com.toob.qa.automationexercise.base.BaseUiTest;
import com.toob.qa.automationexercise.base.TestContext;
import com.toob.qa.automationexercise.flows.AuthFlow;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Authentication")
class LoginTests extends BaseUiTest {

    @Test
    @DisplayName("TC2: Login with correct email/password")
    void shouldLoginSuccessfully() {
        AuthFlow authFlow = ctx.authFlow;
        assertNotNull(authFlow);
        authFlow.loginOk(loginDetails);
    }

    @Test @DisplayName("TC3: Login with incorrect password")
    void shouldFailLogin() {
        AuthFlow authFlow = ctx.authFlow;
        assertNotNull(authFlow);
        authFlow.loginBadPassword(loginDetails.email(), "wrong-pass-123");
    }
}