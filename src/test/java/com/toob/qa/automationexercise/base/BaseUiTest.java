package com.toob.qa.automationexercise.base;

import com.toob.qa.automationexercise.config.LoginDetails;
import com.toob.qa.automationexercise.config.TestEnv;
import com.toob.qabase.QaBaseTest;
import com.toob.qabase.webui.dsl.Sel;
import io.qameta.allure.Step;
import lombok.Getter;
import org.junit.jupiter.api.BeforeAll;

@QaBaseTest
public abstract class BaseUiTest {

    @Getter
    protected LoginDetails loginDetails;

    @BeforeAll
    @Step("Boot context, load credentials, open home")
    void boot() {
        this.loginDetails = TestEnv.credentials();
        Sel.open();
    }
}
