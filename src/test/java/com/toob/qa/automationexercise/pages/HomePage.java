package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;
import org.springframework.stereotype.Component;

import static com.toob.qabase.webui.dsl.UI.*;

@Component
public class HomePage {

    @Step("Verify Home visible")
    public HomePage verifyVisible() {
        shouldBeVisible("img[alt='Website for practice']"); return this;
    }
}
