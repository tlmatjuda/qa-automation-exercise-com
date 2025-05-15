package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.open
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.WebUiModuleConfigs
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import io.qameta.allure.Step
import org.springframework.stereotype.Component

@Component
class HomePage(webUiModuleConfigs: WebUiModuleConfigs) : BasePage(webUiModuleConfigs) {

	@Step("Open Home page")
	fun open(): HomePage {
		open(webUiModuleConfigs.baseUrl)
		return this
	}

	@Step("Verify Components")
	fun verifyComponentsVisible(): HomePage {
		// Verify the two green buttons
		step("Expect Green Buttons To Load") {
			byCss("a[href='/test_cases']").shouldBe(visible)
			byCss("a[href='/api_list']").shouldBe(visible)
		}

		// Verify "Signup / Login" link
		val signUpText = "Signup / Login"
		step("Expect Link : $signUpText To Load") {
			val textElement = text(signUpText)
			byCss("a[href='/login']").shouldHave(textElement).shouldBe(visible)
		}
		return this
	}

}