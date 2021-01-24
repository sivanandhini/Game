package com.kgisl.regex.service;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.kgisl.regex.resource.Properties;

@Service
public class PropertiesService {

	public Boolean validateProperty(Properties property) {

		Boolean validationStatus = false;

		switch (property.getKey()) {
		  case "Number":
			  validationStatus = validateNumber(property.getValue());
			  break;
		  case "Text":
			  validationStatus = validateText(property.getValue());
			  break;
		  case "PAN":
			  validationStatus = validatePAN(property.getValue());
			  break;
		  case "PassportCurrency":
			  validationStatus = validatePassportCurrency(property.getValue());
			  break;
		  case "Age":
			  validationStatus = validateAge(property.getValue());
			  break;
		  case "RestrictSpecialCharacters":
			  validationStatus = restrictSpecialCharacters(property.getValue());
			  break;
		  case "RestrictCharacterRange":
			  validationStatus = restrictCharacterRange(property.getValue());
			  break;
		  case "RestrictNumberRange":
			  validationStatus = restrictNumberRange(property.getValue());
			  break;
		  case "RestrictDateRange":
			  validationStatus = restrictDateRange(property.getValue());
			  break;
		  case "SpecialCharacterAlone":
			  validationStatus = validateSpecialCharacterAlone(property.getValue());
			  break;
		  case "CreditCard":
			  validationStatus = validateCreditCard(property.getValue());
			  break;
		  case "PrimeNumber":
			  validationStatus = validatePrimeNumber(property.getValue());
			  break;
		}

		return validationStatus;
	}

	public Boolean validatePrimeNumber(String value) {

		Integer number = Integer.parseInt(value);
		
		return BigInteger.valueOf(number).isProbablePrime(50);
	}

	public Boolean validateCreditCard(String value) {

        String regex = "^4[0-9]{12}(?:[0-9]{3})?$"; 
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(value); 

        return m.matches();
	}

	public Boolean validateSpecialCharacterAlone(String value) {

		Boolean status = restrictSpecialCharacters(value);
		
		return !status;
	}

	public Boolean restrictDateRange(String value) {

		String regex = "^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$"; 
        Pattern p = Pattern.compile(regex); 
        Matcher m = p.matcher(value); 

        return !m.matches();
	}

	public Boolean restrictNumberRange(String value) {

		String regex = "[0-4]+";

		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean restrictCharacterRange(String value) {

		String regex = "[A-Sa-s ]*";

		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean restrictSpecialCharacters(String value) {

		Pattern p = Pattern.compile("[A-Za-z0-9]*");
		Matcher m = p.matcher(value);
		
		return m.matches();
	}

	public Boolean validateAge(String value) {
		
        String regex  = "^(?:1[01][0-9]|120|1[7-9]|[2-9][0-9])$"; 
		
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean validatePassportCurrency(String value) {

		String regex  = "^[A-PR-WYa-pr-wy][1-9]\\d\\s?\\d{4}[1-9]$"; 
		
		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean validatePAN(String value) {

        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean validateText(String value) {

		String regex = "[A-Za-z ]*";

		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

	public Boolean validateNumber(String value) {

		String regex = "[0-9]+";

		Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

		return m.matches();
	}

}
