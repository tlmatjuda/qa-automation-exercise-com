package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qa.automation.exercise.page.SubscriptionPage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test

@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Home Page UI")
@Story("Verify essential elements load on Home Page")
@QaBaseTest
class HomeTest(pageFactory: PageFactory) {

    private val TEST_MAIL = "tester${System.currentTimeMillis()}@test.com"

    val homePage = pageFactory.get<HomePage>()
    val subscriptionPage = pageFactory.get<SubscriptionPage>()

    @Test
    @Description("Verify Home Page visibility and sections")
    fun shouldLoadHomePageElements() {
        step("Home page loads successfully") {
            homePage
                .open()
                .verifyVisible()
        }

        step("Home page, Subscription Footer loads successfully") {
            subscriptionPage
                .verifyVisible()
                .subscribeWithEmail(TEST_MAIL)
                .verifySuccessMessage()
        }
    }
}