package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlin.test.Test


@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Test Cases Page")
@Story("Test cases page exists")
@QaBaseTest
class TestCasesTest(
    pageFactory: PageFactory,
    private val autoExProps: AutoExProperties) {

    val homePage = pageFactory.get<HomePage>()

    @Test
    @Description("Test Cases Page is visible")
    fun verifyTestCasesPage() {
        homePage
            .open()
            .verifyVisible()
            .clickTestCases()
            .verifyVisible()
    }

}