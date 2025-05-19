package com.toob.qa.automation.exercise

import com.toob.qa.automation.exercise.model.BasicDetails
import com.toob.qa.automation.exercise.model.UserAddress

object MockDataFactory {

	fun basicDetails(): BasicDetails = BasicDetails(
		password = "password123",
		day = "1",
		month = "January",
		year = "2000"
	)

	fun userAddress(): UserAddress = UserAddress(
		firstName = "John",
		lastName = "Doe",
		company = "Test Co",
		address1 = "123 Test St",
		address2 = "Suite 1",
		country = "Canada",
		state = "ON",
		city = "Toronto",
		zipcode = "M4B1B3",
		mobileNumber = "1234567890"
	)

}