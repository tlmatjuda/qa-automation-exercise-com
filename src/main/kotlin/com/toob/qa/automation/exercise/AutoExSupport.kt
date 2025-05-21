package com.toob.qa.automation.exercise

import com.codeborne.selenide.SelenideElement
import com.toob.qabase.webui.ext.SelenideExtensions.byCss

object AutoExSupport {

	const val AUTOMATIONEXERCISE_COM = "automationexercise-com"
	const val RESOURCE_PATH = "src/test/resources"

	fun inputDataQaElementByCss(elementName: String): SelenideElement =
		byCss("input[data-qa='$elementName']")

}