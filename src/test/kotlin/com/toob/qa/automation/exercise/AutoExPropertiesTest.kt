package com.toob.qa.automation.exercise

import com.toob.qabase.QaBaseTest
import com.toob.qabase.core.AllureExtensions.step
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlin.test.Test
import kotlin.test.assertNotNull

@Epic("File Based Configuration")
@Feature("Application YAML")
@Story("Configuration load properly and as expected")
@QaBaseTest
class AutoExPropertiesTest(private val autoExProps: AutoExProperties) {

	@Test
	fun configPropertiesShouldLoad() {
		step("Verify properties are all loaded") {
			assertNotNull(autoExProps)
			assertNotNull(autoExProps.userName)
			assertNotNull(autoExProps.userEmail)
			assertNotNull(autoExProps.userPassword)
		}
	}
}