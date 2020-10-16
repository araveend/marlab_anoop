package com.marlap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.marlap.bean.SecurityBean;
import com.marlap.util.Constant;



@Service
public class SecurityServiceImpl implements SecurityService {

	private volatile static Map<String, SecurityBean> referenceMap = new HashMap<String, SecurityBean>();

	@Override
	public String requestQuestion() {
		
		 Random random = new Random();
	        IntStream randomIntegerNumbers = random.ints(3, 2, 15);
	        return Constant.QUESTION_PREFIX + randomIntegerNumbers
	                .boxed()
	                .map(String::valueOf)
	                .collect(Collectors.joining(","));
		
	}

	
	@Override
	 public  Integer getSumForQuestion(String originalQuestion) {
	        String questionValues = originalQuestion.substring(Constant.QUESTION_PREFIX.length());
	        return Stream.of(questionValues.split(","))
	                .map(Integer::parseInt)
	                .reduce(0, Integer::sum);
	 }
	
	@Override
	public  boolean validateOriginalQuestion(String question, String prevQuestion) {
	        return prevQuestion != null ? prevQuestion.equals(question) : false;
	 }


   

   
	@Async
	public void cleanUp() {
		long now = System.currentTimeMillis();
		List<String> keys = referenceMap.entrySet().stream()
				.filter(entry -> (now - entry.getValue().getCreatedOn().getTime()) / (24 * 60 * 60 * 1000) > 2)
				.map(Entry::getKey).collect(Collectors.toList());
		keys.forEach(key -> referenceMap.remove(key));
	}

}
