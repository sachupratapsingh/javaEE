package com.corona.bo;

public class CoronaBo {
	private String patient_name;
	private String status;
	private String admit_date;
	private String discharge_date;
	private String dead_date;
	private String gender;
	private String home_state;
	private String patient_no;
	public String getPatient_no() {
		return patient_no;
	}
	public void setPatient_no(String patient_no) {
		this.patient_no = patient_no;
	}
	private long mob;
	private int age;
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAdmit_date() {
		return admit_date;
	}
	public void setAdmit_date(String admit_date) {
		this.admit_date = admit_date;
	}
	public String getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(String discharge_date) {
		this.discharge_date = discharge_date;
	}
	public String getDead_date() {
		return dead_date;
	}
	public void setDead_date(String dead_date) {
		this.dead_date = dead_date;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHome_state() {
		return home_state;
	}
	public void setHome_state(String home_state) {
		this.home_state = home_state;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
