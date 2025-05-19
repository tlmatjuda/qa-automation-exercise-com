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
class DashboardPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

	var userName: String? = null


	@Step("Verify user is logged in as '{name}'")
	override fun verifyVisible(): DashboardPage {
		step("Expect 'Logged in as $userName' to be visible") {
			byCss("a[href='/profile']").shouldHave(text("Logged in as $userName")).shouldBe(visible)
		}
		return this
	}

	fun verifyLoggedInAs(name: String): DashboardPage {
		userName = name
		return this
	}

	@Step("Click 'Delete Account' button")
	fun clickDeleteAccount(): AccountDeletedPage {
		step("Click 'Delete Account' link or button") {
			byCss("a[href='/delete_account']").shouldBe(visible).click()
		}
		return pageFactory.get<AccountDeletedPage>()
	}

}