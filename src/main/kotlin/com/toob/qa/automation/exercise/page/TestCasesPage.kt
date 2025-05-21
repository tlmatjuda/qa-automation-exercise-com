package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.Condition.text
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class TestCasesPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

    override fun verifyVisible(): TestCasesPage = step("Verify Test Cases page is visible") {
        byCss("h2.title.text-center").shouldHave(text("Test Cases"))
        this
    }

}