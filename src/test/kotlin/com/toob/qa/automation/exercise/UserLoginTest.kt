package com.toob.qa.automation.exercise


import com.codeborne.selenide.Condition.text
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

    @Test
    @Order(2)
    @Description("Login User with incorrect email and password")
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

        step("Verify error message is displayed") {
            byCss("form[action='/login'] p")
                .shouldHave(text("Your email or password is incorrect!"))
        }
    }


}