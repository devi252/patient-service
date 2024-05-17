package com.example.demo.model;

public class Patient {

	int patientId;
	String patientName;

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int patientId, String patientName) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + "]";
	}

}
