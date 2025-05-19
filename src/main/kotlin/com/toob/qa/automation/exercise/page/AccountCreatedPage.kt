package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class AccountCreatedPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {


	override fun verifyVisible(): AccountCreatedPage {
		step("Verify 'ACCOUNT CREATED!' is visible") {
			val expectedText = text("Account Created!")
			byCss("h2").shouldHave(expectedText).shouldBe(visible)
		}
		return this
	}

	fun clickContinue(): DashboardPage {
		step("Click 'Continue' button after account creation") {
			step("Click the 'Continue' button") {
				byCss("[data-qa='continue-button']").shouldBe(visible).click()
			}
		}
		return pageFactory.get<DashboardPage>()
	}


}