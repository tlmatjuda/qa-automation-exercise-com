package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.open
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.WebUIConfigs
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Step
import org.springframework.stereotype.Component

@Component
class HomePage(val webUIConfigs: WebUIConfigs, pageFactory: PageFactory) : AbstractPage(pageFactory) {

	val CSS_SELECTOR_LOGIN = "a[href='/login']"

	@Step("Open Home page")
	fun open(): HomePage {
		open(webUIConfigs.baseUrl)
		return this
	}

	@Step("Click on 'Signup / Login' link")
	fun clickSignupLogin(): SignupPage {
		byCss(CSS_SELECTOR_LOGIN).shouldBe(visible).click()
		return pageFactory.get<SignupPage>()
	}

	@Step("Verify Components")
	override fun verifyVisible(): HomePage {
		// Verify the two green buttons
		step("Expect \"Green Buttons\" to load") {
			byCss("a[href='/test_cases']").shouldBe(visible)
			byCss("a[href='/api_list']").shouldBe(visible)
		}

		// Verify "Signup / Login" link
		val signUpLinkText = "Signup / Login"
		step("Expect Link : $signUpLinkText to load") {
			val textElement = text(signUpLinkText)
			byCss(CSS_SELECTOR_LOGIN).shouldHave(textElement).shouldBe(visible)
		}
		return this
	}

}