package com.user.manegiment.basis.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

	@Autowired
	private MessageSource messageSource;

	public Map<String, Integer> getGenderMap(Locale locale) {
		Map<String, Integer> genderMap = new LinkedHashMap<>();

		genderMap.put(messageSource.getMessage("male", null, locale), 1);
		genderMap.put(messageSource.getMessage("female", null, locale), 2);

		return genderMap;
	}
}
