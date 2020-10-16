package com.marlap.service;

public interface SecurityService {

	public String requestQuestion();

	public Integer getSumForQuestion(String ansString);
	
	public boolean validateOriginalQuestion(String question, String prevQuestion);

}
