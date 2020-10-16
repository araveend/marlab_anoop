package com.marlap.bean;

import java.sql.Timestamp;

public class SecurityBean {
	private String question;
	private String previousQuestion;
	private String ans;
	private Timestamp createdOn;
	private String uniqueId;

	SecurityBean() {
	}

	public SecurityBean(String question, String ans,String previousQuestion) {
		this.question = question;
		this.ans = ans;
		this.createdOn = new Timestamp(System.currentTimeMillis());
		this.uniqueId = java.util.UUID.randomUUID().toString();
		this.previousQuestion = previousQuestion;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getPreviousQuestion() {
		return previousQuestion;
	}

	public void setPreviousQuestion(String previousQuestion) {
		this.previousQuestion = previousQuestion;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	

}