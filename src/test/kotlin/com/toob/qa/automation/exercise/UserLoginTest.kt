package com.toob.qa.automation.exercise


import com.codeborne.selenide.Condition.text
import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
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

@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Login Page")
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
    @Description("Login with \"Correct\" details")
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

        homePage.fetchLoggedInUserElement()
            .shouldHave(text("Logged in as ${autoExProps.userName}"))

        homePage.logout()
    }

    @Test
    @Order(2)
    @Description("Login with \"InCorrect\" details")
    fun loginWithInvalidCredentials() {
        homePage
            .open()
            .verifyVisible()
            .clickSignupLogin()

        loginPage
            .verifyVisible()
            .enterEmail("wrong-email@example.com")
            .enterPassword("wrongPassword")
            .clickLogin()

        step("Expect error message to display") {
            byCss("form[action='/login'] p")
                .shouldHave(text("Your email or password is incorrect!"))
        }
    }


}