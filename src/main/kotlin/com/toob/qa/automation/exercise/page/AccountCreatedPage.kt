package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Step
import org.springframework.stereotype.Component

@Component
class AccountCreatedPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {


	override fun verifyVisible(): AccountCreatedPage {
		return this
	}

	@Step("Click 'Continue' button after account creation")
	fun clickContinue(): DashboardPage {
		step("Click the 'Continue' button") {
			byCss("[data-qa='continue-button']").shouldBe(visible).click()
		}
		return pageFactory.get<DashboardPage>()
	}


}