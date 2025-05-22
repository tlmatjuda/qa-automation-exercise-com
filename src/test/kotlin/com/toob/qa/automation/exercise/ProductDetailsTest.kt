package com.toob.qa.automation.exercise


import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_EPIC
import com.toob.qa.automation.exercise.page.HomePage
import com.toob.qabase.QaBaseTest
import com.toob.qabase.webui.page.PageFactory
import io.qameta.allure.Description
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Story
import kotlin.test.Test



@Epic(AUTOMATIONEXERCISE_EPIC)
@Feature("Product Browsing")
@Story("Verify All Products and Product Detail Page")
@QaBaseTest
class ProductDetailsTest(pageFactory: PageFactory) {

    val homePage = pageFactory.get<HomePage>()

    @Test
    @Description("Verify all products and product detail page")
    fun verifyProductDetailsPage() {
        homePage
            .open()
            .verifyVisible()
            .clickProducts()
            .verifyVisible()
            .clickFirstViewProduct()
            .verifyVisible()
    }

}