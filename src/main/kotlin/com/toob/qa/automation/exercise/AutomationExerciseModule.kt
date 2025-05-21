package com.toob.qa.automation.exercise

import com.toob.qabase.QaBaseAutomationModule
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan

@QaBaseAutomationModule
@ComponentScan( basePackageClasses = [AutomationExercisePackage::class])
@EnableConfigurationProperties(AutoExProperties::class)
class QaAutomationExerciseComApplication
