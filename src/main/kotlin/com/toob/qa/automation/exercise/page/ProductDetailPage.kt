package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.ext.SelenideExtensions.eleCollection
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component

@Component
class ProductDetailPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

    override fun verifyVisible(): ProductDetailPage {
        step("Verify product detail page elements are visible") {
            byCss(".product-information h2").shouldBe(visible) // name
            byCss(".product-information p").shouldHave(text("Category:")) // category
            byCss(".product-information span span").shouldBe(visible) // price
            byCss(".product-information p:nth-of-type(2)").shouldHave(text("Availability:"))
            byCss(".product-information p:nth-of-type(3)").shouldHave(text("Condition:"))
            byCss(".product-information p:nth-of-type(4)").shouldHave(text("Brand:"))
        }
        return this
    }

}