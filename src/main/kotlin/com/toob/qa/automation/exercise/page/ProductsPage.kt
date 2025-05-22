package com.toob.qa.automation.exercise.page

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.toob.qabase.core.AllureExtensions.step
import com.toob.qabase.util.ReportCompressor
import com.toob.qabase.util.logger
import com.toob.qabase.webui.ext.SelenideExtensions.byCss
import com.toob.qabase.webui.ext.SelenideExtensions.eleCollection
import com.toob.qabase.webui.page.AbstractPage
import com.toob.qabase.webui.page.PageFactory
import org.springframework.stereotype.Component
import java.util.regex.Pattern
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.assertTrue

@Component
class ProductsPage(pageFactory: PageFactory) : AbstractPage(pageFactory) {

    private val log = logger<ProductsPage>()

    companion object {
        const val PRODUCTS_SEARCH_RESULTS_GRID = ".features_items .col-sm-4"
    }

    override fun verifyVisible(): ProductsPage {
        step("Verify ALL PRODUCTS page is displayed") {
            byCss("h2.title.text-center").shouldHave(text("All Products"))
            eleCollection(PRODUCTS_SEARCH_RESULTS_GRID).shouldHave(sizeGreaterThan(0))
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

    fun searchForProduct(query: String): ProductsPage = step("Search for product: $query") {
        byCss("#search_product").setValue(query)
        byCss("#submit_search").click()
        this
    }

    fun verifySearchResultsContain(expected: String): ProductsPage = step("Verify search results contain: $expected") {
        byCss("h2.title.text-center").shouldHave(text("Searched Products"))
        eleCollection(PRODUCTS_SEARCH_RESULTS_GRID).shouldHave(sizeGreaterThan(0))
        val regex = Regex(expected, RegexOption.IGNORE_CASE)

        eleCollection(PRODUCTS_SEARCH_RESULTS_GRID)
            .map { it.text() }
            .any { it.contains(regex) }
            .also { found ->
                if (found) {
                    log.info { "✅ Found product(s) matching: \"$expected\"" }
                } else {
                    error("❌ No search results matched '$expected' (case-insensitive)")
                }
            }
        this
    }

}