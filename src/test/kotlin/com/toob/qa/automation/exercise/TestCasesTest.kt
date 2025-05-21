package com.toob.qa.automation.exercise


import com.codeborne.selenide.Condition.text
import com.toob.qa.automation.exercise.AutoExSupport.RESOURCE_PATH
import com.toob.qa.automation.exercise.page.ContactUsPage
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qa.automation.exercise.page.LoginPage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.Test



@Epic("Automation Exercise Web")
@Feature("Test Cases Page")
@Story("Test cases page exists")
@QaBaseTest
class TestCasesTest(
    pageFactory: PageFactory,
    private val autoExProps: AutoExProperties) {

    val homePage = pageFactory.get<HomePage>()

    @Test
    @Description("Verify Test Cases Page")
    fun verifyTestCasesPage() {
        homePage
            .open()
            .verifyVisible()
            .clickTestCases()
            .verifyVisible()
    }

}