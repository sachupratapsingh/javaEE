package com.corona.service;

import com.corona.bo.CoronaBo;
import com.corona.dao.CoronaDao;
import com.corona.dao.CoronaDaoImpl;
import com.corona.dto.CoronaDto;
import com.corona.vo.CoronaVo;

public class CoronaServiceImpl implements CoronaService {
       
    private CoronaDao dao=null;
    public CoronaServiceImpl(){
    	this.dao=new CoronaDaoImpl();
    }
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
	
	
	
	
//	public void setDao(CoronaDao dao) {
//		this.dao = dao;
//	}




	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public void setAdmit_date(String admit_date) {
		this.admit_date = admit_date;
	}




	public void setDischarge_date(String discharge_date) {
		this.discharge_date = discharge_date;
	}




	public void setDead_date(String dead_date) {
		this.dead_date = dead_date;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public void setHome_state(String home_state) {
		this.home_state = home_state;
	}




	public void setMob(long mob) {
		this.mob = mob;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public CoronaDao getDao() {
		return dao;
	}




	public String getPatient_name() {
		return patient_name;
	}




	public String getStatus() {
		return status;
	}




	public String getAdmit_date() {
		return admit_date;
	}




	public String getDischarge_date() {
		return discharge_date;
	}




	public String getDead_date() {
		return dead_date;
	}




	public String getGender() {
		return gender;
	}




	public String getHome_state() {
		return home_state;
	}




	public long getMob() {
		return mob;
	}




	public int getAge() {
		return age;
	}




	@Override
	public String register(CoronaDto cdto,CoronaVo cvo) throws Exception {
		//this.patient_name=cdto.getPatient_name();
		int i=0;
		String s1=null,s2=null;
		//String s3="Update Successful",s4="Update Failed";
		this.setAdmit_date(cdto.getAdmit_date());
		this.setAge(cdto.getAge());
		this.setDead_date(cdto.getDead_date());
		this.setDischarge_date(cdto.getDischarge_date());
		this.setGender(cdto.getGender());
		this.setHome_state(cdto.getHome_state());
		this.setMob(cdto.getMob());
		this.setPatient_name(cdto.getPatient_name());
		this.setStatus(cdto.getStatus());
		this.setPatient_no(cdto.getPatient_no());
		CoronaBo cbo=new CoronaBo();
		if(this.status.equalsIgnoreCase("nep"))
			status="ADMITTED";
		else if(this.status.equalsIgnoreCase("dip"))
			status="DISCHARGED";
		else if(this.status.equalsIgnoreCase("dep"))
			status="DEAD";
		if(this.discharge_date==null)
			discharge_date="NO ANY DATA";
		if(this.dead_date==null)
			dead_date="NO ANY DATA";
		cbo.setAdmit_date(admit_date);
		cbo.setAge(age);
		cbo.setDead_date(dead_date);
		cbo.setDischarge_date(discharge_date);
		cbo.setGender(gender);
		cbo.setHome_state(home_state);
		cbo.setMob(mob);
		cbo.setPatient_name(patient_name);
		cbo.setStatus(status);
		cbo.setPatient_no(patient_no);
		if(cbo.getAdmit_date()!=null) { 
		i=dao.insert(cbo);
		s1="Registration Successful...ThankYou";s2="Registration Failed....Try Again.";}
		else if(cbo.getStatus().equalsIgnoreCase("DISCHARGED") || cbo.getStatus().equalsIgnoreCase("DEAD"))
	    {
		i=dao.update(cbo);
		s1="Update Successful.";s2="Update Failed.";
		}
		if(cbo.getPatient_no()!=null) {
		i=dao.edit(cbo);
		s1="Edit & Update Successful.";s2=" Edit & Update Failed.";
		}
		if(i==0)
		return s2;
		else
			return i+" "+s1;
	}

}
