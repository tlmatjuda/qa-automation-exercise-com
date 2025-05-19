package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Step
import org.springframework.stereotype.Component
import kotlin.text.get

@Component
class SignupPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

	fun enterNameAndEmail(name: String, email: String): SignupPage {
		step("Enter name and email") {
			step("Fill name: $name") {
				byCss("[data-qa='signup-name']").value = name
			}
			step("Fill email: $email") {
				byCss("[data-qa='signup-email']").value = email
			}
		}
		return this
	}

	fun clickSignup(): AccountInfoPage {
		step("Click 'Signup' button") {
			byCss("[data-qa='signup-button']").shouldBe(visible).click()
		}
		return pageFactory.get<AccountInfoPage>()
	}

	override fun verifyVisible(): SignupPage {
		step("Verify 'New User Signup!' is visible"){
			step("Expect 'New User Signup!' heading to be visible") {
				byCss(".signup-form h2").shouldBe(visible)
			}
		}
		return this
	}

}
