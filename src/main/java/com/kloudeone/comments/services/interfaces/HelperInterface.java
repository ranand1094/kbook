package com.kloudeone.comments.services.interfaces;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kloudeone.comments.entities.PostsEntity;

public interface HelperInterface {

	
	LocalDateTime localDateTime = LocalDateTime.now();

	default public <F,T>T convertObj(F from, T to)
	{
		ObjectMapper objectMapper = new ObjectMapper();
		to = (T) objectMapper.convertValue(from, to.getClass());
		return to;
	}
	

	 
}
