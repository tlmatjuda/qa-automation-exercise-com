package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
import com.toob.qa.automation.exercise.page.ContactUsPage
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlin.test.Test


@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Contact Us Form Functionality")
@Story("We can \"Contact Us\"")
@QaBaseTest
class ContactUsTest(
    pageFactory: PageFactory,
    private val autoExProps: AutoExProperties) {

    val homePage = pageFactory.get<HomePage>()
    val contactUsPage = pageFactory.get<ContactUsPage>()

    @Test
    @Description("Contact US form verification")
    fun submitContactUsForm() {
        homePage
            .open()
            .verifyVisible()
            .clickContactUs()

        contactUsPage.verifyVisible()
            .enterName(autoExProps.userName)
            .enterEmail(autoExProps.userEmail)
            .enterSubject("Test Subject")
            .enterMessage("This is a test message from QABase automation.")
            .uploadFile("test-upload.txt")
            .clickSubmit()
            .verifySuccessMessage()
    }

}