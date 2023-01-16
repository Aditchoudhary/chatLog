package com.ofBusiness.chatLogs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ofBusiness.chatLogs.ChatLogsApplication;
import com.ofBusiness.chatLogs.dto.ChatLogDTO;
import com.ofBusiness.chatLogs.entity.ChatLog;

@SpringBootTest(classes = ChatLogsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class ChatLogControllerTest {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	@Order(1)
	public void testPostChatLog() throws JSONException {

		ChatLogDTO body = new ChatLogDTO();
		body.setMessage("Hi My Name is Adit Choudhary");
		body.setSent(true);
		body.setTimestamp(Long.valueOf(1673875153));
		headers.add("Content-Type", "application/json");
		HttpEntity<ChatLogDTO> entity = new HttpEntity<>(body, headers);

		ResponseEntity<Integer> response = restTemplate.exchange(
				createURLWithPort("/chatlogs/adit"), HttpMethod.POST, entity,
				Integer.class);
		
		assertNotNull(response);
		assertEquals(201, response.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void testRetrieveChatLog() throws JSONException {
		
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<List<ChatLog>> response = restTemplate.exchange(
				createURLWithPort("/chatlogs/adit?start=1&limit=10"), HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ChatLog>>() {});

		
		String actualUser = response.getBody().get(0).getUser();
		String expected = "adit";
		
		assertEquals(expected, actualUser);
		assertEquals(200, response.getStatusCodeValue());
		
	}
	
	@Test
	@Order(3)
	public void testDeleteChatLog() {
		
		headers.add("Content-Type", "application/json");
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/chatlogs/adit"), HttpMethod.DELETE, entity,
				String.class);
		assertEquals(204, response.getStatusCodeValue());
		
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
