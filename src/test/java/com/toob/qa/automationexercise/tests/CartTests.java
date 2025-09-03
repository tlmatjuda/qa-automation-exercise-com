package com.toob.qa.automationexercise.tests;

import com.toob.qa.automationexercise.base.BaseUiTest;
import com.toob.qa.automationexercise.flows.CartFlow;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Cart")
@RequiredArgsConstructor
public class CartTests extends BaseUiTest {

    private final CartFlow cart;

    @Test
    @DisplayName("TC12: Add product to cart")
    void shouldAddProductToCart() {
        cart.addAndVerify(1, 3);
    }
}