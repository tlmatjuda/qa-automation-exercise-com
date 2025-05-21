package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.page.AccountInfoPage
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

@Epic("Automation Exercise Web")
@Feature("User Registration")
@Story("Register and delete user")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@QaBaseTest
class UserRegisterTest(
    pageFactory: PageFactory,
    private val autoExProperties: AutoExProperties) {

    val USER_NAME = "qabase"
    val USER_EMAIL = "${USER_NAME}${System.currentTimeMillis()}@toobprojects.com"

    val homePage = pageFactory.get<HomePage>()
    val accountInfoPage = pageFactory.get<AccountInfoPage>()

    @Test
    @Order(1)
    @Description("Complete registration and account deletion flow")
    fun registerAndDeleteUser() {

        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'
        // 3. Verify that home page is visible successfully
        // 4. Click on 'Signup / Login' button
        val signupPage = homePage
            .open()
            .verifyVisible()
            .clickSignupLogin()

        // 5. Verify 'New User Signup!' is visible
        // 6. Enter name and email address
        // 7. Click 'Signup' button
        signupPage
            .verifyVisible()
            .enterNameAndEmail(USER_NAME, USER_EMAIL)
            .clickSignup()

        // 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
        // 9. Fill details: Title, Name, Email, Password, Date of birth
        // 10. Select checkbox 'Sign up for our newsletter!'
        // 11. Select checkbox 'Receive special offers from our partners!'
        // 12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        // 13. Click 'Create Account button'
        val accountCreatedPage = accountInfoPage
            .verifyVisible()
            .fillBasicDetails(MockDataFactory.basicDetails())
            .selectCheckboxes()
            .fillAddressDetails(MockDataFactory.userAddress())
            .clickCreateAccount()

        // 14. Verify that 'ACCOUNT CREATED!' is visible
        // 15. Click 'Continue' button
        val dashboardPage = accountCreatedPage.verifyVisible().clickContinue()

        // 16. Verify that 'Logged in as username' is visible
        // 17. Click 'Delete Account' button
        val accountDeletedPage = dashboardPage
            .verifyLoggedInAs(USER_NAME)
            .clickDeleteAccount()

        // 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        accountDeletedPage.verifyVisible().clickContinue()
    }

    @Test
    @Order(2)
    @Description("Register User with existing email")
    fun registerExistingUser() {

        val signupPage = homePage
            .open()
            .verifyVisible()
            .clickSignupLogin()

        val accountInfoPage = signupPage
            .verifyVisible()
            .enterNameAndEmail(
                autoExProperties.userName,
                autoExProperties.userEmail
            )
            .clickSignup()
            .verifyEmailAlreadyExistsError()
    }

}