package com.marlap.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlap.bean.SecurityBean;
import com.marlap.service.SecurityService;

@RestController
@RequestMapping("${app.rest.base.path}/security")
public class SecurityResource extends ResponseBuilder {

	@Autowired
	private SecurityService service;
	
	@Autowired
	private Environment environment;
	
	@PostMapping(value = "/question",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> question(@RequestBody String requestString) {
		if(StringUtils.isEmpty(requestString)) {
			return buildResponseEntity(new SecurityBean(environment.getProperty("app.marlab.wrong"),"",""), HttpStatus.BAD_REQUEST);
		}
		return buildResponseEntity(new SecurityBean( service.requestQuestion(),"",""), HttpStatus.OK);
	}
	
	@PostMapping(value = "/answer",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> answer(@RequestBody SecurityBean answerString) {
		System.out.println(answerString.getPreviousQuestion());
		if(StringUtils.isEmpty(answerString.getAns())) {
			return buildResponseEntity(environment.getProperty("app.marlab.wrong"), HttpStatus.BAD_REQUEST);
		}
		if(service.validateOriginalQuestion(answerString.getQuestion(), answerString.getPreviousQuestion())) {
			
		
		Integer sum = service.getSumForQuestion(answerString.getQuestion());
		
		return buildResponseEntity(new SecurityBean(answerString.getQuestion(),environment.getProperty("app.marlab.great")+answerString.getQuestion()+environment.getProperty("app.marlab.answer")+" "+sum , answerString.getPreviousQuestion()),
				HttpStatus.OK );
		} else {
			return buildResponseEntity(environment.getProperty("app.marlab.wrong"), HttpStatus.BAD_REQUEST);
		}
	}

}
