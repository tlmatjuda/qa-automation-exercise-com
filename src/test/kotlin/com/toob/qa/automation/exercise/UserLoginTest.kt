package com.toob.qa.automation.exercise


import com.codeborne.selenide.Condition.text
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qa.automation.exercise.page.LoginPage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.Test
import kotlin.test.assertNotNull

@Epic("Login Functionality")
@Feature("Positive Login")
@Story("User logs in with credentials")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@QaBaseTest
class UserLoginTest(
    pageFactory: PageFactory,
    private val autoExProps: AutoExProperties) {

    val homePage = pageFactory.get<HomePage>()
    val loginPage = pageFactory.get<LoginPage>()

    @Test
    @Order(1)
    @Description("Configured properties or yaml should load")
    fun configPropertiesShouldLoad() {
        step("Verify properties are all loaded") {
            assertNotNull(autoExProps)
            assertNotNull(autoExProps.userName)
            assertNotNull(autoExProps.userEmail)
            assertNotNull(autoExProps.userPassword)
        }
    }

    @Test
    @Order(2)
    @Description("Login User with correct email and password")
    fun loginWithValidCredentials() {
        homePage
            .open()
            .verifyVisible()
            .clickSignupLogin()

        loginPage
            .verifyVisible()
            .enterEmail(autoExProps.userEmail)
            .enterPassword(autoExProps.userPassword)
            .clickLogin()

        homePage.getLoggedInUsername()
            .shouldHave(text("Logged in as ${autoExProps.userName}"))

        homePage.logout()
    }

}