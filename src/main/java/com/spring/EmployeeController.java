package com.spring;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	private RestTemplateService restTemplateService;
	
	public EmployeeController(RestTemplateService restTemplateService) {
		this.restTemplateService=restTemplateService;
	}

	@GetMapping(value="/empList")
	public List<Employee> getListofEmployee() throws JsonParseException, JsonMappingException, IOException {
		String resultJson = restTemplateService.getcall();
		ObjectMapper mapper=new ObjectMapper();
		List<Employee> empList = mapper.readValue(resultJson, new TypeReference<List<Employee>>(){});
		return empList;
	}
	
	@PostMapping(value="/getAllEmpByPost")
	public List<Employee> postCallRestTemplateEmployee(@RequestBody Employee employee) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		String jsonEmployee = mapper.writeValueAsString(employee);
		String resultJson = restTemplateService.postCall(jsonEmployee);
		List<Employee> empList = mapper.readValue(resultJson, new TypeReference<List<Employee>>(){});
		return empList;
	}
	
	@PostMapping(value="/getAllEmpByEntity")
	public List<Employee> entityCallRestTemplateEmployee(@RequestBody Employee employee) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper=new ObjectMapper();
		String jsonEmployee = mapper.writeValueAsString(employee);
		ResponseEntity<String> resultJson = restTemplateService.postCallForeResponseEntity(jsonEmployee);
		String json = resultJson.getBody();
		List<Employee> empList = mapper.readValue(json, new TypeReference<List<Employee>>(){});
		int statusCode=resultJson.getStatusCodeValue();
		return empList;
	}
	
	
}
