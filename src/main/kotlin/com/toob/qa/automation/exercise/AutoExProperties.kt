package com.toob.qa.automation.exercise

import com.toob.qa.automation.exercise.AutoExSupport.AUTOMATIONEXERCISE_COM
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = AUTOMATIONEXERCISE_COM)
class AutoExProperties {
	lateinit var userName: String
	lateinit var userEmail: String
	lateinit var userPassword: String
}