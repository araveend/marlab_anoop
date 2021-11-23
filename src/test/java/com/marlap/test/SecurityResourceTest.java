package com.marlap.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.marlap.app.MarlapApplication;
import com.marlap.resource.SecurityResource;
import com.marlap.service.SecurityServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MarlapApplication.class)
@WebMvcTest(SecurityResource.class)
public class SecurityResourceTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SecurityServiceImpl service;

	@BeforeClass
	public static void setUp() {
	}

	private String basePath = "/api/v1/security/";

	@Test
	public void getQuestion() throws Exception {
		when(service.requestQuestion())
				.thenReturn("Here you go, solve the question: \"Please sum1 the nddumbers 14,20,2\"");
		/*
		 * this.mockMvc.perform(post(basePath +
		 * "question").contentType(MediaType.APPLICATION_JSON).content("test"))
		 * .andDo(print()).andExpect(status().isOk()). andExpect(
		 * jsonPath("$[0].question","\"Please sum the numbers 14,20,2\""));
		 */
				
				 
		 
		 
	}

	@Test
	public void validateAns() throws Exception {
		String ans = "Great. The original question was \"Please sum the numbers 14,20,2\" and the answer is 36.";
		when(service.getSumForQuestion(ans)).thenReturn(28);
		//this.mockMvc.perform(post(basePath + "answer").contentType(MediaType.APPLICATION_JSON).content(ans))
				//.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("That's great")));
	}

	@Test
	public void invalidAns() throws Exception {
		String ans = "Great. The original question was \"Please sum the numbers 14,20,2\" and the answer is 56.";
		when(service.getSumForQuestion(ans)).thenReturn(10);
		//this.mockMvc.perform(post(basePath + "answer").contentType(MediaType.APPLICATION_JSON).content(ans))
				//.andDo(print()).andExpect(status().isBadRequest())
				//.andExpect(content().string(containsString("That's wrong. Please try again")));
	}

	
}