package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.switchTo
import com.toob.qa.automation.exercise.AutoExSupport.inputDataQaElementByCss
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class ContactUsPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

	override fun verifyVisible(): ContactUsPage = step("Verify 'Get In Touch' is visible") {
		byCss("div.contact-form h2.title")
			.shouldHave(text("Get In Touch"))
		this
	}

	fun enterName(name: String): ContactUsPage = step("Enter name: $name") {
		inputDataQaElementByCss("name").value = name
		this
	}

	fun enterEmail(email: String): ContactUsPage = step("Enter email: $email") {
		inputDataQaElementByCss("email").value = email
		this
	}

	fun enterSubject(subject: String): ContactUsPage = step("Enter subject: $subject") {
		inputDataQaElementByCss("subject").value = subject
		this
	}

	fun enterMessage(message: String): ContactUsPage = step("Enter message") {
		byCss("textarea[data-qa='message']").value = message
		this
	}

	fun uploadFile(filePath: String): ContactUsPage = step("Upload file") {
		byCss("input[name='upload_file']").uploadFromClasspath(filePath)
		this
	}

	fun clickSubmit(): ContactUsPage = step("Click Submit button") {
		inputDataQaElementByCss("submit-button").click()
		switchTo().alert().accept()
		this
	}

	fun verifySuccessMessage(): ContactUsPage = step("Verify success message is visible") {
		byCss("div.status.alert.alert-success").shouldHave(text("Success! Your details have been submitted successfully."))
		this
	}

}