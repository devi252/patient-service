package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	@GetMapping("/patients")
	public List<Patient> getList() {
		return PatientService.patients;
	}

	@GetMapping("/patient/{id}")
	public Patient getList(@PathVariable("id") int id) {
		return PatientService.patients.stream().filter(f -> f.getPatientId() == id).collect(Collectors.toList()).get(0);
	}

	@GetMapping("/patients/header")
	public ResponseEntity<Map<String, String>> getRequestHeaderParameters(@RequestHeader Map<String, String> map)
			throws URISyntaxException {
		System.out.println(map);

		URI uri = new URI("/patients/header");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uri);
		responseHeaders.set("MyResponseHeader", "MyValue");

		return new ResponseEntity<Map<String, String>>(map, responseHeaders, HttpStatus.OK);
	}

	@PostMapping("/post-header-and-body")
	public ResponseEntity<List<Map<String, String>>> getPostReqestHeaderAndBody(
			@RequestHeader Map<String, String> headerMap, @RequestBody Map<String, String> body) {
		System.out.println("header : " + headerMap);
		System.out.println("Body : " + body);

		return new ResponseEntity<List<Map<String, String>>>(List.of(headerMap, body), HttpStatus.OK);
	}

	@PutMapping("/update-patient/{id}")
	public Map<String, List<Patient>> updateEntityWithPut(@PathVariable int id,
			@RequestHeader Map<String, String> headerMap, @RequestBody Patient patienAsBody) {

		// creating deep copy of patients list
		List<Patient> oldList = new ArrayList<>();
		for (Patient patient : PatientService.patients) {
			Patient p = new Patient();
			p.setPatientId(patient.getPatientId());
			p.setPatientName(patient.getPatientName());

			oldList.add(p);
		}

		Map<String, List<Patient>> map = new HashMap<String, List<Patient>>();
		map.put("oldList", oldList);

		PatientService.patients = PatientService.patients.stream().map(m -> {
			if (m.getPatientId() == id) {
				m.setPatientName(patienAsBody.getPatientName());
			}
			return m;
		}).collect(Collectors.toList());

		map.put("modifiedList", PatientService.patients);
		return map;
	}
}
