package com.toob.qa.automation.exercise

import com.toob.qabase.QaBaseAutomationModule
import org.springframework.context.annotation.ComponentScan

@QaBaseAutomationModule
@ComponentScan( basePackageClasses = [QaAutomationExercisePackage::class])
class QaAutomationExerciseComApplication
