package com.kgisl.regex;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import com.kgisl.regex.service.PropertiesService;

public class PropertiesServiceTest {

	@Mock
	private PropertiesService propertiesService = new PropertiesService();

	/* To test negative cases like exceptions */
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void validatePrimeNumberTest() {

		String primeValue = "3";

		Boolean status = propertiesService.validatePrimeNumber(primeValue);

		Assert.assertTrue(status);

		primeValue = "4";

		status = propertiesService.validatePrimeNumber(primeValue);

		Assert.assertFalse(status);
	}

	@Test
	public void validateCreditCardTest() {

		String cardNumber = "12312423423423";

		Boolean status = propertiesService.validateCreditCard(cardNumber);

		Assert.assertFalse(status);
	}

	@Test
	public void validateSpecialCharacterAloneTest() {

		String specialCharacters = "@$^&(!";

		Boolean status = propertiesService.validateSpecialCharacterAlone(specialCharacters);

		Assert.assertTrue(status);

		specialCharacters = "AS4123423";

		status = propertiesService.validateSpecialCharacterAlone(specialCharacters);

		Assert.assertTrue(status);
	}

	@Test
	public void restrictCharacterRangeTest() {

		String characters = "ab";

		Boolean status = propertiesService.restrictCharacterRange(characters);

		Assert.assertTrue(status);

		characters = "yu";

		status = propertiesService.restrictCharacterRange(characters);

		Assert.assertFalse(status);
	}

	@Test
	public void restrictNumberRangeTest() {
		
		String numbers = "04";

		Boolean status = propertiesService.restrictNumberRange(numbers);

		Assert.assertTrue(status);

		numbers = "67";

		status = propertiesService.restrictNumberRange(numbers);

		Assert.assertFalse(status);
	}

	@Test
	public void restrictSpecialCharactersTest() {

	}

	@Test
	public void validatePassportCurrencyTest() {

	}

	@Test
	public void validatePANTest() {

	}

	@Test
	public void validateTextTest() {

	}

	@Test
	public void validateNumberTest() {

	}

}
