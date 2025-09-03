package com.toob.qa.automationexercise.config;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record LoginDetails(
        @NonNull String name,
        @NonNull String email,
        @NonNull String password
){}