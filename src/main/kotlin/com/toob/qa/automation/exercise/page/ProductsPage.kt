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
class ProductsPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

    override fun verifyVisible(): ProductsPage {
        step("Verify ALL PRODUCTS page is displayed") {
            byCss("h2.title.text-center").shouldHave(text("All Products"))
            eleCollection(".features_items .col-sm-4").shouldHave(sizeGreaterThan(0))
        }
        return this
    }

    fun clickFirstViewProduct(): ProductDetailPage = step("Click on first 'View Product' button") {
        eleCollection("a[href^='/product_details/']")
            .first()
            .scrollIntoView(true)
            .shouldBe(visible)
            .click()

        pageFactory.get<ProductDetailPage>()
    }

}