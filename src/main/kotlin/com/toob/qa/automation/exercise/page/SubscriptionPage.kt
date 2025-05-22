package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class SubscriptionPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

    private val subscriptionSection = "#footer .single-widget"
    private val emailInput = "#susbscribe_email"
    private val submitButton = "#subscribe"
    private val successMessage = "#success-subscribe"

    override fun verifyVisible(): SubscriptionPage = step("Verify Subscription Section is visible") {
        byCss(subscriptionSection)
            .scrollIntoView(true)
            .shouldBe(visible)
            .shouldHave(text("Subscription"))
        this
    }

    fun subscribeWithEmail(email: String): SubscriptionPage = step("Subscribe with email: $email") {
        byCss(emailInput).shouldBe(visible).value = email
        byCss(submitButton).shouldBe(visible).click()
        this
    }

    fun verifySuccessMessage(): SubscriptionPage = step("Verify success message for subscription") {
        byCss(successMessage)
            .shouldBe(visible)
            .shouldHave(text("You have been successfully subscribed!"))
        this
    }

}