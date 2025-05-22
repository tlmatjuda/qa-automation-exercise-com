package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qa.automation.exercise.page.ProductsPage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlin.test.Test



@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Product Search")
@Story("Verify product search functionality")
@QaBaseTest
class ProductSearchTest(pageFactory: PageFactory) {

    val homePage = pageFactory.get<HomePage>()
    val productsPage = pageFactory.get<ProductsPage>()

    @Test
    @Description("Verify that the search returns relevant products")
    fun verifyProductSearch() {
        homePage
            .open()
            .clickProducts()

        productsPage
            .searchForProduct("top")
            .verifySearchResultsContain("top")
    }

}