package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Patient;

@Service
public class PatientService {

	public static List<Patient> patients = init();

	private static List<Patient> init() {

		Patient p1 = new Patient(1, "A");
		Patient p2 = new Patient(2, "B");
		Patient p3 = new Patient(3, "C");
		Patient p4 = new Patient(4, "D");
		Patient p5 = new Patient(5, "E");
		Patient p6 = new Patient(6, "F");
		
		return List.of(p1,p2,p3,p4,p5,p6);
	}
	
}
