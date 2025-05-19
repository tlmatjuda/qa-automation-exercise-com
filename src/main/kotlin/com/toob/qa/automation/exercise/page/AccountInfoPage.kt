package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.open
import com.toob.qa.automation.exercise.model.BasicDetails
import com.toob.qa.automation.exercise.model.UserAddress
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Step
import org.springframework.stereotype.Component

@Component
class AccountInfoPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

	@Step("Verify 'ENTER ACCOUNT INFORMATION' is visible")
	override fun verifyVisible(): AccountInfoPage {
		step("Expect 'ENTER ACCOUNT INFORMATION' section to be visible") {
			byCss("h2").shouldHave(text("Enter Account Information")).shouldBe(visible)
		}
		return this
	}

	fun fillBasicDetails(details: BasicDetails): AccountInfoPage {
		step("Expect 'ENTER ACCOUNT INFORMATION' is visible") {
			byCss("#id_gender1").click()
			byCss("#password").value = details.password
			byCss("#days").selectOption(details.day)
			byCss("#months").selectOption(details.month)
			byCss("#years").selectOption(details.year)
		}
		return this
	}

	fun selectCheckboxes(): AccountInfoPage {
		step("Select 'Newsletter' and 'Special Offers' checkboxes") {
			step("Check 'Sign up for our newsletter!'") {
				byCss("#newsletter").click()
			}
			step("Check 'Receive special offers from our partners!'") {
				byCss("#optin").click()
			}
		}
		return this
	}

	fun fillAddressDetails(user: UserAddress): AccountInfoPage {
		step("Fill user address details") {
			byCss("#first_name").value = user.firstName
			byCss("#last_name").value = user.lastName
			byCss("#company").value = user.company
			byCss("#address1").value = user.address1
			byCss("#address2").value = user.address2
			byCss("#country").selectOption(user.country)
			byCss("#state").value = user.state
			byCss("#city").value = user.city
			byCss("#zipcode").value = user.zipcode
			byCss("#mobile_number").value = user.mobileNumber
		}
		return this
	}

	fun clickCreateAccount(): AccountCreatedPage {
		step("Click 'Create Account' button") {
			step("Submit the account creation form") {
				byCss("[data-qa='create-account']").shouldBe(visible).click()
			}
		}
		return pageFactory.get<AccountCreatedPage>()
	}

}