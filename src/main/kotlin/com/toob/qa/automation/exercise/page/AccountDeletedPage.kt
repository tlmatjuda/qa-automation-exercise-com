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
class AccountDeletedPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {


	@Step("Verify 'ACCOUNT DELETED!' is visible")
	override fun verifyVisible(): AccountDeletedPage {
		step("Expect 'ACCOUNT DELETED!' message to be visible") {
			byCss("h2").shouldHave(text("Account Deleted!")).shouldBe(visible)
		}
		return this
	}

	@Step("Click 'Continue' after account deletion")
	fun clickContinue(): HomePage {
		step("Click 'Continue' button") {
			byCss("[data-qa='continue-button']").shouldBe(visible).click()
		}
		return pageFactory.get<HomePage>()
	}


}