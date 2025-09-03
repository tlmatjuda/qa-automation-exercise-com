package com.toob.qa.automationexercise.flows;

import com.toob.qa.automationexercise.pages.CartPage;
import com.toob.qa.automationexercise.pages.HeaderBar;
import com.toob.qa.automationexercise.pages.ProductDetailPage;
import com.toob.qa.automationexercise.pages.ProductsPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartFlow {

    private final HeaderBar header;
    private final ProductsPage products;
    private final ProductDetailPage details;
    private final CartPage cart;

    @Step("Add product id={id} qty={qty} and verify cart")
    public void addAndVerify(int id, int qty) {
        header.openProducts();
        products.openProductById(id);
        details.verifyVisible().setQuantity(qty).addToCart();
        cart.verifyVisible()
                .shouldContainProduct(id)
                .shouldHaveQuantity(id, qty);
    }
}