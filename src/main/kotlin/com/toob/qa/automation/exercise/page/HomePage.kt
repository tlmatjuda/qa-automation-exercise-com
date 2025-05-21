package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.open
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.WebUIConfigs
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.ext.SelenideExtensions.eleCollection
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class HomePage(val webUIConfigs: WebUIConfigs, pageFactory: PageFactory) : AbstractPage(pageFactory) {

	val CSS_SELECTOR_LOGIN = "a[href='/login']"

	fun open(): HomePage {
		step("Open Home page") {
			open(webUIConfigs.baseUrl)
		}
		return this
	}

	override fun verifyVisible(): HomePage {
		// Verify the two green buttons
		step("Verify Components") {
			step("Expect \"Green Buttons\" to load") {
				byCss("a[href='/test_cases']").shouldBe(visible)
				byCss("a[href='/api_list']").shouldBe(visible)
			}
		}

		// Verify "Signup / Login" link
		val signUpLinkText = "Signup / Login"
		step("Expect Link : $signUpLinkText to load") {
			val textElement = text(signUpLinkText)
			byCss(CSS_SELECTOR_LOGIN).shouldHave(textElement).shouldBe(visible)
		}
		return this
	}

	fun clickSignupLogin(): SignupPage {
		step("Click on 'Signup / Login' link") {
			byCss(CSS_SELECTOR_LOGIN).shouldBe(visible).click()
		}
		return pageFactory.get<SignupPage>()
	}


	fun fetchLoggedInUserElement() = step("Verify logged-in username is displayed") {
		byCss("a[href='/logout']").shouldBe(visible) // Ensure user is logged in
		eleCollection("ul.navbar-nav li a")
			.findBy(text("Logged in as"))
			.shouldBe(visible)
	}

	fun logout(): HomePage = step("Click Logout link") {
		byCss("a[href='/logout']").click()
		this
	}

	fun clickContactUs(): HomePage = step("Click on 'Contact Us' link") {
		byCss("a[href='/contact_us']")
			.shouldBe(visible).click()
		this
	}

}