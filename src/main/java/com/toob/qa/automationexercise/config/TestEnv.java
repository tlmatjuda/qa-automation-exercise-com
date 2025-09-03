package com.toob.qa.automationexercise.config;


import org.apache.commons.lang3.StringUtils;

public final class TestEnv {
    private TestEnv() {}

    public static LoginDetails credentials() {
        return LoginDetails.builder()
                .name(req("AUTOMATIONEXERCISE_USER_NAME"))
                .email(req("AUTOMATIONEXERCISE_USER_EMAIL"))
                .password(req("AUTOMATIONEXERCISE_PASSWORD"))
                .build();
    }

    private static String req(String key) {
        var v = System.getenv(key);
        if (StringUtils.isBlank(v)) {
            throw new IllegalStateException("Missing env var: " + key);
        }
        return v;
    }
}
