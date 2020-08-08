package com.kloudeone.comments.services.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface HelperInterface {

	
	

	default public LocalDateTime getDateTime()
	{
		return LocalDateTime.now();
			
	}
	
	default public <F,T>T convertObj(F from, T to)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		to = (T) objectMapper.convertValue(from, to.getClass());
		return to;
	}
	

	 
}
