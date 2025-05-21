package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.ext.SelenideExtensions.eleCollection
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class LoginPage( pageFactory: PageFactory) : AbstractPage(pageFactory) {

    override fun verifyVisible(): LoginPage = step("Verify Login Page is visible") {
        eleCollection("div.login-form h2")
            .findBy(text("Login to your account"))
            .shouldBe(visible)
        this
    }

    fun enterEmail(email: String): LoginPage = step("Enter email: $email") {
        byCss("[data-qa='login-email']").setValue(email)
        this
    }

    fun enterPassword(password: String): LoginPage = step("Enter password: [HIDDEN]") {
        byCss("[data-qa='login-password']").setValue(password)
        this
    }

    fun clickLogin(): LoginPage = step("Click Login button") {
        byCss("[data-qa='login-button']").click()
        this
    }


}