package com.toob.qa.automationexercise.pages;

import io.qameta.allure.Step;

import static com.toob.qabase.webui.dsl.UI.*;

public class HomePage {

    @Step("Verify Home visible")
    public HomePage verifyVisible() {
        shouldBeVisible("img[alt='Website for practice']"); return this;
    }
}
