package com.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${rest.endpoint}")
	private String endpoint;

	public String getcall() {
		String response = restTemplate.getForObject(endpoint, String.class);
		return response;
	}
	
	@Value("${post.endpoint}")
	private String postEndpoint;

	public String postCall(String json) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		String response = restTemplate.postForObject(postEndpoint, entity, String.class);
		return response;
	}
	
	@Value("${entity.endpoint}")
	private String entityEndpoint;
	public ResponseEntity<String> postCallForeResponseEntity(String json){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(json, headers);
		//ResponseEntity<String> response = restTemplate.postForObject(postEndpoint, entity, String.class);
		ResponseEntity<String> result = restTemplate.exchange(entityEndpoint, HttpMethod.POST, entity, String.class);
		result.getBody();
		result.getStatusCodeValue();
		return result;
	}
}
