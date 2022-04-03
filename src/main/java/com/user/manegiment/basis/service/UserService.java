package com.user.manegiment.basis.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public Map<String,Integer> getGenderMap(){
		Map<String,Integer> genderMap = new LinkedHashMap<>();
		genderMap.put("男性",1);
		genderMap.put("女性",2);
		
		return genderMap;
		}
}
