package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Test

@Epic("Automation Exercise Web")
@Feature("Home Page UI")
@Story("Verify essential elements load on Home Page")
@QaBaseTest
class HomePageVisibilityTest(pageFactory: PageFactory) {

    val homePage = pageFactory.get<HomePage>()

    @Test
    @Description("Open the Home page and verify green buttons and 'Signup / Login' link are visible")
    fun shouldLoadHomePageElements() {
        homePage
            .open()
            .verifyVisible()
    }

}