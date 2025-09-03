package com.toob.qa.automationexercise.base;

import com.toob.qa.automationexercise.config.LoginDetails;
import com.toob.qa.automationexercise.config.TestEnv;
import com.toob.qabase.QaBaseTest;
import com.toob.qabase.webui.dsl.Sel;
import io.qameta.allure.Step;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QaBaseTest
public abstract class BaseUiTest {

    @Getter
    protected LoginDetails loginDetails;

    @BeforeAll
    @Step("Boot context, load credentials, open home")
    void boot() {
        this.loginDetails = TestEnv.credentials();
        assertNotNull(loginDetails, "Your login details are not set! Please, check the README.md for details.");
        assertNotNull(loginDetails.name(), "Your login details are not set!");
        assertNotNull(loginDetails.email(), "Your login details are not set!");
        assertNotNull(loginDetails.password(), "Your login details are not set!");
        Sel.open();
    }
}
