package com.toob.qa.automationexercise.tests;

import com.toob.qa.automationexercise.base.BaseUiTest;
import com.toob.qa.automationexercise.flows.CartFlow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Cart")
class CartTests extends BaseUiTest {

    @Test
    @DisplayName("TC12: Add product to cart")
    void shouldAddProductToCart() {
        CartFlow cartFlow = ctx.cartFlow;
        assertNotNull(cartFlow);
        cartFlow.addAndVerify(1, 3);
    }
}